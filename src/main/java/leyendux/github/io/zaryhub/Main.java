package leyendux.github.io.zaryhub;

import io.github.thatkawaiisam.assemble.Assemble;
import io.github.thatkawaiisam.assemble.AssembleStyle;
import leyendux.github.io.zaryhub.data.BungeeData;
import leyendux.github.io.zaryhub.listeners.BoardCreatedListener;
import leyendux.github.io.zaryhub.listeners.BungeeMessageListener;
import leyendux.github.io.zaryhub.listeners.PlayerJoinListener;
import leyendux.github.io.zaryhub.listeners.PlayerQuitListener;
import leyendux.github.io.zaryhub.scoreboard.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    private Assemble scoreboard;
    private String region;

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;

        checkRegion();

        BungeeData.setup();

        scoreboard = new Assemble(this, new ScoreboardManager());
        scoreboard.setTicks(20);
        scoreboard.setAssembleStyle(AssembleStyle.MODERN);

        registerChannels();
        registerListeners();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        instance = null;
    }

    public static Main getInstance() {
        return instance;
    }

    private void checkRegion() {
        region = Bukkit.getServerName().contains("SA") ? "SA" : "NA";
    }

    public String getRegion() {
        return region;
    }

    public Assemble getScoreboard() {
        return scoreboard;
    }

    private void registerChannels() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeeMessageListener());
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new BoardCreatedListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), this);
    }
}
