package com.eclipsekingdom.fractalforest.phylo.encyclopedia;

import com.eclipsekingdom.fractalforest.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.eclipsekingdom.fractalforest.util.language.Message.WARN_NO_PERMISSION;

public class CommandTEncyclopedia implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Permissions.canWriteEncyclopedia(player)) {
                if (EncyclopediaCache.hasRecords()) {
                    player.getInventory().addItem(Encyclopedia.getEncyclopedia());
                } else {
                    player.sendMessage(ChatColor.RED + "No records have been taken.");
                }
            } else {
                sender.sendMessage(ChatColor.RED + WARN_NO_PERMISSION.toString());
            }

        }

        return false;
    }


}
