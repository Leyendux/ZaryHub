package leyendux.github.io.zaryhub.listeners;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import leyendux.github.io.zaryhub.data.BungeeData;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class BungeeMessageListener implements PluginMessageListener {

    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if(!channel.equals("BungeeCord")) return;

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subChannel = in.readUTF();
        if(subChannel.equals("PlayerCount")) {
            String server = in.readUTF();
            int online = in.readInt();

            BungeeData.setPlayerCount(server, online);
        }
    }
}
