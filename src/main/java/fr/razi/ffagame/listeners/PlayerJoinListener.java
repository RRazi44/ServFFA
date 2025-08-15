package fr.razi.ffagame.listeners;

import fr.razi.ffagame.FFAGame;
import fr.razi.ffagame.FFAService;
import fr.razi.ffagame.utils.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener extends BaseFFAListener {

    public PlayerJoinListener(FFAService ffa) {
        super(ffa);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        PlayerManager.sendToLobby(player);
        event.setJoinMessage(FFAGame.PREFIX + "Bienvenue sur le serveur !");
    }

}
