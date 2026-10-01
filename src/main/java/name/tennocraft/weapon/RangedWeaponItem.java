package name.tennocraft.weapon;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public abstract class RangedWeaponItem extends Item {
    public final RangedWeaponStats stats;

    protected RangedWeaponItem(Properties properties, RangedWeaponStats stats) {
        super(properties);
        this.stats = stats;
    }

    /** Fire exactly one "shot" (one bullet, one arrow, one kunai) — server-side only. */
    protected abstract void fire(Level level, Player player, ItemStack stack);

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000; // AUTO and CHARGE both ride this; SEMI never reaches it — it fires directly in use()
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return stats.fireMode == FireMode.CHARGE ? UseAnim.BOW : UseAnim.NONE;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (stats.fireMode == FireMode.SEMI) {
            if (!level.isClientSide) {
                fire(level, player, stack);
            }
            player.getCooldowns().addCooldown(this, stats.fireRateTicks);
            return InteractionResultHolder.success(stack);
        }

        // AUTO and CHARGE both ride the "using item" tick loop below
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    public static void registerAutoFireTick() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                if (!player.isUsingItem()) continue;

                ItemStack using = player.getUseItem();
                if (!(using.getItem() instanceof RangedWeaponItem weapon)) continue;
                if (weapon.stats.fireMode != FireMode.AUTO) continue;

                int elapsed = weapon.getUseDuration(using) - player.getUseItemRemainingTicks();
                if (elapsed > 0 && elapsed % weapon.stats.fireRateTicks == 0) {
                    weapon.fire(player.level(), player, using);
                }
            }
        });
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int remainingUseDuration) {
        if (stats.fireMode != FireMode.CHARGE) return;
        if (level.isClientSide) return;
        if (!(livingEntity instanceof Player player)) return;

        int elapsed = getUseDuration(stack) - remainingUseDuration;
        if (elapsed >= stats.fireRateTicks) { // full charge required — a partial charge does nothing, per your note
            fire(level, player, stack);
        }
    }

    protected void playFireSound(Level level, Player player, SoundEvent sound) {
        level.playSound(null, player.getX(), player.getY(), player.getZ(), sound, player.getSoundSource(), 1.0F, 1.0F);
    }
}
