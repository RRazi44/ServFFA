package fr.razi.ffagame.listeners;

import fr.razi.ffagame.FFAGame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerJoinFFAListener implements Listener {

    private final FFAGame plugin;

    public PlayerJoinFFAListener(FFAGame main) {
        plugin = main;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(event.getClickedInventory() == null
                || event.getCurrentItem() == null
                || event.getWhoClicked() == null
                || !(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();
        if(plugin.getWorldManager().isInFFA(player.getWorld())) return;

        if(plugin.getItemManager().isLobbyAxe(event.getCurrentItem())){
            event.setCancelled(true);
            plugin.getPlayerManager().sendToFFA(player, plugin);
        }

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(plugin.getWorldManager().isInFFA(event.getPlayer().getWorld())) return;
        if(event.getItem() == null || event.getPlayer() == null) return;
        if(event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if(plugin.getItemManager().isLobbyAxe(event.getItem())){
            event.setCancelled(true);
            plugin.getPlayerManager().sendToFFA(event.getPlayer(), plugin);
        }
    }

}
