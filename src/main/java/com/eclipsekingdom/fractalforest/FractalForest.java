package com.eclipsekingdom.fractalforest;

import com.eclipsekingdom.fractalforest.gui.InputListener;
import com.eclipsekingdom.fractalforest.gui.LiveSessions;
import com.eclipsekingdom.fractalforest.protection.RegionValidation;
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

        new PopCache();
        new Generator();
        new LiveSessions();

        Species.registerPermissions();

        getCommand("sapling").setExecutor(new CommandSapling());
        getCommand("fractalforest").setExecutor(new CommandFractalForest());
        getCommand("tpop").setExecutor(new CommandTPop());
        getCommand("tgenerator").setExecutor(new CommandTGenerator());

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
    }

    public static Plugin getPlugin() {
        return plugin;
    }

}