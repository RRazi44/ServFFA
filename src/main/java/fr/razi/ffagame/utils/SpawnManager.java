package fr.razi.ffagame.utils;

import fr.razi.ffagame.FFAGame;
import org.bukkit.Location;
import org.bukkit.World;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpawnManager {

    private final List<Location> spawns = new ArrayList<>(20);
    private final Random random = new Random();

    public SpawnManager() {

    }

    public Location getRandomSpawn() {
        return spawns.get(random.nextInt(spawns.size()));
    }

    public void loadSpawns(FFAGame plugin) {
        World world = plugin.getWorldManager().getWorldFFA();

        int y = 101;
        double radius = 48.5;
        double[] offsets = {-38.5, -19.5, 0.5, 19.5, 38.5};

        for (double z : offsets) spawns.add(new Location(world,  radius, y, z,  90f, 0f));
        for (double z : offsets) spawns.add(new Location(world, -radius, y, z, -90f, 0f));
        for (double x : offsets) spawns.add(new Location(world, x, y, -radius,  0f, 0f));
        for (double x : offsets) spawns.add(new Location(world, x, y,  radius, 180f, 0f));

    }
}
