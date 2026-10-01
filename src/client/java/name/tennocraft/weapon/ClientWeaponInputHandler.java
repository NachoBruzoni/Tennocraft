package name.tennocraft.weapon;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.Minecraft;

public class ClientWeaponInputHandler {
    private static boolean wasDown = false;

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            boolean isRangedWeapon = client.player.getMainHandItem().getItem() instanceof RangedWeaponItem;
            boolean isDown = isRangedWeapon && client.options.keyAttack.isDown();

            if (isDown && !wasDown) {
                ClientPlayNetworking.send(WeaponFirePacket.START, PacketByteBufs.create());
            } else if (!isDown && wasDown) {
                ClientPlayNetworking.send(WeaponFirePacket.STOP, PacketByteBufs.create());
            }
            wasDown = isDown;

            if (isRangedWeapon) {
                client.options.keyAttack.setDown(false); // consume the press so vanilla doesn't ALSO mine/attack this tick
            }
        });
    }
}