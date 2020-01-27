package com.eclipsekingdom.fractalforest.gui.page;

import com.eclipsekingdom.fractalforest.gui.MenuGlass;
import com.eclipsekingdom.fractalforest.trees.Species;
import com.eclipsekingdom.fractalforest.util.X.XMaterial;
import com.eclipsekingdom.fractalforest.worldgen.pop.PopCache;
import com.eclipsekingdom.fractalforest.worldgen.pop.TreePopulator;
import com.eclipsekingdom.fractalforest.worldgen.pop.TreeSpawner;
import com.eclipsekingdom.fractalforest.worldgen.pop.util.TreeBiome;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.eclipsekingdom.fractalforest.sys.language.Message.*;

public class Icons {

    private static Material wheatSeeds = XMaterial.WHEAT_SEEDS.parseMaterial();

    public static ItemStack BORDER_ITEM = MenuGlass.BLACK.getDotItem();
    public static ItemStack BACKGROUND_ITEM = MenuGlass.WHITE.getDotItem();
    public static ItemStack BACK_BUTTON = createIcon(Material.IRON_AXE, ChatColor.DARK_GRAY + ICON_BACK.toString());
    public static ItemStack CLOSE = XMaterial.BARRIER.isSupported() ? createIcon(XMaterial.BARRIER.parseMaterial(), ChatColor.RED + ICON_CLOSE.toString()) :
            MenuGlass.getCustom(14, ChatColor.RED, ICON_CLOSE.toString());


    public static ItemStack createIcon(Material material, String name) {
        ItemStack icon = new ItemStack(material, 1);
        ItemMeta meta = icon.getItemMeta();
        meta.setDisplayName(name);
        icon.setItemMeta(meta);
        return icon;
    }

    public static ItemStack createGlass(MenuGlass glass, String name) {
        ItemStack icon = glass.getItemStack();
        ItemMeta meta = icon.getItemMeta();
        meta.setDisplayName(name);
        icon.setItemMeta(meta);
        return icon;
    }

    public static ItemStack createTreeSpawner(TreeSpawner treeSpawner) {
        Species species = treeSpawner.getSpecies();
        ItemStack itemStack = new ItemStack(species.getSapling().getType());
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(species.toString());
        List<String> lore = new ArrayList<>();
        NumberFormat formatter = new DecimalFormat("#0.00");
        lore.add(ChatColor.GRAY + "Chance: " + formatter.format(treeSpawner.getChance() * 100) + "%");
        lore.add(ChatColor.GRAY + "Min: " + treeSpawner.getMin() + " trees");
        lore.add(ChatColor.GRAY + "Max: " + treeSpawner.getMax() + " trees");
        lore.add(ChatColor.GRAY + "Overflow: " + treeSpawner.getOverflow() + " blocks");
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack createTreeSpawnerType(TreeSpawner treeSpawner) {
        Species species = treeSpawner.getSpecies();
        ItemStack itemStack = new ItemStack(species.getSapling().getType());
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(species.toString());
        itemStack.setItemMeta(meta);
        return itemStack;
    }


    public static ItemStack createSpecies(Species species) {
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

    public static ItemStack createPopItem(TreePopulator populator) {
        ItemStack itemStack = new ItemStack(wheatSeeds);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(populator.getName());
        List<String> lore = new ArrayList<>();
        if (PopCache.isPreset(populator.getName())) lore.add(ChatColor.RED + "(" + LABEL_PRESET + ")");
        for (Map.Entry<TreeBiome, List<TreeSpawner>> entry : populator.getBiomeToTreeSpawner().entrySet()) {
            lore.add(ChatColor.GREEN + entry.getKey().toString());
            NumberFormat formatter = new DecimalFormat("#0.00");
            for (TreeSpawner treeSpawner : entry.getValue()) {
                String overflowString = treeSpawner.getOverflow() > 0 ? " (+" + treeSpawner.getOverflow() + "b)" : "";
                lore.add(ChatColor.GRAY + "> " + treeSpawner.getSpecies().toString() + " - " +
                        formatter.format(treeSpawner.getChance() * 100) + "% " + "[" + treeSpawner.getMin() + "-" + treeSpawner.getMax() + "]" + overflowString);
            }
        }

        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

}
