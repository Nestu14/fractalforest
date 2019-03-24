package com.eclipsekingdom.proctree;

import com.eclipsekingdom.proctree.commands.CommandTree;
import com.eclipsekingdom.proctree.managers.TreeManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ProcTree extends JavaPlugin {

    private TreeManager treeManager = new TreeManager(this);
    public TreeManager getTreeManager() {
        return treeManager;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

        this.getCommand("ptree").setExecutor(new CommandTree(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
