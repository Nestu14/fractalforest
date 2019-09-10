package com.eclipsekingdom.proctree;

import com.eclipsekingdom.proctree.sapling.AutoCompleteListener;
import com.eclipsekingdom.proctree.sapling.CommandSapling;
import com.eclipsekingdom.proctree.sapling.SaplingListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ProcTree extends JavaPlugin {

    public static ProcTree plugin;

    @Override
    public void onEnable() {
        this.plugin = this;

        this.getCommand("sapling").setExecutor(new CommandSapling());
        this.getCommand("fractalforest").setExecutor(new CommandFractalForest());

        new AutoCompleteListener();
        new SaplingListener();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
