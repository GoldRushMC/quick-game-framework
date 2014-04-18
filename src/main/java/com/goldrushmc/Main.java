package com.goldrushmc;

import framework.manager.Managable;
import implement.game.manage.GameManager;
import implement.game.manage.SaveManager;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * User: InspiredIdealist
 * Date: 09/04/2014
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
        sm = new SaveManager(saveFile.getPath());
    }

    public void onDisable(){
        gm.stopAllGames();
    }
}
