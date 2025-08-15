package fr.razi.ffagame.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class WorldManager {

    public static Location locLobby;

    private World worldLobby;
    private World worldFFA;

    public static World loadWorld(String name){
        return new WorldCreator(name).createWorld();
    }

    public void initWorlds() {
        worldLobby = loadWorld("lobby");
        worldFFA = loadWorld("ffa");

        locLobby = new Location(worldLobby, 0.5, 102, 0.5);
    }

    public boolean isInFFA(World world){
        return world.getName().equals(worldFFA.getName());
    }

    public World getLobby() {
        return worldLobby;
    }

    public World getWorldFFA() {
        return worldFFA;
    }

}
