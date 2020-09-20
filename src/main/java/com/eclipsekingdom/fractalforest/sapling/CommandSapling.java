package com.eclipsekingdom.fractalforest.sapling;

import com.eclipsekingdom.fractalforest.gui.LiveSessions;
import com.eclipsekingdom.fractalforest.sys.Permissions;
import com.eclipsekingdom.fractalforest.sys.language.Message;
import com.eclipsekingdom.fractalforest.trees.Species;
import com.eclipsekingdom.fractalforest.util.Amount;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static com.eclipsekingdom.fractalforest.sys.language.Message.*;

public class CommandSapling implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (Permissions.canSummonSapling(sender)) {
            if (args.length > 0) {
                try {
                    Species species = Species.from(args[0]);
                    if (args.length > 1) {
                        String argOne = args[1];
                        try {
                            int amount = Integer.parseInt(argOne);
                            if (sender instanceof Player) {
                                giveLoot((Player) sender, species, amount);
                            } else {
                                sender.sendMessage(ChatColor.RED + INFO_FORMAT.fromFormat("/sapling [" + ARG_SPECIES + "] [" + ARG_PLAYER + "] [" + ARG_AMOUNT + "]"));
                            }
                        } catch (Exception e) {
                            Player player = Bukkit.getServer().getPlayer(argOne);
                            if (player != null) {
                                int amount = args.length > 2 ? Amount.parse(args[2]) : 1;
                                giveLoot(player, species, amount);
                                sender.sendMessage(ChatColor.GREEN + SUCCESS_GIFT_SAPLING.fromAmountSpeciesPlayer(amount, species.name(), player.getName()));
                            } else {
                                player.sendMessage(ChatColor.RED + WARN_NOT_ONLINE.fromPlayer(argOne));
                            }
                        }
                    } else {
                        if (sender instanceof Player) {
                            giveLoot((Player) sender, species, 1);
                        } else {
                            sender.sendMessage(ChatColor.RED + INFO_FORMAT.fromFormat("/sapling [" + ARG_SPECIES + "] [" + ARG_PLAYER + "] [" + ARG_AMOUNT + "]"));
                        }
                    }
                } catch (Exception e) {
                    if (args[0].equalsIgnoreCase("list")) {
                        sender.sendMessage(ChatColor.DARK_GREEN + LABEL_SAPLING.toString() + ":");
                        for (Species species : Species.values()) {
                            sender.sendMessage("- " + species.toString());
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + Message.WARN_UNKNOWN_SPECIES.toString());
                    }
                }
            } else {
                if (sender instanceof Player) {
                    LiveSessions.launchSpecies(((Player) sender));
                } else {
                    sender.sendMessage(ChatColor.RED + INFO_FORMAT.fromFormat("/sapling [" + ARG_SPECIES + "] [" + ARG_PLAYER + "] [" + ARG_AMOUNT + "]"));
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + WARN_NO_PERMISSION.toString());
        }

        return false;
    }

    private void giveLoot(Player player, Species species, int amount) {
        if (amount > 255) amount = 255;
        ItemStack item = species.getSapling();
        item.setAmount(amount);
        player.getInventory().addItem(item);
    }

}

