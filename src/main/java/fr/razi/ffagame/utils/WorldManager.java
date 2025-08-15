package fr.razi.ffagame.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class WorldManager {

    public static Location locLobby;

    public static World worldLobby;
    public static World worldFFA;

    public static World loadWorld(String name){
        return new WorldCreator(name).createWorld();
    }

    public static void initWorlds() {
        worldLobby = loadWorld("lobby");
        worldFFA = loadWorld("ffa");

        locLobby = new Location(worldLobby, 0.5, 102, 0.5);
    }

}
