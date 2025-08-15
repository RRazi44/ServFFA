package fr.razi.ffagame.listeners;

import fr.razi.ffagame.FFAService;
import fr.razi.ffagame.utils.WorldManager;
import org.bukkit.World;
import org.bukkit.event.Listener;

public abstract class BaseFFAListener implements Listener {

    protected FFAService ffa;
    protected final World worldFFA;

    protected BaseFFAListener(FFAService ffa) {
        this.ffa = ffa;
        this.worldFFA = WorldManager.worldFFA;
    }

    protected boolean isInFFA(World world) {
        return world == worldFFA;
    }

}
