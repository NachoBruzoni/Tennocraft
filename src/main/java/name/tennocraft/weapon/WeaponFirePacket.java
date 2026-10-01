package name.tennocraft.weapon;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

import name.tennocraft.TennoCraft;

public class WeaponFirePacket {
    public static final ResourceLocation START = new ResourceLocation(TennoCraft.TENNOCRAFT, "weapon_fire_start");
    public static final ResourceLocation STOP = new ResourceLocation(TennoCraft.TENNOCRAFT, "weapon_fire_stop");

    public static void registerServerReceivers() {
        ServerPlayNetworking.registerGlobalReceiver(START, (server, player, handler, buf, responseSender) -> {
            server.execute(() -> {
                ItemStack held = player.getMainHandItem();
                if (!(held.getItem() instanceof RangedWeaponItem weapon)) return;

                if (weapon.stats.fireMode == FireMode.SEMI) {
                    weapon.fire(player.level(), player, held);
                    player.getCooldowns().addCooldown(weapon, weapon.stats.fireRateTicks);
                } else {
                    player.startUsingItem(InteractionHand.MAIN_HAND);
                }
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(STOP, (server, player, handler, buf, responseSender) -> {
            server.execute(player::stopUsingItem);
        });
    }
}