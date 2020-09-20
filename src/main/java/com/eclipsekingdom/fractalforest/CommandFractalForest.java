package com.eclipsekingdom.fractalforest;

import com.eclipsekingdom.fractalforest.sys.PluginHelp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandFractalForest implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        PluginHelp.showHelp(sender);
        return false;
    }
}
