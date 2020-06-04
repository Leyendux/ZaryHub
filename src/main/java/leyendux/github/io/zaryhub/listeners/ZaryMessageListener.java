package leyendux.github.io.zaryhub.listeners;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import leyendux.github.io.zaryhub.util.MethodUtils;
import leyendux.github.io.zaryhub.util.ValuesUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class ZaryMessageListener implements PluginMessageListener {


    public void onPluginMessageReceived(String channel, Player player, byte[] data) {
        if(!channel.equals("ZaryCore")) return;

        ByteArrayDataInput in = ByteStreams.newDataInput(data);
        String subChannel = in.readUTF();
        if(subChannel.equals("LuckPerms")) {
            String target = in.readUTF();
            String rank = in.readUTF();

            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + target + " parent set "+ rank);
            MethodUtils.sendMessageToBungee("Message", "ALL", ValuesUtil.PREFIX.getValue() + "§b" + target + " has been moved to §e" + rank, player);
        }
    }
}
