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

	private FFAService ffaService;
    private ItemManager itemManager;
	private KnockBackManager knockBackManager;

    public SpawnManager spawnManager;
	public BreakBlockTask breakTask;
	public FFAVoidCheckTask voidTask;

	@Override
	public void onEnable() {

		itemManager = new ItemManager();
        ConfigManager configManager = new ConfigManager(this);
		ffaService = new FFAService();
		knockBackManager = new KnockBackManager(configManager);
        spawnManager = new SpawnManager();

		getCommand("lobby").setExecutor(new commandLobby());

		getCommand("world").setExecutor(new commandWorld());
		getCommand("world").setTabCompleter(new WorldTabCompleter());

		getServer().getScheduler().runTask(this, () -> {
			WorldManager.initWorlds();
			itemManager.loadItems();
            spawnManager.loadSpawns();
		});

		registerEvents(getServer().getPluginManager());

		voidTask = new FFAVoidCheckTask();
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
		pm.registerEvents(new BlockListener(ffaService, this), this);
		pm.registerEvents(new PlayerDamageListener(ffaService), this);
		pm.registerEvents(new PlayerJoinListener(ffaService), this);
        pm.registerEvents(new PlayerJoinFFAListener(ffaService), this);
        pm.registerEvents(new PlayerVelocityListener(ffaService, knockBackManager), this);
	}

	public BreakBlockTask getBreakTask() {
		return breakTask;
	}

	public void consoleInfo(String msg)  { Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN  + PREFIX + msg); }
	public void consoleWarn(String msg)  { Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + PREFIX + msg); }
	public void consoleError(String msg) { Bukkit.getConsoleSender().sendMessage(ChatColor.RED    + PREFIX + msg); }

}