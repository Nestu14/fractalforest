package com.eclipsekingdom.proctree;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFractalForest implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            player.sendMessage(ChatColor.DARK_GREEN + "Fractal Forest");
            player.sendMessage(ChatColor.GREEN + "To generate a tree, right click on a grass block with an enchanted sapling");
            if(Permissions.canSummonSapling(player)){
                player.sendMessage(ChatColor.YELLOW + "-------" + ChatColor.GOLD + " Commands " + ChatColor.YELLOW + "-------");
                player.sendMessage(ChatColor.GOLD + "/sapling "+ChatColor.RED+ "[species]" + ChatColor.GOLD + ":" + ChatColor.RESET + " get a sapling");
                player.sendMessage(ChatColor.GOLD + "/sapling "+ChatColor.RED+ "[species] [amount]" + ChatColor.GOLD + ":" + ChatColor.RESET + " get sapling(s)");
            }
        }
        return false;
    }
}
