package com.eclipsekingdom.fractalforest.encyclopedia;

import com.eclipsekingdom.fractalforest.util.system.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import static com.eclipsekingdom.fractalforest.util.language.Message.WARN_NO_PERMISSION;

public class CommandUpdateTRecords implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Permissions.canUpdateRecords(player)) {
                processUpdate(player);
            } else {
                sender.sendMessage(ChatColor.RED + WARN_NO_PERMISSION.toString());
            }
        }

        return false;
    }

    private void processUpdate(Player player) {
        boolean found = false;
        Inventory inventory = player.getInventory();
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack itemStack = inventory.getItem(i);
            if (Encyclopedia.isEncyclopedia(itemStack)) {
                String author = ((BookMeta) itemStack.getItemMeta()).getAuthor();
                inventory.setItem(i, Encyclopedia.getEncyclopediaRewrite(author, itemStack.getAmount()));
                found = true;
            }
        }

        String statusMessage = found ? ChatColor.GREEN + "Records updated" : ChatColor.RED + "No records found.";
        player.sendMessage(statusMessage);

    }

}
