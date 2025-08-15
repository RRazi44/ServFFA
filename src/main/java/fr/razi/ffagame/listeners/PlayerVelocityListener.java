package fr.razi.ffagame.listeners;

import fr.razi.ffagame.FFAGame;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.util.Vector;

public class PlayerVelocityListener implements Listener {

    private final FFAGame plugin;

    public PlayerVelocityListener(FFAGame plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onTntExplosionKnockback(PlayerVelocityEvent event) {

        final Player player = event.getPlayer();

        if (event.getPlayer().getWorld() != plugin.getWorldManager().getWorldFFA()) return;

        final EntityDamageEvent lastDamage = player.getLastDamageCause();
        if(!(lastDamage instanceof EntityDamageByEntityEvent)) return;

        final Entity damager = ((EntityDamageByEntityEvent) lastDamage).getDamager();
        if (!(damager instanceof TNTPrimed)) return;

        final Location tntLocation = damager.getLocation();
        final Location playerLocation = player.getLocation();

        Vector velocity = plugin.getKnockBackManager().calculateKnockback(tntLocation, playerLocation);
        event.setVelocity(velocity);


    }

}
