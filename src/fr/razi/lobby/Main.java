package fr.razi.lobby;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.plugin.java.JavaPlugin;

import fr.razi.lobby.commands.commandChangeWorld;
import fr.razi.lobby.commands.commandLobby;
import fr.razi.lobby.listeners.MainListener;

public class Main extends JavaPlugin {

    private Location spawn;
	private Location LocFFARush;
	
	@Override
	public void onEnable() {
        World lobby = Bukkit.getWorld("lobby");
	    createLobby();
	    if (lobby == null) {
	        lobby = Bukkit.getWorld("lobby");
	    }

        World ffarushworld = Bukkit.getWorld("ffarush");
	    if (ffarushworld == null) {
	        WorldCreator wc = new WorldCreator("ffarush");
	        wc.type(WorldType.NORMAL);
	        ffarushworld = wc.createWorld();
	    }

	    this.spawn = new Location(lobby, 0, 104, 0);
	    this.LocFFARush = new Location(ffarushworld, 0, 104, 0);

	    getCommand("lobby").setExecutor(new commandLobby(this));
	    getCommand("changeworld").setExecutor(new commandChangeWorld(this));

	    getServer().getPluginManager().registerEvents(new MainListener(this), this);

	    System.out.println("Lobby world: " + lobby);
	    System.out.println("FFARUSH world: " + ffarushworld);
	    
	}

	private void createLobby() {
	    WorldCreator wc = new WorldCreator("lobby");
	    wc.type(WorldType.FLAT);
	    wc.generateStructures(false);
	    wc.generatorSettings("2;0;1;");
	    wc.createWorld();
	}


	@Override
	public void onDisable() {

	}


	public Location getLobby() {
		return this.spawn;
	}


	public Location getFFARUSH() {
		return this.LocFFARush;
	}
	
}
