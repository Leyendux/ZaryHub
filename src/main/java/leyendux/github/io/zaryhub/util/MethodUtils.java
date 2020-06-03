package leyendux.github.io.zaryhub.util;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import leyendux.github.io.zaryhub.Main;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class MethodUtils {

    public static void sendMessageToBungee(String channelName, String content, Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();

        out.writeUTF(channelName);
        out.writeUTF(content);

        player.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
    }

    public static int getPing(Player player) {
        return ((CraftPlayer)player).getHandle().ping;
    }
}
