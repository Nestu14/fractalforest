package com.eclipsekingdom.fractalforest.sapling;

import com.eclipsekingdom.fractalforest.Permissions;
import com.eclipsekingdom.fractalforest.gui.LiveSessions;
import com.eclipsekingdom.fractalforest.phylo.Species;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static com.eclipsekingdom.fractalforest.util.language.Message.WARN_NO_PERMISSION;

public class CommandSapling implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Permissions.canSummonSapling(player)) {
                if (args.length > 0) {
                    if (args[0].equalsIgnoreCase("list")) {
                        sendSaplingList(player);
                    } else {
                        Species species = Species.from(args[0]);
                        if (species != null) {
                            int amount = 1;
                            if (args.length > 1) {
                                try {
                                    amount = Integer.parseInt(args[1]);
                                } catch (Exception e) {
                                    amount = 1;
                                }
                            }
                            ItemStack sapling = species.getSapling();
                            sapling.setAmount(amount);
                            player.getInventory().addItem(sapling);
                        } else {
                            player.sendMessage(ChatColor.RED + "Unrecognized species");
                        }
                    }
                } else {
                    LiveSessions.launchSpecies(player);
                }
            } else {
                player.sendMessage(ChatColor.RED + WARN_NO_PERMISSION.toString());
            }

        }
        return false;
    }


    private void sendSaplingList(Player player) {
        player.sendMessage(ChatColor.DARK_GREEN + "Saplings:");
        for (Species species : Species.values()) {
            player.sendMessage("- " + species.toString());
        }
    }


}


