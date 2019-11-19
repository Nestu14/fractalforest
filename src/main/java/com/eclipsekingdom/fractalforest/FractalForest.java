package com.eclipsekingdom.fractalforest;

import com.eclipsekingdom.fractalforest.gui.InputListener;
import com.eclipsekingdom.fractalforest.gui.LiveSessions;
import com.eclipsekingdom.fractalforest.populator.CommandTPop;
import com.eclipsekingdom.fractalforest.populator.PopCache;
import com.eclipsekingdom.fractalforest.protection.RegionValidation;
import com.eclipsekingdom.fractalforest.sapling.CommandSapling;
import com.eclipsekingdom.fractalforest.sapling.SaplingListener;
import com.eclipsekingdom.fractalforest.util.AutoCompleteListener;
import com.eclipsekingdom.fractalforest.util.PluginBase;
import org.bukkit.plugin.java.JavaPlugin;

public final class FractalForest extends JavaPlugin {

    public static FractalForest plugin;

    @Override
    public void onEnable() {
        this.plugin = this;

        new PluginConfig();
        PluginBase pluginBase = new PluginBase();
        new RegionValidation(pluginBase);

        new PopCache();

        getCommand("sapling").setExecutor(new CommandSapling());
        getCommand("fractalforest").setExecutor(new CommandFractalForest());
        getCommand("tpop").setExecutor(new CommandTPop());

        new AutoCompleteListener();
        new SaplingListener();
        new InputListener();
    }

    @Override
    public void onDisable() {
        SaplingListener.shutdown();
        LiveSessions.disable();
        PopCache.save();
        RegionValidation.shutdown();
    }
}