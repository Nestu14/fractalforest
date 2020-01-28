package com.eclipsekingdom.fractalforest.sapling;

import com.eclipsekingdom.fractalforest.sys.Permissions;
import com.eclipsekingdom.fractalforest.trees.Species;
import com.eclipsekingdom.fractalforest.util.Amount;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

import static com.eclipsekingdom.fractalforest.sys.language.Message.*;

public class CommandGiftSapling implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (Permissions.canSummonSapling(sender)) {
            if (args.length > 1) {
                String playerName = args[0];
                Player player = Bukkit.getPlayer(playerName);
                if (player != null) {
                    Species species = Species.from(args[1]);
                    if (species != null) {
                        int amount = 1;
                        if (args.length > 2) amount = Amount.parse(args[2]);
                        ItemStack sapling = species.getSapling();
                        sapling.setAmount(amount);
                        HashMap<Integer, ItemStack> overflow = player.getInventory().addItem(sapling);
                        World world = player.getWorld();
                        Location location = player.getLocation();
                        for (ItemStack itemStack : overflow.values()) {
                            world.dropItemNaturally(location, itemStack);
                        }
                        sender.sendMessage(ChatColor.GREEN.toString() + SUCCESS_GIFT_SAPLING.fromAmountSpeciesPlayer(amount, species.format(), player.getName()));
                    } else {
                        player.sendMessage(ChatColor.RED + WARN_UNKNOWN_SPECIES.toString());
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + WARN_NOT_ONLINE.fromPlayer(playerName));
                }
            } else {
                sender.sendMessage(ChatColor.RED + INFO_FORMAT.fromFormat("/giftsapling [" + ARG_PLAYER + "] [" + ARG_SPECIES + "] [" + ARG_AMOUNT + "]"));
            }
        } else {
            sender.sendMessage(ChatColor.RED + WARN_NO_PERMISSION.toString());
        }
        return false;
    }

}

