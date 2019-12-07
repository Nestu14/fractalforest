package com.eclipsekingdom.fractalforest.util.language;

import com.eclipsekingdom.fractalforest.util.system.ConsoleSender;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

import static com.eclipsekingdom.fractalforest.util.language.Message.CONSOLE_FILE_ERROR;


public class Language {

    private static File file = new File("plugins/MeteorStorms", "language.yml");
    private static FileConfiguration config = YamlConfiguration.loadConfiguration(file);

    public Language(){
        load();
    }

    private static void load(){

        if(file.exists()){
            try{
                for(Message message: Message.values()){
                    MessageSetting setting = message.getMessageSetting();
                    setting.setMessage(config.getString(setting.getMessageSetting(), setting.getMessageDefault()));
                    if(setting.getMessage() == null) setting.setMessage(setting.getMessageDefault());
                }
            }catch (Exception e){
            }
        }else{
            //createDefault(); //TODO USE LANGUAGE FILE
        }
    }

    private static void createDefault(){
        for(Message message: Message.values()){
            MessageSetting setting = message.getMessageSetting();
            config.set(setting.getMessageSetting(), setting.getMessageDefault());
        }
        saveConfig();
    }

    private static void saveConfig(){
        try{
            config.save(file);
        } catch (Exception e){
            ConsoleSender.sendMessage(ChatColor.RED + CONSOLE_FILE_ERROR.getFromFile(file.getName()));
        }
    }


}
