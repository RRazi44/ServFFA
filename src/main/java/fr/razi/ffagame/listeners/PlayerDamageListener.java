package fr.razi.ffagame.listeners;

import fr.razi.ffagame.FFAGame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageListener implements Listener {

    private final FFAGame plugin;
    public PlayerDamageListener(FFAGame plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerDamage(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        if (player.getWorld() != plugin.getWorldManager().getWorldFFA()){
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
            plugin.getPlayerManager().sendToLobby(player, plugin);
        }

    }

}
