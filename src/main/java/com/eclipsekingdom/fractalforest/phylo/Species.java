package com.eclipsekingdom.fractalforest.phylo;

import com.eclipsekingdom.fractalforest.trees.ITree;
import com.eclipsekingdom.fractalforest.trees.fractal.FractalGrowthPattern;
import com.eclipsekingdom.fractalforest.trees.fractal.FractalTreeBuilder;
import com.eclipsekingdom.fractalforest.trees.fractal.genome.GenomeType;
import com.eclipsekingdom.fractalforest.util.Scale;
import com.eclipsekingdom.fractalforest.util.theme.ThemeType;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public enum Species {
    MAGNOLIA(Material.OAK_SAPLING),  //small
    BUCK_EYE(Material.OAK_SAPLING), //medium
    FLOWERING_HAWTHORN(Material.OAK_SAPLING), //medium
    OAK(Material.OAK_SAPLING), //big
    ELM(Material.OAK_SAPLING), //massive
    BIRCH(Material.BIRCH_SAPLING), //big
    FALL_BIRCH(Material.BIRCH_SAPLING),
    FALL_OAK(Material.OAK_SAPLING),//big
    FALL_ELM(Material.OAK_SAPLING), //massive
    FALL_MAPLE(Material.OAK_SAPLING),//big
    WEIRWOOD(Material.BIRCH_SAPLING),//big
    ;

    private Material material;

    Species(Material material) {
        this.material = material;
    }

    public ITree getIndividual(Player planter, Location seed) {
        switch (this) {
            case MAGNOLIA:
                return new FractalTreeBuilder(this, planter, seed, ThemeType.OAK.value(), new FractalGrowthPattern(GenomeType.MAGNOLIA.value()));
            case BUCK_EYE:
                return new FractalTreeBuilder(this, planter, seed, ThemeType.OAK.value(), new FractalGrowthPattern(GenomeType.BUCK_EYE.value()));
            case OAK:
                return new FractalTreeBuilder(this, planter, seed, ThemeType.OAK.value(), new FractalGrowthPattern(GenomeType.OAK.value()));
            case FALL_OAK:
                return new FractalTreeBuilder(this, planter, seed, ThemeType.FALL_OAK.value(), new FractalGrowthPattern(GenomeType.OAK.value()));
            case ELM:
                return new FractalTreeBuilder(this, planter, seed, ThemeType.OAK.value(), new FractalGrowthPattern(GenomeType.ELM.value()));
            case FALL_ELM:
                return new FractalTreeBuilder(this, planter, seed, ThemeType.FALL_ELM.value(), new FractalGrowthPattern(GenomeType.ELM.value()));
            case BIRCH:
                return new FractalTreeBuilder(this, planter, seed, ThemeType.BIRCH.value(), new FractalGrowthPattern(GenomeType.BIRCH.value()));
            case FALL_BIRCH:
                return new FractalTreeBuilder(this, planter, seed, ThemeType.FALL_BIRCH.value(), new FractalGrowthPattern(GenomeType.BIRCH.value()));
            case FALL_MAPLE:
                return new FractalTreeBuilder(this, planter, seed, ThemeType.FALL_MAPLE.value(), new FractalGrowthPattern(GenomeType.OAK.value()));
            case WEIRWOOD:
                return new FractalTreeBuilder(this, planter, seed, ThemeType.WEIRWOOD.value(), new FractalGrowthPattern(GenomeType.WEIRWOOD.value()));
            case FLOWERING_HAWTHORN:
                return new FractalTreeBuilder(this, planter, seed, ThemeType.FLOWERING_HAWTHORN.value(), new FractalGrowthPattern(GenomeType.BUCK_EYE.value()));
            default:
                return null;
        }
    }

    public ItemStack getSapling() {
        String species = toString();
        Entry entry = Encyclopedia.getEntry(species);
        Scale scale = getScale(entry);
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + format(species) + " Sapling");
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.DARK_GREEN + "Species: " + ChatColor.GRAY + species);
        lore.add(ChatColor.DARK_GREEN + "Size: " + ChatColor.GRAY + scale.getFormatted());
        if (entry != null) {
            lore.add(ChatColor.DARK_PURPLE + "○ Volume: " + ChatColor.WHITE + (int) entry.getAverageVolume() + "m²");
            lore.add(ChatColor.DARK_PURPLE + "○ Height: " + ChatColor.WHITE + (int) entry.getAverageHeight() + "m");
            lore.add(ChatColor.DARK_PURPLE + "○ Spread: " + ChatColor.WHITE + (int) entry.getAverageSpread() + "m");
        }
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }


    public static Scale getScale(Entry entry) {
        if (entry != null) {
            double vol = entry.getAverageVolume();
            if (vol < 15) {
                return Scale.SHRUB;
            } else if (vol < 50) {
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

    public static String format(String saplingName) {
        String[] parts = saplingName.split("_");
        String formatted = "";
        if (parts.length > 0) {
            String first = parts[0];
            if (first.length() > 1) {
                formatted += first.toUpperCase().charAt(0) + first.substring(1).toLowerCase();
            } else {
                formatted += first;
            }
            for (int i = 1; i < parts.length; i++) {
                String subString = parts[i];
                if (subString.length() > 1) {
                    formatted += " " + subString.toUpperCase().charAt(0) + subString.substring(1).toLowerCase();
                } else {
                    formatted += " " + subString;
                }
            }
            return formatted;
        } else {
            return saplingName;
        }
    }

    public static Species from(String string) {
        for (Species species : values()) {
            if (species.toString().equalsIgnoreCase(string)) {
                return species;
            }
        }
        return null;
    }

}
