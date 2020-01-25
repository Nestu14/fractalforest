package com.eclipsekingdom.fractalforest.sys;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class PluginHelp {

    public static void showHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "Fractal Forest");
        showSapling(sender);
        showGen(sender);
        showPop(sender);
        showEncyclopedia(sender);
    }


    public static void showSapling(CommandSender sender) {
        sender.sendMessage(ChatColor.YELLOW + "-------" + ChatColor.GOLD + " Sapling Commands " + ChatColor.YELLOW + "-------");
        sendMessage(sender, "&6/sapling: &rselect saplings");
        sendMessage(sender, "&6/sapling &c[species]&6: &rget a sapling");
        sendMessage(sender, "&6/sapling &c[species] [amount]&6: &rget saplings");
        sendMessage(sender, "&6/giftsapling &c[player] [species]&6: &rgive sapling to player");
        sendMessage(sender, "&6/giftsapling &c[player] [species] [amount]&6: &rgive saplings to player");
    }

    public static void showGen(CommandSender sender) {
        sender.sendMessage(ChatColor.YELLOW + "-------" + ChatColor.GOLD + " Generator Commands " + ChatColor.YELLOW + "-------");
        sender.sendMessage(ChatColor.GOLD + "/tgenerator:" + ChatColor.RESET + " edit the tree generator");
    }

    public static void showPop(CommandSender sender) {
        sender.sendMessage(ChatColor.YELLOW + "-------" + ChatColor.GOLD + " Populator Commands " + ChatColor.YELLOW + "-------");
        sender.sendMessage(ChatColor.GOLD + "/tpop list: " + ChatColor.RESET + "list all populators");
        sender.sendMessage(ChatColor.GOLD + "/tpop help: " + ChatColor.RESET + "display populator commands");
        sender.sendMessage(ChatColor.GOLD + "/tpop create: " + ChatColor.RESET + "create a populator");
        sender.sendMessage(ChatColor.GOLD + "/tpop create " + ChatColor.RED + "[populator]" + ChatColor.GOLD + ": " + ChatColor.RESET + "create populator");
        sender.sendMessage(ChatColor.GOLD + "/tpop createfrom " + ChatColor.RED + "[populator]" + ChatColor.GOLD + ": " + ChatColor.RESET + "create populator from template");
        sender.sendMessage(ChatColor.GOLD + "/tpop edit " + ChatColor.RED + "[populator]" + ChatColor.GOLD + ": " + ChatColor.RESET + "edit populator");
        sender.sendMessage(ChatColor.GOLD + "/tpop delete " + ChatColor.RED + "[populator]" + ChatColor.GOLD + ": " + ChatColor.RESET + "delete populator");
        sender.sendMessage(ChatColor.GOLD + "/tpop rename " + ChatColor.RED + "[old] [new]" + ChatColor.GOLD + ": " + ChatColor.RESET + "rename populator");
    }

    public static void showEncyclopedia(CommandSender sender) {
        sender.sendMessage(ChatColor.YELLOW + "-------" + ChatColor.GOLD + " Encyclopedia Commands " + ChatColor.YELLOW + "-------");
        sender.sendMessage(ChatColor.GOLD + "/tencyclopedia: " + ChatColor.RESET + "request an encyclopedia");
        sender.sendMessage(ChatColor.GOLD + "/updatetrecords: " + ChatColor.RESET + "update the records in inventory");
    }

    private static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

}
