package com.eclipsekingdom.fractalforest;

import com.eclipsekingdom.fractalforest.gui.pop.PopInputListener;
import com.eclipsekingdom.fractalforest.gui.pop.session.LivePopSessions;
import com.eclipsekingdom.fractalforest.populator.CommandTPop;
import com.eclipsekingdom.fractalforest.populator.PopCache;
import com.eclipsekingdom.fractalforest.util.AutoCompleteListener;
import com.eclipsekingdom.fractalforest.sapling.CommandSapling;
import com.eclipsekingdom.fractalforest.sapling.SaplingListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class FractalForest extends JavaPlugin {

    public static FractalForest plugin;

    @Override
    public void onEnable() {
        this.plugin = this;

        new PluginConfig();

        new PopCache();

        getCommand("sapling").setExecutor(new CommandSapling());
        getCommand("fractalforest").setExecutor(new CommandFractalForest());
        getCommand("tpop").setExecutor(new CommandTPop());

        new AutoCompleteListener();
        new SaplingListener();
        new PopInputListener();
    }

    @Override
    public void onDisable() {
        SaplingListener.shutdown();
        LivePopSessions.disable();
        PopCache.save();
    }
}