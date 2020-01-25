package com.eclipsekingdom.fractalforest;

import com.eclipsekingdom.fractalforest.encyclopedia.CommandTEncyclopedia;
import com.eclipsekingdom.fractalforest.encyclopedia.CommandUpdateTRecords;
import com.eclipsekingdom.fractalforest.encyclopedia.EncyclopediaCache;
import com.eclipsekingdom.fractalforest.gui.InputListener;
import com.eclipsekingdom.fractalforest.gui.LiveSessions;
import com.eclipsekingdom.fractalforest.protection.RegionValidation;
import com.eclipsekingdom.fractalforest.sapling.CommandGiftSapling;
import com.eclipsekingdom.fractalforest.sapling.CommandSapling;
import com.eclipsekingdom.fractalforest.sapling.SaplingListener;
import com.eclipsekingdom.fractalforest.sys.PluginBase;
import com.eclipsekingdom.fractalforest.sys.Version;
import com.eclipsekingdom.fractalforest.sys.config.ConfigLoader;
import com.eclipsekingdom.fractalforest.sys.config.PluginConfig;
import com.eclipsekingdom.fractalforest.trees.Species;
import com.eclipsekingdom.fractalforest.util.AutoCompleteListener;
import com.eclipsekingdom.fractalforest.worldgen.CommandTGenerator;
import com.eclipsekingdom.fractalforest.worldgen.Generator;
import com.eclipsekingdom.fractalforest.worldgen.pop.CommandTPop;
import com.eclipsekingdom.fractalforest.worldgen.pop.PopCache;
import com.eclipsekingdom.fractalforest.worldgen.pop.util.TreeBiome;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class FractalForest extends JavaPlugin {

    private static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        ConfigLoader.load();
        new PluginConfig();
        PluginBase pluginBase = new PluginBase();
        new RegionValidation(pluginBase);
        new EncyclopediaCache();

        TreeBiome.init();

        new PopCache();
        new Generator();
        new LiveSessions();

        Species.registerPermissions();

        getCommand("sapling").setExecutor(new CommandSapling());
        getCommand("giftsapling").setExecutor(new CommandGiftSapling());
        getCommand("fractalforest").setExecutor(new CommandFractalForest());
        getCommand("tpop").setExecutor(new CommandTPop());
        getCommand("tgenerator").setExecutor(new CommandTGenerator());
        getCommand("tencyclopedia").setExecutor(new CommandTEncyclopedia());
        getCommand("updatetrecords").setExecutor(new CommandUpdateTRecords());

        if (Version.current.value >= 109) new AutoCompleteListener();
        new SaplingListener();
        new InputListener();
    }

    @Override
    public void onDisable() {
        SaplingListener.shutdown();
        LiveSessions.disable();
        Generator.save();
        PopCache.save();
        RegionValidation.shutdown();
        EncyclopediaCache.save();
    }

    public static Plugin getPlugin() {
        return plugin;
    }

}