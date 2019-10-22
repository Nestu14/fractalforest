package com.eclipsekingdom.fractalforest.gui;

import com.eclipsekingdom.fractalforest.phylo.Species;
import com.eclipsekingdom.fractalforest.populator.TreeSpawner;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Icons {

    public static ItemStack BORDER_ITEM = createIcon(Material.BLACK_STAINED_GLASS_PANE, ChatColor.GRAY + "•");
    public static ItemStack BACKGROUND_ITEM = createIcon(Material.WHITE_STAINED_GLASS_PANE, ChatColor.WHITE + "•");
    public static ItemStack BACK_BUTTON = createIcon(Material.IRON_AXE, ChatColor.DARK_GRAY + "Back");
    public static ItemStack CLOSE = createIcon(Material.BARRIER, ChatColor.RED + "Close");


    public static ItemStack createIcon(Material material, String name) {
        ItemStack icon = new ItemStack(material, 1);
        ItemMeta meta = icon.getItemMeta();
        meta.setDisplayName(name);
        icon.setItemMeta(meta);
        return icon;
    }

    public static ItemStack createTreeSpawner(TreeSpawner treeSpawner){
        Species species = treeSpawner.getSpecies();
        ItemStack itemStack = new ItemStack(species.getSapling().getType());
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(species.toString());
        List<String> lore = new ArrayList<>();
        NumberFormat formatter = new DecimalFormat("#0.00");
        lore.add(ChatColor.GRAY + "Chance: " + formatter.format(treeSpawner.getChance()*100) + "%");
        lore.add(ChatColor.GRAY + "min: " + treeSpawner.getMin() + " trees");
        lore.add(ChatColor.GRAY + "max: " + treeSpawner.getMax() + " trees");
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }


    public static ItemStack createSpecies(Species species){
        ItemStack itemStack = new ItemStack(species.getSapling().getType());
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(species.toString());
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack VALUE_MANIPULATOR(String label, String current) {
        ItemStack itemStack = new ItemStack(Material.ARROW);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + label);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + current);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack CURRENT_VALUE(Material material, String label, String value) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + label);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + value);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack createBiome(Biome biome){
        ItemStack itemStack = new ItemStack(getMaterial(biome));
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(biome.toString());
        itemStack.setItemMeta(meta);
        return itemStack;
    }


    private static Material getMaterial(Biome biome) {
        switch (biome) {
            case FOREST:
                return Material.OAK_LOG;
            case FLOWER_FOREST:
                return Material.POPPY;
            case BIRCH_FOREST:
                return Material.BIRCH_LOG;
            case DARK_FOREST:
                return Material.DARK_OAK_LOG;
            case BIRCH_FOREST_HILLS:
                return Material.BIRCH_LOG;
            case TALL_BIRCH_FOREST:
                return Material.BIRCH_LOG;
            case TALL_BIRCH_HILLS:
                return Material.BIRCH_LOG;
            case DARK_FOREST_HILLS:
                return Material.DARK_OAK_LOG;
            case SNOWY_TAIGA_MOUNTAINS:
                return Material.SPRUCE_LOG;
            case TAIGA_MOUNTAINS:
                return Material.SPRUCE_LOG;
            case TAIGA:
                return Material.SPRUCE_LOG;
            case TAIGA_HILLS:
                return Material.SPRUCE_LOG;
            case SNOWY_TAIGA:
                return Material.SPRUCE_LOG;
            case SNOWY_TAIGA_HILLS:
                return Material.SPRUCE_LOG;
            case SNOWY_TUNDRA:
                return Material.SNOW_BLOCK;
            case SNOWY_MOUNTAINS:
                return Material.SNOW_BLOCK;
            case WOODED_MOUNTAINS:
                return Material.OAK_LOG;
            case PLAINS:
                return Material.GRASS_BLOCK;
            default:
                return Material.GRAY_TERRACOTTA;
        }
    }


}
