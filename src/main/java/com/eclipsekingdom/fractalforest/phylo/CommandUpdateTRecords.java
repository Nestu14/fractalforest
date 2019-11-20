package com.eclipsekingdom.fractalforest.phylo;

import com.eclipsekingdom.fractalforest.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static com.eclipsekingdom.fractalforest.util.language.Message.WARN_NO_PERMISSION;

public class CommandUpdateTRecords implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (Permissions.canWriteEncyclopedia(player)) {
                Inventory inventory = player.getInventory();
                for (int i = 0; i < inventory.getSize(); i++) {
                    ItemStack itemStack = inventory.getItem(i);
                    if (itemStack != null && itemStack.hasItemMeta()) {
                        ItemMeta itemMeta = itemStack.getItemMeta();
                        if (itemMeta instanceof BookMeta) {
                            BookMeta bookMeta = (BookMeta) itemMeta;
                            if (bookMeta.hasLore()) {
                                List<String> lore = bookMeta.getLore();
                                if (lore.size() > 0) {
                                    if (lore.get(0).equals(CommandTEncyclopedia.LORE_ID)) {
                                        String author = bookMeta.getAuthor();
                                        ItemStack newItem = CommandTEncyclopedia.rewrite(author);
                                        newItem.setAmount(itemStack.getAmount());
                                        inventory.setItem(i, newItem);
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                sender.sendMessage(ChatColor.RED + WARN_NO_PERMISSION.toString());
            }

        }

        return false;
    }
}
