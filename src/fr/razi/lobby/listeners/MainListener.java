package fr.razi.lobby.listeners;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import fr.razi.lobby.Main;

public class MainListener implements Listener {

	private final Main main;
	public MainListener(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {

        event.setJoinMessage("");

		Player player = event.getPlayer();

        ArrayList<String> lore = new ArrayList<>();

        lore.add("§6Si vous faites clique droit avec cette hache dans la main, vous rejoignez le combat !");

        ItemStack it = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta itM = it.getItemMeta();

        itM.setDisplayName("§6Rejoigner le combat !");
        itM.setLore(lore);

        player.getInventory().clear();

        player.getInventory().getBoots().setType(Material.AIR);
        player.getInventory().getLeggings().setType(Material.AIR);
        player.getInventory().getChestplate().setType(Material.AIR);
        player.getInventory().getHelmet().setType(Material.AIR);

        player.setLevel(0);
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.setHealthScale(20);
		player.setGameMode(GameMode.SURVIVAL);
        player.setFlying(false);

        player.sendMessage("§8[§6FFAGames§8]§f " + "§6Bienvenue sur §66FFAGames !");

		event.setJoinMessage("");

		player.teleport(main.getLobby());

	}


	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		
		if(event.getEntity() instanceof Player player) {
            if(player.getLocation().getWorld() == Bukkit.getWorld("ffarush")) {
				event.setCancelled(true);
			}
		}		
	}
	
	
	@EventHandler
	public void onPvP(EntityDamageByEntityEvent event){
		if(event.getEntity() instanceof Player player) {

            if(player.getWorld() == Bukkit.getWorld("ffarush")){
				event.setCancelled(true);
			}
			if(event.getCause() == DamageCause.BLOCK_EXPLOSION || event.getCause() == DamageCause.ENTITY_EXPLOSION) {
				event.setDamage(0);
			}
		}
	}


	@EventHandler
	public void onFall(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {			
			if(event.getCause() == DamageCause.FALL || event.getCause() == DamageCause.FALLING_BLOCK) {
				event.setCancelled(true);
			} if(event.getCause() == DamageCause.BLOCK_EXPLOSION || event.getCause() == DamageCause.ENTITY_EXPLOSION) {
				event.setDamage(0);
			}
		}
	}


	@EventHandler
	public void onVelocity(PlayerVelocityEvent event) {
		
		Player player = event.getPlayer();
		if(player.getLastDamageCause() != null ) {
			if(player.getLastDamageCause().getCause() == DamageCause.ENTITY_EXPLOSION) {
				double yvelo = event.getVelocity().getY();
				event.getVelocity();
				
				Vector.getRandom().setX(0);
				Vector.getRandom().setZ(0);
				Vector.getRandom().setY(0);
				
				event.getVelocity().multiply(10);
				event.getVelocity().setY(yvelo * 5);
			}
		}
	}


	@EventHandler
	public void onBreakBlock(BlockBreakEvent event) {
		if(event.getPlayer().getWorld() == Bukkit.getWorld("ffarush")) {
			event.setCancelled(true);
		}
	}


	@EventHandler
    public void onExplode(EntityExplodeEvent event) {

        Iterator<Block> iterator = event.blockList().iterator();
        ArrayList<Material> incassable = new ArrayList<>();
        
        incassable.add(Material.SANDSTONE);
        incassable.add(Material.FENCE);
        incassable.add(Material.REDSTONE_BLOCK);
        incassable.add(Material.GOLD_BLOCK);
        
        while (iterator.hasNext()) {
            Block block = iterator.next();
            if (incassable.contains(block.getType())) {
                iterator.remove(); 
            }
            else if(block.getType() == Material.TNT) {
            	iterator.remove();
            	Location Loctnt = block.getLocation();
            	Loctnt.getBlock().setType(Material.AIR);
            	
            	double x = Loctnt.getX();
            	x=x+0.5;
            	double z = Loctnt.getZ();
            	z=z+0.5;
            	Loctnt.setX(x);
            	Loctnt.setZ(z);
            	
            	TNTPrimed tnt = (TNTPrimed) Loctnt.getWorld().spawnEntity(Loctnt, EntityType.PRIMED_TNT);
            	tnt.setFuseTicks(80);
            	tnt.setYield(4.0f);
            	tnt.setVelocity(new Vector(0,0,0));
            }
        }
    }
}















