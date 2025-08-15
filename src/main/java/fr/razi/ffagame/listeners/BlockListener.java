package fr.razi.ffagame.listeners;

import fr.razi.ffagame.FFAGame;
import org.bukkit.GameMode;
import org.bukkit.Material;

import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Iterator;
import java.util.List;

public class BlockListener implements Listener {

    private final FFAGame plugin;

    public BlockListener(FFAGame plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBreakBlock(BlockBreakEvent event) {
        if(event.getPlayer().getWorld() == plugin.getWorldManager().getLobby()){
            event.setCancelled(true);
            return;
        }

        boolean protect = event.getPlayer().getGameMode() == GameMode.SURVIVAL
                && !(event.getBlock().getType() == Material.SANDSTONE
                || event.getBlock().getType() == Material.TNT);
        event.setCancelled(protect);

        if (event.getBlock().getType() == Material.SANDSTONE){
            plugin.getBreakTask().unregisterBlock(event.getBlock().getLocation());
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlaceBlock(BlockPlaceEvent event){
        if(event.getBlock().getWorld() == plugin.getWorldManager().getLobby()) return;
        if(event.getBlock().getType() != Material.SANDSTONE) return;
        plugin.getBreakTask().registerBlock(event.getBlock().getLocation());
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockExplode(BlockExplodeEvent event){
        spawnTnT(event.blockList());
        removeOnlySandstone(event.blockList());
    }

    private void spawnTnT(List<Block> blocks) {
        Iterator<Block> it = blocks.iterator();
        while (it.hasNext()) {
            Block b = it.next();
            if (b.getType() == Material.TNT) {
                b.setType(Material.AIR, false);
                TNTPrimed primed = (TNTPrimed) b.getWorld().spawnEntity(
                        b.getLocation().add(0.5, 0, 0.5),
                        EntityType.PRIMED_TNT
                );
                primed.setFuseTicks(40);
                it.remove();
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityExplode(EntityExplodeEvent event){
        spawnTnT(event.blockList());
        removeOnlySandstone(event.blockList());
    }

    private void removeOnlySandstone(List<Block> blocks) {
        blocks.removeIf(block -> block.getType() != Material.SANDSTONE);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFlame(PlayerInteractEvent event){
        if(event.getItem() != null && event.getItem().getType() == Material.FLINT_AND_STEEL){
            if(event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() != Material.TNT){
                event.setCancelled(true);
            }
        }
    }

}
