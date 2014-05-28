package com.goldrushmc.samples.arena;

import framework.manager.BlueprintFactory;
import implement.arena.AbstractArena;
import implement.arena.AbstractBlueprint;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * An arena made of pure stone. It is circular, rising from the center. Resembles a colosseum, but for Minecraft and less epic.
 */
public class RockArena extends AbstractArena {


    public RockArena(JavaPlugin plugin, FileConfiguration file) {
        super(null, "An arena of pure stone!", plugin);

        blueprint = BlueprintFactory.CreateNewBlueprint(null, "A plain blueprint that is just a big slab of stone", new ArrayList<Location>());
    }



    @Override
    public void capturePresentState() {
    }

    @Override
    public boolean save(String saveName) {
        return false;
    }

    @Override
    public boolean load(String saveName) {
        return false;
    }

    @Override
    public boolean loadMostRecentSave() {
        return false;
    }
}
