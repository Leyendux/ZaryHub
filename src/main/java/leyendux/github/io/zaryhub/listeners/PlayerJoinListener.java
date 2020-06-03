package leyendux.github.io.zaryhub.listeners;

import leyendux.github.io.zaryhub.Main;
import leyendux.github.io.zaryhub.util.MethodUtils;
import leyendux.github.io.zaryhub.util.ValuesUtil;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        event.setJoinMessage(null);

        Player player = event.getPlayer();

        player.teleport(Main.getInstance().getSpawnLocation());
        player.getInventory().clear();
        player.setGameMode(GameMode.SURVIVAL);
        player.setExp(0);
        player.setHealth(20);
        player.setFoodLevel(20);

        player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 0.5f, 0.5f);
        player.sendMessage(ValuesUtil.PREFIX.getValue() + "§bHi, " + player.getDisplayName());
        player.sendMessage(" §8» §fFollow us on twitter: §b@ZaryNetwork");
        player.sendMessage(" §8» §fJoin our discord: §bdiscord.zarynetwork.net");

        player.getInventory().setItem(4, MethodUtils.createItemStack(Material.COMPASS, "§3§lNavigation", " §8» §7Right click to use"));
    }
}
