package com.eclipsekingdom.fractalforest.phylo;

import com.eclipsekingdom.fractalforest.Permissions;
import com.google.common.collect.ImmutableList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.eclipsekingdom.fractalforest.util.language.Message.WARN_NO_PERMISSION;

public class CommandTEncyclopedia implements CommandExecutor {

    public static final String TITLE = ChatColor.GREEN + "Tree Encyclopedia";
    public static final String LORE_ID = "A book of detailed";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Permissions.canWriteEncyclopedia(player)) {
                if (Encyclopedia.hasRecords()) {
                    player.getInventory().addItem(writeEncyclopedia(getAuthor()));
                } else {
                    player.sendMessage(ChatColor.RED + "No records have been taken.");
                }
            } else {
                sender.sendMessage(ChatColor.RED + WARN_NO_PERMISSION.toString());
            }

        }

        return false;
    }

    public static ItemStack rewrite(String author) {
        return writeEncyclopedia(author);
    }

    private static ItemStack writeEncyclopedia(String author) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(LORE_ID);
        lore.add("records maintained");
        lore.add("by the Koroks");
        bookMeta.setLore(lore);
        bookMeta.setAuthor(ChatColor.DARK_GREEN + author);
        bookMeta.setTitle(TITLE);
        ArrayList<String> pages = new ArrayList<>();
        pages.add(makeHome(author));
        int index = 0;
        String log = "";
        for (Map.Entry<String, Entry> entry : Encyclopedia.getSpeciesToEntry().entrySet()) {
            String species = entry.getKey();
            Entry record = entry.getValue();
            log += getLog(species, record);
            if ((index + 1) % 2 == 0) {
                pages.add(log);
                log = "";
            }
            index++;
        }
        if (!log.equals("")) {
            pages.add(log);
        }
        bookMeta.setPages(pages);
        book.setItemMeta(bookMeta);
        return book;
    }

    private static String getLog(String species, Entry record) {
        return ChatColor.BLACK + ChatColor.BOLD.toString() + Species.format(species) + "\n" +
                ChatColor.BLACK + "> " + record.getSpecimensObserved() + " planted\n" +
                ChatColor.BLACK + "○ Size: " + Species.getScale(record).getFormatted() + "\n" +
                ChatColor.BLACK + "○ Volume: " + (int) record.getAverageVolume() + "m²\n" +
                ChatColor.BLACK + "○ Height: " + (int) record.getAverageHeight() + "m\n" +
                ChatColor.BLACK + "○ Spread: " + (int) record.getAverageSpread() + "m\n\n\n";
    }

    private static List<String> korokNames = new ImmutableList.Builder<String>()
            .add("Makar")
            .add("Aldo")
            .add("Drona")
            .add("Elma")
            .add("Hollo")
            .add("Irch")
            .add("Linder")
            .add("Oakin")
            .add("Olivio")
            .add("Rown")
            .build();

    private static Random random = new Random();

    private static String getAuthor() {
        return korokNames.get(random.nextInt(korokNames.size()));
    }

    private static String makeHome(String author) {
        return "\n" + ChatColor.BOLD + "Tree Encyclopedia\n\n" + ChatColor.RESET + "By " + author + "\n\n§2" +
                "        , , , ." + "\n" +
                "     &%%&%&&%" + "\n" +
                "    %&\\%&&%&&%" + "\n" +
                "   %&&%&%&/%&&%" + "\n" +
                "   %&&%/ %&%%&&*" + "\n" +
                "     `&%§6\\ §2` §6/§2%&'" + "\n" +
                "          §6| o|" + "\n" +
                "          §6| . |" + "\n" +
                "§a_\\__\\\\§6/ §a._§6\\§a//_/__/_";


    }

}
