package com.goldrushmc;

import implement.GameManager;
import implement.SaveManager;
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
    private final String saveFileName;
    private Configuration config;
    private GameManager gm;
    private SaveManager sm;

    public Main() {
        saveFileName = "saved_games";
    }

    public void onEnable(){
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


        GameManager gm = new GameManager();
        SaveManager sm = new SaveManager(saveFile.toURI().getPath());
    }

    public void onDisable(){

    }

    private void getAllExistingGames() {

    }
}
