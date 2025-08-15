package fr.razi.ffagame.utils;

import fr.razi.ffagame.FFAGame;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private final FFAGame plugin;
    private FileConfiguration config;

    private static double tntHorizontal;
    private static double tntVertical;
    private static int blockBreakDelay;

    public ConfigManager(FFAGame plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        load();
    }

    public void load(){
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        this.config = plugin.getConfig();

        tntHorizontal = config.getDouble("tnt-knockback.horizontal-multiplier", 4.0);
        tntVertical = config.getDouble("tnt-knockback.vertical-velocity", 3.0);
        blockBreakDelay = config.getInt("block-break-delay", 100);
    }

    public double getTntHorizontal(){
        return tntHorizontal;
    }

    public double getTntVertical(){
        return tntVertical;
    }

    public int getBlockBreakDelay(){
        return blockBreakDelay;
    }

    public void reload(){
        load();
    }

}
