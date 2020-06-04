package leyendux.github.io.zaryhub.listeners;

import leyendux.github.io.zaryhub.util.MethodUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SpawnListener implements Listener {

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChangeEvent(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPickupItemEvent(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDamageEvent(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntitySpawnEvent(CreatureSpawnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerLossFoodEvent(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInventoryClickEvent(InventoryClickEvent event) {
        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();

        Inventory inv = event.getClickedInventory();
        ItemStack itemStack = event.getCurrentItem();
        Material item = event.getCurrentItem().getType();

        if(inv.getName().contains("Navigation")) {
            switch(item) {
                case GOLDEN_APPLE:
                    player.closeInventory();
                    player.openInventory(MethodUtils.uhcInventory("§3§lUHC", 27));
                    break;
                case PAPER:
                    player.closeInventory();
                    break;
                default:
                    break;
            }
        }
        if(inv.getName().contains("UHC")) {
            switch (item) {
                case GOLDEN_APPLE:
                    if(itemStack.getItemMeta().getDisplayName().contains("North America")) {
                        player.closeInventory();
                        MethodUtils.sendMessageToBungee("Connect", "NA-UHC", player);
                    } else {
                        player.closeInventory();
                        player.sendMessage("§cWorking on it...");
                    }
                    break;
                case PAPER:
                    player.closeInventory();
                    player.openInventory(MethodUtils.hubInventory("§3§lNavigation", 27));
            }
        }
    }


    @EventHandler
    public void onPlayerInteractItemEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Material material = player.getItemInHand().getType();

        event.setCancelled(true);

        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(material.equals(Material.COMPASS)) {
                player.openInventory(MethodUtils.hubInventory("§3§lNavigation", 27));
            }
        }
    }
}
