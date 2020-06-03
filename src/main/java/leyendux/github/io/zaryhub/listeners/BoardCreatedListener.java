package leyendux.github.io.zaryhub.listeners;

import io.github.thatkawaiisam.assemble.AssembleBoard;
import io.github.thatkawaiisam.assemble.events.AssembleBoardCreatedEvent;
import leyendux.github.io.zaryhub.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class BoardCreatedListener implements Listener {

    @EventHandler
    public void onBoardCreatedEvent(AssembleBoardCreatedEvent event) {
        AssembleBoard board = event.getBoard();

        final Player player = Bukkit.getPlayer(board.getUuid());

        final Scoreboard scoreboard = board.getScoreboard();

        Team team = scoreboard.registerNewTeam("§a" + player.getName());
        team.setPrefix("§a");
        team.addPlayer(player);

        new BukkitRunnable() {
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()) {
                    if(p != player) {
                        Team pTeam = scoreboard.getTeam("§c" + p.getName());
                        if(pTeam == null) {
                            pTeam = scoreboard.registerNewTeam("§c" + p.getName());
                        }
                        pTeam.setPrefix("§c");
                        pTeam.addPlayer(p);
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 0L, 20L);
    }
}
