package com.eclipsekingdom.fractalforest.trees;

import com.eclipsekingdom.fractalforest.encyclopedia.EncyclopediaCache;
import com.eclipsekingdom.fractalforest.encyclopedia.Entry;
import com.eclipsekingdom.fractalforest.trees.effect.IEffects;
import com.eclipsekingdom.fractalforest.trees.gen.fractal.FractalGrowthPattern;
import com.eclipsekingdom.fractalforest.trees.gen.fractal.FractalTreeBuilder;
import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.GenomeType;
import com.eclipsekingdom.fractalforest.trees.habitat.IHabitat;
import com.eclipsekingdom.fractalforest.util.Scale;
import com.eclipsekingdom.fractalforest.util.theme.ITheme;
import com.eclipsekingdom.fractalforest.util.theme.ThemeType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;

public enum Species {
    MAGNOLIA(Material.OAK_SAPLING),
    BUCK_EYE(Material.OAK_SAPLING),
    FLOWERING_HAWTHORN(Material.OAK_SAPLING),
    OAK(Material.OAK_SAPLING),
    ELM(Material.OAK_SAPLING),
    BIRCH(Material.BIRCH_SAPLING),
    FALL_BIRCH(Material.BIRCH_SAPLING),
    FALL_OAK(Material.OAK_SAPLING),
    FALL_ELM(Material.OAK_SAPLING),
    FALL_MAPLE(Material.OAK_SAPLING),
    WEIRWOOD(Material.BIRCH_SAPLING),
    FLAME_TREE(Material.NETHER_WART),
    WHITE_ASH(Material.OAK_SAPLING),

    ;

    private Material saplingMaterial;
    private IHabitat habitat;
    private IEffects effects;
    private ITheme theme;
    private String plantingPermString;

    Species(Material saplingMaterial, IHabitat habitat, IEffects effects, ITheme theme) {
        this.saplingMaterial = saplingMaterial;
        this.habitat = habitat;
        this.effects = effects;
        this.theme = theme;
        this.plantingPermString = "forest.plant." + toString().replace("_", "").toLowerCase();
    }

    public ITree getIndividual(Player planter, Location seed) {
        switch (this) {
            case MAGNOLIA:
                return new FractalTreeBuilder(this, planter, seed, ThemeType.OAK.getTheme(), new FractalGrowthPattern(GenomeType.MAGNOLIA.value()));
            case BUCK_EYE:
                return new FractalTreeBuilder(this, planter, seed, ThemeType.OAK.getTheme(), new FractalGrowthPattern(GenomeType.BUCK_EYE.value()));
            case OAK:
                return new FractalTreeBuilder(this, planter, seed, ThemeType.OAK.getTheme(), new FractalGrowthPattern(GenomeType.OAK.value()));
            case FALL_OAK:
                return new FractalTreeBuilder(this, planter, seed, ThemeType.FALL_OAK.getTheme(), new FractalGrowthPattern(GenomeType.OAK.value()));
            case ELM:
                return new FractalTreeBuilder(this, planter, seed, ThemeType.OAK.getTheme(), new FractalGrowthPattern(GenomeType.ELM.value()));
            case FALL_ELM:
                return new FractalTreeBuilder(this, planter, seed, ThemeType.FALL_ELM.getTheme(), new FractalGrowthPattern(GenomeType.ELM.value()));
            case BIRCH:
                return new FractalTreeBuilder(this, planter, seed, ThemeType.BIRCH.getTheme(), new FractalGrowthPattern(GenomeType.BIRCH.value()));
            case FALL_BIRCH:
                return new FractalTreeBuilder(this, planter, seed, ThemeType.FALL_BIRCH.getTheme(), new FractalGrowthPattern(GenomeType.BIRCH.value()));
            case FALL_MAPLE:
                return new FractalTreeBuilder(this, planter, seed, ThemeType.FALL_MAPLE.getTheme(), new FractalGrowthPattern(GenomeType.OAK.value()));
            case WEIRWOOD:
                return new FractalTreeBuilder(this, planter, seed, ThemeType.WEIRWOOD.getTheme(), new FractalGrowthPattern(GenomeType.WEIRWOOD.value()));
            case FLOWERING_HAWTHORN:
                return new FractalTreeBuilder(this, planter, seed, ThemeType.FLOWERING_HAWTHORN.getTheme(), new FractalGrowthPattern(GenomeType.BUCK_EYE.value()));
            case WHITE_ASH:
                return new FractalTreeBuilder(this, planter, seed, ThemeType.OAK.getTheme(), new FractalGrowthPattern(GenomeType.WHITE_ASH.value()));
            case FLAME_TREE:
                return new FractalTreeBuilder(this, planter, seed, ThemeType.FLAME_TREE.getTheme(), new FractalGrowthPattern(GenomeType.FLAME_TREE.value()));
            default:
                return null;
        }
    }

    public ItemStack getSapling() {
        String species = toString();
        Entry entry = EncyclopediaCache.getEntry(species);
        Scale scale = getScale(entry);
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + format(species) + " Sapling");
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> lore = new ArrayList();
        //TODO ask encyclopedia cache for text
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


    public String format() {
        return format(toString());
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

    public String getPlanterPerm() {
        return plantingPermString;
    }

    public static Species from(String string) {
        for (Species species : values()) {
            if (species.toString().equalsIgnoreCase(string)) {
                return species;
            }
        }
        return null;
    }

    public static void registerPermissions() {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        for (Species species : values()) {
            pm.addPermission(new Permission(species.getPlanterPerm(), "allows player to plant " + species.format() + " sapling."));
        }
    }

}
