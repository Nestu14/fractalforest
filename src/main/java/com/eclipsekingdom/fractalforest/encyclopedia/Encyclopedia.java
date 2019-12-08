package com.eclipsekingdom.fractalforest.encyclopedia;

import com.eclipsekingdom.fractalforest.trees.Species;
import com.eclipsekingdom.fractalforest.util.ChatUtil;
import com.google.common.collect.ImmutableList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Encyclopedia {

    private static final String TITLE = ChatColor.GREEN + "Tree EncyclopediaCache";
    private static final String LORE_ID = "A book of detailed";

    public static ItemStack getEncyclopediaRewrite(String author, int amount) {
        ItemStack encyclopedia = writeEncyclopedia(author);
        encyclopedia.setAmount(amount);
        return encyclopedia;
    }

    public static ItemStack getEncyclopedia() {
        String author = korokNames.get(random.nextInt(korokNames.size()));
        return writeEncyclopedia(author);
    }

    private static Random random = new Random();
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
        pages.addAll(getCurrentRecords());
        bookMeta.setPages(pages);
        book.setItemMeta(bookMeta);
        return book;
    }

    private static String makeHome(String author) {
        return "\n" + ChatColor.BOLD + "Tree EncyclopediaCache\n\n" + ChatColor.RESET + "By " + author + "\n\n§2" +
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

    private static ArrayList<String> getCurrentRecords() {
        ArrayList<String> pages = new ArrayList<>();
        int index = 0;
        String log = "";
        for (Map.Entry<String, Entry> entry : EncyclopediaCache.getSpeciesToEntry().entrySet()) {
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
        return pages;
    }

    private static String getLog(String species, Entry record) {
        return ChatColor.BLACK + ChatColor.BOLD.toString() + ChatUtil.format(species) + "\n" +
                ChatColor.BLACK + "> " + record.getSpecimensObserved() + " planted\n" +
                ChatColor.BLACK + "○ Size: " + getScale(record).getFormatted() + "\n" +
                ChatColor.BLACK + "○ Volume: " + (int) record.getAverageVolume() + "m²\n" +
                ChatColor.BLACK + "○ Height: " + (int) record.getAverageHeight() + "m\n" +
                ChatColor.BLACK + "○ Spread: " + (int) record.getAverageSpread() + "m\n\n";
    }

    public static boolean isEncyclopedia(ItemStack i) {
        if (i != null && i.hasItemMeta()) {
            ItemMeta itemMeta = i.getItemMeta();
            if (itemMeta instanceof BookMeta) {
                BookMeta bookMeta = (BookMeta) itemMeta;
                if (bookMeta.hasLore()) {
                    List<String> lore = bookMeta.getLore();
                    return (lore.size() > 0 && LORE_ID.equals(lore.get(0)));
                }
            }
        }
        return false;
    }

    public static List<String> getSaplingDetails(Species species) {
        ArrayList<String> details = new ArrayList();
        Entry entry = EncyclopediaCache.getEntry(species.toString());
        Scale scale = getScale(entry);
        details.add(ChatColor.DARK_GREEN + "Size: " + ChatColor.GRAY + scale.getFormatted());
        if (entry != null) {
            details.add(ChatColor.DARK_PURPLE + "○ Volume: " + ChatColor.WHITE + (int) entry.getAverageVolume() + "m²");
            details.add(ChatColor.DARK_PURPLE + "○ Height: " + ChatColor.WHITE + (int) entry.getAverageHeight() + "m");
            details.add(ChatColor.DARK_PURPLE + "○ Spread: " + ChatColor.WHITE + (int) entry.getAverageSpread() + "m");
        }
        return details;
    }


    private static Scale getScale(Entry entry) {
        if (entry != null) {
            double vol = entry.getAverageVolume();
            if (vol < 30) {
                return Scale.SHRUB;
            } else if (vol < 150) {
                return Scale.SMALL;
            } else if (vol < 1000) {
                return Scale.MEDIUM;
            } else if (vol < 2000) {
                return Scale.BIG;
            } else {
                return Scale.MASSIVE;
            }
        } else {
            return Scale.UNCLASSIFIED;
        }
    }


}
