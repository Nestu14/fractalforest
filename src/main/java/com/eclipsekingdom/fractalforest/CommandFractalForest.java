package com.eclipsekingdom.fractalforest;

import com.eclipsekingdom.fractalforest.util.PluginHelp;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFractalForest implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        PluginHelp.showHelp(sender);
        return false;
    }
}
