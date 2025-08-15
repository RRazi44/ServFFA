package fr.razi.ffagame;

import fr.razi.ffagame.commands.commandWorld;
import fr.razi.ffagame.commands.tabs.WorldTabCompleter;
import fr.razi.ffagame.listeners.*;
import fr.razi.ffagame.task.BreakBlockTask;
import fr.razi.ffagame.task.FFAVoidCheckTask;
import fr.razi.ffagame.utils.*;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.razi.ffagame.commands.commandLobby;

public class FFAGame extends JavaPlugin {

	public static final String PREFIX = "§8[§6FFAGames§8]§f ";

    private ItemManager itemManager;
	private KnockBackManager knockBackManager;

	private PlayerManager playerManager;
    private SpawnManager spawnManager;
	private BreakBlockTask breakTask;
	private FFAVoidCheckTask voidTask;
	private WorldManager worldManager;

    @Override
	public void onEnable() {

		ConfigManager configManager = new ConfigManager(this);
		itemManager = new ItemManager();
		knockBackManager = new KnockBackManager(configManager);
        spawnManager = new SpawnManager();
		playerManager = new PlayerManager();
		worldManager = new WorldManager();

		getCommand("lobby").setExecutor(new commandLobby(this));

		getCommand("world").setExecutor(new commandWorld());
		getCommand("world").setTabCompleter(new WorldTabCompleter());

		getServer().getScheduler().runTask(this, () -> {
			worldManager.initWorlds();
			itemManager.loadItems();
            spawnManager.loadSpawns(this);
		});

		registerEvents(getServer().getPluginManager());

		voidTask = new FFAVoidCheckTask(this);
		voidTask.runTaskTimer(this, 0L, 5L);

		breakTask = new BreakBlockTask(configManager.getBlockBreakDelay());
		breakTask.runTaskTimer(this, 0L, 5L);

		saveDefaultConfig();

		consoleInfo("Plugin Enabled.");
	}

	@Override
	public void onDisable(){
		if(breakTask != null) breakTask.cancel();
		if(voidTask != null) voidTask.cancel();
		consoleInfo("Plugin Disabled.");
	}


	private void registerEvents(PluginManager pm) {
		pm.registerEvents(new BlockListener(this), this);
		pm.registerEvents(new PlayerDamageListener(this), this);
		pm.registerEvents(new PlayerJoinListener(this), this);
        pm.registerEvents(new PlayerJoinFFAListener(this), this);
        pm.registerEvents(new PlayerVelocityListener(this), this);
	}

	public BreakBlockTask getBreakTask() {
		return breakTask;
	}

	public SpawnManager getSpawnManager() {
		return spawnManager;
	}

	public PlayerManager getPlayerManager() {
		return playerManager;
	}

	public WorldManager getWorldManager() {
		return worldManager;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public KnockBackManager getKnockBackManager() {
		return knockBackManager;
	}




	public void consoleInfo(String msg)  { Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN  + PREFIX + msg); }
	public void consoleWarn(String msg)  { Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + PREFIX + msg); }
	public void consoleError(String msg) { Bukkit.getConsoleSender().sendMessage(ChatColor.RED    + PREFIX + msg); }


}