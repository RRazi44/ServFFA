package fr.razi.ffagame.listeners;

import fr.razi.ffagame.FFAGame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final FFAGame plugin;
    public PlayerJoinListener(FFAGame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        plugin.getPlayerManager().sendToLobby(player, plugin);
        event.setJoinMessage(FFAGame.PREFIX + "Bienvenue sur le serveur !");
    }

}
