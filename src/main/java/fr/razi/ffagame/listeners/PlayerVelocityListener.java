package fr.razi.ffagame.listeners;

import fr.razi.ffagame.FFAService;
import fr.razi.ffagame.utils.KnockBackManager;
import fr.razi.ffagame.utils.WorldManager;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.util.Vector;

public class PlayerVelocityListener extends BaseFFAListener{

    private final KnockBackManager knockBackManager;

    public PlayerVelocityListener(FFAService ffa, KnockBackManager knockBackManager) {
        super(ffa);
        this.knockBackManager = knockBackManager;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onTntExplosionKnockback(PlayerVelocityEvent event) {

        final Player player = event.getPlayer();

        if (event.getPlayer().getWorld() != WorldManager.worldFFA) return;

        final EntityDamageEvent lastDamage = player.getLastDamageCause();
        if(!(lastDamage instanceof EntityDamageByEntityEvent)) return;

        final Entity damager = ((EntityDamageByEntityEvent) lastDamage).getDamager();
        if (!(damager instanceof TNTPrimed)) return;

        final Location tntLocation = damager.getLocation();
        final Location playerLocation = player.getLocation();

        Vector velocity = knockBackManager.calculateKnockback(tntLocation, playerLocation);
        event.setVelocity(velocity);


    }

}
