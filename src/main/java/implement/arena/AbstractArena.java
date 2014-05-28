package implement.arena;

import framework.arena.Arenable;
import framework.arena.Blueprintable;
import framework.arena.Environmentable;
import framework.game.Gameable;
import framework.game.Playable;
import implement.general.AbstractInformable;
import inspire.Datum;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.*;

/**
 * The Base implementation for Arenas.
 */
public abstract class AbstractArena extends AbstractInformable implements Arenable {

    protected Blueprintable blueprint;
    protected List<Gameable> supportedGames = new ArrayList<>();
    private  List<String> supportedGameNames = new ArrayList<>();
    protected List<Environmentable> environmentList = new ArrayList<>();
    protected Playable currentGame;
    protected Environmentable currentEnvironment;
    protected List<Datum<?>> data = new ArrayList<>();
    protected final JavaPlugin plugin;

    protected AbstractArena(String name, String description, JavaPlugin plugin) {
        super(name, description);
        this.plugin = plugin;
    }

    protected AbstractArena(String description, JavaPlugin plugin) {
        this(null, description, plugin);
    }

    @Override
    public List<String> getSupportedGameTypes() {
        return supportedGameNames;
    }

    @Override
    public List<Gameable> getSupportedGames() {
        return supportedGames;
    }

    @Override
    public List<Environmentable> getEnvironments() {
        return environmentList;
    }

    @Override
    public Environmentable getCurrentEnvironment() {
        return currentEnvironment;
    }

    @Override
    public boolean changeEnvironment(String environmentName) {
        for(Environmentable e : environmentList) {
            if(e.getName().equals(environmentName)) {
                return changeEnvironment(e);
            }
        }
        return false;
    }

    @Override
    public boolean changeEnvironment(Environmentable environment) {
        if(currentGame.getGame().allowEnvironmentUpdates()) {
            //Make sure it is a compatible environment, i.e. It has the same layout, just different blocks
            if(environmentList.contains(environment)) {
                ArenaShift as = new ArenaShift(currentEnvironment, environment);
                Bukkit.getScheduler().runTask(plugin, as);
                return true;
            }
        }
        return false;
    }

    @Override
    public Playable getCurrentGame() {
        return currentGame;
    }

    @Override
    public Blueprintable getBlueprint() {
        return blueprint;
    }

    @Override
    public List<Datum<?>> getData() {
        return data;
    }
}