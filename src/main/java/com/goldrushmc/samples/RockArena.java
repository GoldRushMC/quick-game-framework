package com.goldrushmc.samples;

import framework.arena.Environmentable;
import implement.arena.AbstractArena;
import implement.arena.ArenaShift;
import org.bukkit.Bukkit;

/**
 * An arena made of pure stone. It is circular, rising from the center. Resembles a colosseum, but for Minecraft and less epic.
 */
public class RockArena extends AbstractArena {


    public RockArena() {
        super(null, "An arena of pure stone!");
    }

    @Override
    public boolean changeEnvironment(Environmentable environment) {
        if(currentGame.getGame().allowEnvironmentUpdates()) {
            //Make sure it is a compatible environment, i.e. It has the same layout, just different blocks
            if(environmentList.contains(environment)) {
                ArenaShift as = new ArenaShift(currentEnvironment, environment);
                Bukkit.getScheduler().runTask(null, as);
            }
        }
        return false;
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
