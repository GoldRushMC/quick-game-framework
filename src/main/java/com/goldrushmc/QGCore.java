package com.goldrushmc;

import implement.game.manage.SaveManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import register.SimpleTrigger;

import java.io.File;
import java.io.IOException;

/**
 * The core class for this plugin.
 */
public class QGCore extends JavaPlugin {

    private static QGCore instance;
    private Thread coreThread;

    private Configuration config;
    private SaveManager sm;

    public static QGCore getInstance() {
        if(instance == null) {
            instance = new QGCore();
            Bukkit.getPluginManager().enablePlugin(instance);
        }
        return instance;
    }

    public void onEnable(){
        instance = this;
        //Get the thread that will be used to access the layer system
        coreThread = Thread.currentThread();

        final String saveFileName = "saved_games";

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
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    }

    public void onDisable(){
    }
}

