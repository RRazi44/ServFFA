package fr.razi.ffagame.listeners;

import fr.razi.ffagame.FFAService;
import fr.razi.ffagame.utils.ItemManager;
import fr.razi.ffagame.utils.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerJoinFFAListener extends BaseFFAListener {

    public PlayerJoinFFAListener(FFAService ffa) {
        super(ffa);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(event.getClickedInventory() == null
                || event.getCurrentItem() == null
                || event.getWhoClicked() == null
                || !(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();
        if(isInFFA(player.getWorld())) return;

        if(ItemManager.isLobbyAxe(event.getCurrentItem())){
            event.setCancelled(true);
            PlayerManager.sendToFFA(player);
        }

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(isInFFA(event.getPlayer().getWorld())) return;
        if(event.getItem() == null || event.getPlayer() == null) return;
        if(event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if(ItemManager.isLobbyAxe(event.getItem())){
            event.setCancelled(true);
            PlayerManager.sendToFFA(event.getPlayer());
        }
    }

}
