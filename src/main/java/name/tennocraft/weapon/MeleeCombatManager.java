package name.tennocraft.weapon;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MeleeCombatManager {
    private static final Map<UUID, Integer> WINDUP_REMAINING = new HashMap<>();
    private static final Map<UUID, Boolean> HOLDING = new HashMap<>();

    public static void onFireStart(ServerPlayer player) {
        if (!(player.getMainHandItem().getItem() instanceof MeleeWeaponItem)) return;
        HOLDING.put(player.getUUID(), true);
        WINDUP_REMAINING.putIfAbsent(player.getUUID(), currentAttackSpeed(player));
    }

    public static void onFireStop(ServerPlayer player) {
        HOLDING.put(player.getUUID(), false);
    }

    public static void registerServerTick() {
        net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                UUID id = player.getUUID();
                if (!HOLDING.getOrDefault(id, false)) continue;

                ItemStack held = player.getMainHandItem();
                if (!(held.getItem() instanceof MeleeWeaponItem weapon)) {
                    HOLDING.put(id, false);
                    continue;
                }

                int remaining = WINDUP_REMAINING.getOrDefault(id, weapon.stats.attackSpeedTicks) - 1;
                if (remaining <= 0) {
                    weapon.performAttack(player);
                    remaining = weapon.stats.attackSpeedTicks; // next windup starts immediately — this IS the cooldown
                }
                WINDUP_REMAINING.put(id, remaining);
            }
        });

        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            UUID id = handler.player.getUUID();
            WINDUP_REMAINING.remove(id);
            HOLDING.remove(id);
        });
    }

    private static int currentAttackSpeed(ServerPlayer player) {
        return player.getMainHandItem().getItem() instanceof MeleeWeaponItem weapon ? weapon.stats.attackSpeedTicks : 20;
    }
}