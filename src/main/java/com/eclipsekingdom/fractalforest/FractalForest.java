package com.eclipsekingdom.fractalforest;

import com.eclipsekingdom.fractalforest.sapling.AutoCompleteListener;
import com.eclipsekingdom.fractalforest.sapling.CommandSapling;
import com.eclipsekingdom.fractalforest.sapling.SaplingListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class FractalForest extends JavaPlugin {

    public static FractalForest plugin;

    @Override
    public void onEnable() {
        this.plugin = this;

        new PluginConfig();

        this.getCommand("sapling").setExecutor(new CommandSapling());
        this.getCommand("fractalforest").setExecutor(new CommandFractalForest());

        new AutoCompleteListener();
        new SaplingListener();
    }

    @Override
    public void onDisable() {
        SaplingListener.shutdown();
    }
}