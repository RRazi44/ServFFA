package fr.razi.ffagame.task;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class BreakBlockTask extends BukkitRunnable {

    // Map des blocs -> tickServeur quand on doit casser
    private final Map<Location, Long> expiry = new LinkedHashMap<>();

    // Période effective de la tâche (en ticks). Mets exactement la même
    // valeur que celle passée à runTaskTimer(..., period).
    private final long periodTicks;

    // Tick “virtuel” interne basé sur le nombre d’exécutions * periodTicks
    private long currentTick = 0L;

    public BreakBlockTask(long periodTicks) {
        this.periodTicks = Math.max(1L, periodTicks);
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

            int cx = loc.getBlockX() >> 4;
            Block b = loc.getBlock();
            if (b.getType() != Material.AIR) {
                b.setType(Material.AIR, true);
                it.remove();
            }
        }


    }

    /**
     * Planifie la destruction d'un bloc après un délai en **ticks**.
     * Exemple: 100 ticks = 5 secondes à 20 TPS.
     */
    public void registerBlock(Location rawLoc, long delayTicks){
        if (rawLoc == null || rawLoc.getWorld() == null) return;
        Location key = rawLoc.getBlock().getLocation(); // normalisé aux coords de bloc
        long when = currentTick + Math.max(1L, delayTicks);
        expiry.put(key, when);
        // Bukkit.getLogger().info("[BreakTask] Ajout " + key + " -> " + when + " (now=" + currentTick + ")");
    }

    /** Overload pratique: 5s par défaut (100 ticks). */
    public void registerBlock(Location rawLoc) {
        registerBlock(rawLoc, 100L);
    }

    public void unregisterBlock(Location rawLoc){
        if (rawLoc == null) return;
        Location key = rawLoc.getBlock().getLocation();
        expiry.remove(key);
        // Bukkit.getLogger().info("[BreakTask] Remove " + key);
    }

    /** Debug rapide: taille de la file */
    public int pendingCount() {
        return expiry.size();
    }


}
