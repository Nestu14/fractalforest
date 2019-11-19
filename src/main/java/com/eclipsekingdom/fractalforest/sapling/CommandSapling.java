package com.eclipsekingdom.fractalforest.sapling;

import com.eclipsekingdom.fractalforest.Permissions;
import com.eclipsekingdom.fractalforest.gui.LiveSessions;
import com.eclipsekingdom.fractalforest.phylo.Species;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.eclipsekingdom.fractalforest.util.language.Message.WARN_NO_PERMISSION;

public class CommandSapling implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Permissions.canSummonSapling(player)) {
                LiveSessions.launchSpecies(player);
            } else {
                player.sendMessage(ChatColor.RED + WARN_NO_PERMISSION.toString());
            }

        }
        return false;
    }

}
