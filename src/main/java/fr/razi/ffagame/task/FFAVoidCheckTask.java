package fr.razi.ffagame.task;

import fr.razi.ffagame.FFAGame;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class FFAVoidCheckTask extends BukkitRunnable {

    private static int timer = 0;
    private final FFAGame plugin;

    public FFAVoidCheckTask(FFAGame plugin){
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if(timer%5==0){
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getWorld() == plugin.getWorldManager().getWorldFFA() && player.getLocation().getY() < 50) {
                    plugin.getPlayerManager().sendToLobby(player, plugin);
                }
            }
        }

        timer++;
    }
}
