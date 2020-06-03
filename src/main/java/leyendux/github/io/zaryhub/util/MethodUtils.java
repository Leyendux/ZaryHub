package leyendux.github.io.zaryhub.util;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import leyendux.github.io.zaryhub.Main;
import leyendux.github.io.zaryhub.data.BungeeData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

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

    public static ItemStack createItemStack(Material material, String displayName, String... lore) {
        ItemStack is = new ItemStack(material, 1);
        ItemMeta im = is.getItemMeta();

        im.setDisplayName(displayName);
        im.setLore(Arrays.asList(lore));

        is.setItemMeta(im);
        return is;
    }

    public static Inventory hubInventory(String name, int slots) {
        Inventory inv = Bukkit.createInventory(null, slots, name);

        inv.setItem(11, createItemStack(Material.GOLDEN_APPLE, "§b§lUHC Servers", "§8» §71.7x - 1.8x", "§8» §7NA - SA", "§8» §7Players Online: §b" + BungeeData.getPlayerCount(Main.getInstance().getRegion() + "-UHC")));
        inv.setItem(15, createItemStack(Material.BEDROCK, "§cHmm...", "§8» §bdiscord.zarynetwork.net", "§8» §b@ZaryNetwork"));
        inv.setItem(18, createItemStack(Material.PAPER, "§cClose Menu"));

        return inv;
    }
}
