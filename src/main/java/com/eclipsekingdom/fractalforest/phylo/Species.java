package com.eclipsekingdom.fractalforest.phylo;

import com.eclipsekingdom.fractalforest.trees.ITree;
import com.eclipsekingdom.fractalforest.trees.fractal.FractalTreeBuilder;
import com.eclipsekingdom.fractalforest.trees.fractal.genome.GenomeType;
import com.eclipsekingdom.fractalforest.trees.fractal.FractalGrowthPattern;
import com.eclipsekingdom.fractalforest.util.Scale;
import com.eclipsekingdom.fractalforest.util.theme.ThemeType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Collections;

public enum Species {
    MAGNOLIA(Scale.SMALL, Material.OAK_SAPLING),
    BUCK_EYE(Scale.MEDIUM, Material.OAK_SAPLING),
    FLOWERING_HAWTHORN(Scale.MEDIUM, Material.OAK_SAPLING),
    OAK(Scale.BIG, Material.OAK_SAPLING),
    ELM(Scale.MASSIVE, Material.OAK_SAPLING),
    BIRCH(Scale.BIG, Material.BIRCH_SAPLING),
    FALL_BIRCH(Scale.BIG, Material.BIRCH_SAPLING),
    FALL_OAK(Scale.BIG, Material.OAK_SAPLING),
    FALL_ELM(Scale.MASSIVE, Material.OAK_SAPLING),
    FALL_MAPLE(Scale.BIG, Material.OAK_SAPLING),
    WEIRWOOD(Scale.BIG, Material.BIRCH_SAPLING),
    ;

    private ItemStack sapling;
    private Scale scale;

    Species(Scale scale, Material saplingBase) {
        this.sapling = buildSapling(this.toString(), scale, saplingBase);
        this.scale = scale;
    }

    public ITree getIndividual(Player planter, Location seed) {
        switch (this) {
            case MAGNOLIA:
                return new FractalTreeBuilder(planter, seed, ThemeType.OAK.value(), new FractalGrowthPattern(GenomeType.MAGNOLIA.value()));
            case BUCK_EYE:
                return new FractalTreeBuilder(planter, seed, ThemeType.OAK.value(), new FractalGrowthPattern(GenomeType.BUCK_EYE.value()));
            case OAK:
                return new FractalTreeBuilder(planter, seed, ThemeType.OAK.value(), new FractalGrowthPattern(GenomeType.OAK.value()));
            case FALL_OAK:
                return new FractalTreeBuilder(planter, seed, ThemeType.FALL_OAK.value(), new FractalGrowthPattern(GenomeType.OAK.value()));
            case ELM:
                return new FractalTreeBuilder(planter, seed, ThemeType.OAK.value(), new FractalGrowthPattern(GenomeType.ELM.value()));
            case FALL_ELM:
                return new FractalTreeBuilder(planter, seed, ThemeType.FALL_ELM.value(), new FractalGrowthPattern(GenomeType.ELM.value()));
            case BIRCH:
                return new FractalTreeBuilder(planter, seed, ThemeType.BIRCH.value(), new FractalGrowthPattern(GenomeType.BIRCH.value()));
            case FALL_BIRCH:
                return new FractalTreeBuilder(planter, seed, ThemeType.FALL_BIRCH.value(), new FractalGrowthPattern(GenomeType.BIRCH.value()));
            case FALL_MAPLE:
                return new FractalTreeBuilder(planter, seed, ThemeType.FALL_MAPLE.value(), new FractalGrowthPattern(GenomeType.OAK.value()));
            case WEIRWOOD:
                return new FractalTreeBuilder(planter, seed, ThemeType.WEIRWOOD.value(), new FractalGrowthPattern(GenomeType.WEIRWOOD.value()));
            case FLOWERING_HAWTHORN:
                return new FractalTreeBuilder(planter, seed, ThemeType.FLOWERING_HAWTHORN.value(), new FractalGrowthPattern(GenomeType.BUCK_EYE.value()));
            default:
                return null;
        }
    }

    public ItemStack getSapling() {
        return sapling;
    }

    private static ItemStack buildSapling(String string, Scale scale, Material material) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + format(string) + " Sapling");
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.DARK_GREEN + "Species: " + ChatColor.GRAY + string);
        lore.add(ChatColor.DARK_GREEN + "Scale: " + ChatColor.GRAY + scale.getFormatted());
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    private static String format(String saplingName) {
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
                    formatted += " " + subString.toLowerCase().charAt(0) + subString.substring(1).toLowerCase();
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
