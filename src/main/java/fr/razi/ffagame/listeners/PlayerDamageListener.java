package fr.razi.ffagame.listeners;

import fr.razi.ffagame.FFAService;

import fr.razi.ffagame.utils.PlayerManager;
import fr.razi.ffagame.utils.WorldManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageListener extends BaseFFAListener {

    public PlayerDamageListener(FFAService ffa) {
        super(ffa);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerDamage(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        if (player.getWorld() != WorldManager.worldFFA){
            event.setCancelled(true);
            return;
        }
        switch (event.getCause()){
            case BLOCK_EXPLOSION:
            case ENTITY_EXPLOSION:
                event.setDamage(1);
                return;
            case FALL:
                event.setCancelled(true);
                return;
            default:
        }

        if(player.getHealth() <= event.getDamage()){
            event.setCancelled(true);
            PlayerManager.sendToLobby(player);
        }

    }

}
