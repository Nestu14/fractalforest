package com.eclipsekingdom.fractalforest.sys;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import static com.eclipsekingdom.fractalforest.sys.language.Message.*;

public class PluginHelp {

    public static void showHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "__Fractal Forest_______");
        showCommands(sender);
        showPop(sender);
    }


    public static void showCommands(CommandSender sender) {
        sender.sendMessage(ChatColor.YELLOW + "-------" + ChatColor.GOLD + " " + LABEL_GENERAL_COMMANDS + " " + ChatColor.YELLOW + "-------");
        sendMessage(sender, "&6/sapling: &rselect saplings");
        sendMessage(sender, "&6/sapling &c[" + ARG_PLAYER + "] [" + ARG_SPECIES + "] [" + ARG_AMOUNT + "]&6: &r" + TEXT_GIFT_SAPLINGS);
        sender.sendMessage(ChatColor.GOLD + "/tencyclopedia: " + ChatColor.RESET + TEXT_TENCYCLOPEDIA);
        sender.sendMessage(ChatColor.GOLD + "/updatetrecords: " + ChatColor.RESET + TEXT_UPDATE_TRECORDS);
        sender.sendMessage(ChatColor.GOLD + "/tgenerator: " + ChatColor.RESET + TEXT_TGENERATOR);
    }

    public static void showPop(CommandSender sender) {
        sender.sendMessage(ChatColor.YELLOW + "-------" + ChatColor.GOLD + " " + LABEL_POP_COMMANDS + " " + ChatColor.YELLOW + "-------");
        sender.sendMessage(ChatColor.GOLD + "/tpop list: " + ChatColor.RESET + TEXT_POP_LIST);
        sender.sendMessage(ChatColor.GOLD + "/tpop help: " + ChatColor.RESET + TEXT_POP_HELP);
        sender.sendMessage(ChatColor.GOLD + "/tpop create " + ChatColor.RED + "[" + ARG_TPOP + "]" + ChatColor.GOLD + ": " + ChatColor.RESET + TEXT_POP_CREATE);
        sender.sendMessage(ChatColor.GOLD + "/tpop createfrom " + ChatColor.RED + "[" + ARG_TPOP + "]" + ChatColor.GOLD + ": " + ChatColor.RESET + TEXT_POP_CREATEFROM);
        sender.sendMessage(ChatColor.GOLD + "/tpop edit " + ChatColor.RED + "[" + ARG_TPOP + "]" + ChatColor.GOLD + ": " + ChatColor.RESET + TEXT_POP_EDIT);
        sender.sendMessage(ChatColor.GOLD + "/tpop delete " + ChatColor.RED + "[" + ARG_TPOP + "]" + ChatColor.GOLD + ": " + ChatColor.RESET + TEXT_POP_DELETE);
        sender.sendMessage(ChatColor.GOLD + "/tpop rename " + ChatColor.RED + "[" + ARG_OLD + "] [" + ARG_NEW + "]" + ChatColor.GOLD + ": " + ChatColor.RESET + TEXT_POP_RENAME);
    }


    private static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

}
