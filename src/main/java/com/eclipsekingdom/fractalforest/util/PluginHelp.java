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
        sender.sendMessage(ChatColor.GOLD + "/tpop help: " + ChatColor.RESET + "display pop commands");
        sender.sendMessage(ChatColor.GOLD + "/tpop create: " + ChatColor.RESET + "create a tree pop");
        sender.sendMessage(ChatColor.GOLD + "/tpop create " + ChatColor.RED + "[tpop]" + ChatColor.GOLD + ": " + ChatColor.RESET + "create tree pop");
        sender.sendMessage(ChatColor.GOLD + "/tpop edit " + ChatColor.RED + "[tpop]" + ChatColor.GOLD + ": " + ChatColor.RESET + "edit tree pop");
        sender.sendMessage(ChatColor.GOLD + "/tpop delete " + ChatColor.RED + "[tpop]" + ChatColor.GOLD + ": " + ChatColor.RESET + "delete tree pop");
        sender.sendMessage(ChatColor.GOLD + "/tpop rename " + ChatColor.RED + "[old] [new]" + ChatColor.GOLD + ": " + ChatColor.RESET + "rename tree pop");
    }


}
