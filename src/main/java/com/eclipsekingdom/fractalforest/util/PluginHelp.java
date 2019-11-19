package com.eclipsekingdom.fractalforest.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class PluginHelp {

    public static void showTo(CommandSender sender){
        sender.sendMessage(ChatColor.YELLOW + "-------" + ChatColor.GOLD + " Sapling Commands " + ChatColor.YELLOW + "-------");
        sender.sendMessage(ChatColor.GOLD + "/sapling:" + ChatColor.RESET + " select saplings");
        showPop(sender);
    }

    public static void showPop(CommandSender sender){
        sender.sendMessage(ChatColor.YELLOW + "-------" + ChatColor.GOLD + " Populator Commands " + ChatColor.YELLOW + "-------");
        sender.sendMessage(ChatColor.GOLD + "/tpop list: " + ChatColor.RESET + "list all populators");
        sender.sendMessage(ChatColor.GOLD + "/tpop help: " + ChatColor.RESET + "display populator commands");
        sender.sendMessage(ChatColor.GOLD + "/tpop create: " + ChatColor.RESET + "create a tree populator");
        sender.sendMessage(ChatColor.GOLD + "/tpop create " + ChatColor.RED + "[tpop]" + ChatColor.GOLD + ": " + ChatColor.RESET + "create tree populator");
        sender.sendMessage(ChatColor.GOLD + "/tpop edit " + ChatColor.RED + "[tpop]" + ChatColor.GOLD + ": " + ChatColor.RESET + "edit tree populator");
        sender.sendMessage(ChatColor.GOLD + "/tpop delete " + ChatColor.RED + "[tpop]" + ChatColor.GOLD + ": " + ChatColor.RESET + "delete tree populator");
        sender.sendMessage(ChatColor.GOLD + "/tpop rename " + ChatColor.RED + "[old] [new]" + ChatColor.GOLD + ": " + ChatColor.RESET + "rename tree populator");
    }


}
