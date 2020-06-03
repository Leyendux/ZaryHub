package leyendux.github.io.zaryhub.scoreboard;

import io.github.thatkawaiisam.assemble.AssembleAdapter;
import leyendux.github.io.zaryhub.Main;
import leyendux.github.io.zaryhub.data.BungeeData;
import leyendux.github.io.zaryhub.util.MethodUtils;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ScoreboardManager implements AssembleAdapter {

    private String lines = "&8&m---------------------";

    public String getTitle(Player player) {
        return "§3§lZaryNetwork §8(§f§l" + Main.getInstance().getRegion() + "§8)";
    }

    public List<String> getLines(Player player) {
        List<String> scoreLines = new LinkedList<String>();
        scoreLines.add(lines);
        scoreLines.add("§bYou: §f" + player.getName());
        scoreLines.add("§bYour ping: §f" + MethodUtils.getPing(player) + "ms");
        scoreLines.add(" ");
        scoreLines.add("§bTotal Online: §f" + BungeeData.getPlayerCount("ALL"));
        scoreLines.add(" ");
        scoreLines.add(" §8» §bHub: §f" + BungeeData.getPlayerCount("lobby"));
        scoreLines.add(" §8» §bUHC: §f" + BungeeData.getPlayerCount("uhc"));
        scoreLines.add(lines);
        String[] lastLine = {"discord.zarynetwork.net", "@ZaryNetwork", "hub.zarynetwork.net"};
        scoreLines.add("§b" + lastLine[new Random().nextInt(lastLine.length)]);
        return scoreLines;
    }
}
