package leyendux.github.io.zaryhub.data;

import com.google.common.collect.Iterables;
import leyendux.github.io.zaryhub.Main;
import leyendux.github.io.zaryhub.util.MethodUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class BungeeData {

    private static Map<String, Integer> dataMap = new HashMap<String, Integer>();
    private static String[] servers = {"ALL", "NA-HUB", "NA-UHC"};

    public static void setup() {
        for(String server : servers) {
            dataMap.put(server, 0);
        }

        startCounting();
    }

    public static void setPlayerCount(String server, int count) {
        dataMap.put(server, count);
    }

    public static int getPlayerCount(String server) {
        return dataMap.get(server);
    }

    private static void startCounting() {
        new BukkitRunnable() {
            public void run() {
                if(Bukkit.getOnlinePlayers().size() > 0) {
                    Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
                    for(String server : servers) {
                        MethodUtils.sendMessageToBungee("PlayerCount", server, player);
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 0L, 20L);
    }
}
