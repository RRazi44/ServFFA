package fr.razi.ffagame.task;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class BreakBlockTask extends BukkitRunnable {

    private final Map<Location, Long> expiry = new LinkedHashMap<>();
    private final long periodTicks;
    private long currentTick = 0L;

    public BreakBlockTask(long periodTicks) {
        this.periodTicks = periodTicks;
    }

    @Override
    public void run() {

        currentTick += periodTicks;
        if (expiry.isEmpty()) return;

        Iterator<Map.Entry<Location, Long>> it = expiry.entrySet().iterator();
        while (it.hasNext()) {

            Map.Entry<Location, Long> e = it.next();
            long due = e.getValue();
            if (due > currentTick) continue;

            Location loc = e.getKey();
            if (loc == null || loc.getWorld() == null) {
                it.remove(); continue;
            }

            Block b = loc.getBlock();
            if (b.getType() != Material.AIR) {
                b.setType(Material.AIR, true);
                it.remove();
            }
        }

    }

    public void registerBlock(Location rawLoc, long delayTicks){
        if (rawLoc == null || rawLoc.getWorld() == null) return;
        Location key = rawLoc.getBlock().getLocation();
        long when = currentTick + Math.max(1L, delayTicks);
        expiry.put(key, when);
    }

    public void registerBlock(Location rawLoc) {
        registerBlock(rawLoc, 100L);
    }

    public void unregisterBlock(Location rawLoc){
        if (rawLoc == null) return;
        Location key = rawLoc.getBlock().getLocation();
        expiry.remove(key);
    }

}
