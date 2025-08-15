package fr.razi.ffagame.listeners;

import fr.razi.ffagame.FFAService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDeathListener extends BaseFFAListener {

    public PlayerDeathListener(FFAService ffa){
        super(ffa);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeath(EntityDamageEvent event){
        if(!(event instanceof Player)) return;
        Player player = (Player) event.getEntity();


    }


}
