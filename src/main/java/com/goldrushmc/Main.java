package com.goldrushmc;

import com.goldrushmc.samples.Collector;
import com.goldrushmc.samples.arena.RockArena;
import framework.arena.Arenable;
import framework.game.Gameable;
import framework.game.Playable;
import framework.manager.Managable;
import framework.utility.Convert;
import implement.game.GameContainer;
import implement.game.manage.GameManager;
import implement.game.manage.SaveManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import java.io.File;
import java.io.IOException;

/**
 * The core class for this plugin.
 */
public class Main extends JavaPlugin {

    public static Main instance;
    private Configuration config;
    private Managable gm;
    private SaveManager sm;

    public void onEnable(){
        final String saveFileName = "saved_games";

        instance = this;

        File folder = getDataFolder();
        File saveFile = new File(folder, saveFileName);

        if(!saveFile.exists()) {
            try {
                boolean success = saveFile.createNewFile();

                if(!success) {
                    getLogger().severe("Could not save new Game Save File into the directory: " + folder.getCanonicalPath());
                }
            } catch (IOException e) {
                getLogger().severe("Could not create or retrieve new Game Save File into the directory: " + folder.getPath());
            }
        }

        FileConfiguration fc = getConfig();
        gm = new GameManager(this);

        //Create all of the necessary components for a game to occur.
        Arenable arena = new RockArena(this, fc);
        Gameable collector = new Collector();
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Playable container = new GameContainer(arena, collector, scoreboard, Convert.makeStandardParticipants(Bukkit.getOnlinePlayers()), 0);

        gm.registerGame(collector);
        gm.registerArena(arena);

    }

    public void onDisable(){
        gm.stopAllGames();
    }
}

