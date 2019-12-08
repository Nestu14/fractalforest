package com.eclipsekingdom.fractalforest.worldgen;

import com.eclipsekingdom.fractalforest.gui.LiveSessions;
import com.eclipsekingdom.fractalforest.util.system.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.eclipsekingdom.fractalforest.util.language.Message.WARN_BUSY_TGEN;
import static com.eclipsekingdom.fractalforest.util.language.Message.WARN_NO_PERMISSION;

public class CommandTGenerator implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Permissions.canEditGen(player)) {
                if (!LiveSessions.isBusyGen()) {
                    LiveSessions.launchGen(player);
                } else {
                    player.sendMessage(WARN_BUSY_TGEN.getFromPlayer(LiveSessions.getGenEditor()));
                }
            } else {
                sender.sendMessage(ChatColor.RED + WARN_NO_PERMISSION.toString());
            }
        }

        return false;
    }


}
