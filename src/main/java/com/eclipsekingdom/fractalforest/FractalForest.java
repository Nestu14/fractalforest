package com.eclipsekingdom.fractalforest;

import com.eclipsekingdom.fractalforest.worldgen.CommandTGenerator;
import com.eclipsekingdom.fractalforest.worldgen.Generator;
import com.eclipsekingdom.fractalforest.worldgen.pop.CommandTPop;
import com.eclipsekingdom.fractalforest.worldgen.pop.PopCache;
import com.eclipsekingdom.fractalforest.gui.InputListener;
import com.eclipsekingdom.fractalforest.gui.LiveSessions;
import com.eclipsekingdom.fractalforest.encyclopedia.CommandTEncyclopedia;
import com.eclipsekingdom.fractalforest.encyclopedia.CommandUpdateTRecords;
import com.eclipsekingdom.fractalforest.encyclopedia.EncyclopediaCache;
import com.eclipsekingdom.fractalforest.trees.Species;
import com.eclipsekingdom.fractalforest.protection.RegionValidation;
import com.eclipsekingdom.fractalforest.sapling.CommandGiftSapling;
import com.eclipsekingdom.fractalforest.sapling.CommandSapling;
import com.eclipsekingdom.fractalforest.sapling.SaplingListener;
import com.eclipsekingdom.fractalforest.util.AutoCompleteListener;
import com.eclipsekingdom.fractalforest.util.config.PluginConfig;
import com.eclipsekingdom.fractalforest.util.system.PluginBase;
import org.bukkit.plugin.java.JavaPlugin;

public final class FractalForest extends JavaPlugin {

    public static FractalForest plugin;

    @Override
    public void onEnable() {
        this.plugin = this;

        new PluginConfig();
        PluginBase pluginBase = new PluginBase();
        new RegionValidation(pluginBase);
        new EncyclopediaCache();

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

        new AutoCompleteListener();
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
}