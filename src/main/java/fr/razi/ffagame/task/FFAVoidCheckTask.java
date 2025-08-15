package fr.razi.ffagame.task;

import fr.razi.ffagame.utils.PlayerManager;
import fr.razi.ffagame.utils.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class FFAVoidCheckTask extends BukkitRunnable {

    private static int timer = 0;

    @Override
    public void run() {
        if(timer%5==0){
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getWorld() == WorldManager.worldFFA && player.getLocation().getY() < 50) {
                    PlayerManager.sendToLobby(player);
                }
            }
        }

        timer++;
    }
}
