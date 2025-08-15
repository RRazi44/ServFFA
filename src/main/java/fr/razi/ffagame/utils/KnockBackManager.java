package fr.razi.ffagame.utils;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class KnockBackManager {

    private final double tntHorizontal;
    private final double tntVertical;

    public KnockBackManager(ConfigManager config){
        this.tntHorizontal = config.getTntHorizontal();
        this.tntVertical = config.getTntVertical();
    }

    public Vector calculateKnockback(Location tntLoc, Location playerLoc) {
        final double EPS = 1e-9;

        Vector direction = playerLoc.toVector().subtract(tntLoc.toVector());

        if(direction.lengthSquared() < EPS){
            return new Vector(0,tntVertical, 0);
        }

        direction = direction.normalize().multiply(tntHorizontal);
        direction.setY(tntVertical);

        return direction;
    }

}
