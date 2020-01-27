package com.eclipsekingdom.fractalforest.trees;

import com.eclipsekingdom.fractalforest.encyclopedia.Encyclopedia;
import com.eclipsekingdom.fractalforest.trees.effect.EffectType;
import com.eclipsekingdom.fractalforest.trees.effect.IEffects;
import com.eclipsekingdom.fractalforest.trees.gen.fractal.FractalGrowthPattern;
import com.eclipsekingdom.fractalforest.trees.gen.fractal.FractalTreeBuilder;
import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.GenomeType;
import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.IGenome;
import com.eclipsekingdom.fractalforest.trees.habitat.HabitatType;
import com.eclipsekingdom.fractalforest.trees.habitat.IHabitat;
import com.eclipsekingdom.fractalforest.util.ChatUtil;
import com.eclipsekingdom.fractalforest.util.X.FSapling;
import com.eclipsekingdom.fractalforest.util.X.XMaterial;
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
    MAGNOLIA(GenomeType.MAGNOLIA.value(), FSapling.OAK_SAPLING, ThemeType.OAK.getTheme()),
    BUCK_EYE(GenomeType.BUCK_EYE.value(), FSapling.OAK_SAPLING, ThemeType.OAK.getTheme()),
    FLOWERING_HAWTHORN(GenomeType.BUCK_EYE.value(), FSapling.OAK_SAPLING, ThemeType.FLOWERING_HAWTHORN.getTheme()),
    OAK(GenomeType.OAK.value(), FSapling.OAK_SAPLING, ThemeType.OAK.getTheme()),
    ELM(GenomeType.ELM.value(), FSapling.OAK_SAPLING, ThemeType.OAK.getTheme()),
    BIRCH(GenomeType.BIRCH.value(), FSapling.BIRCH_SAPLING, ThemeType.BIRCH.getTheme()),
    FALL_BIRCH(GenomeType.BIRCH.value(), FSapling.BIRCH_SAPLING, ThemeType.FALL_BIRCH.getTheme()),
    FALL_OAK(GenomeType.OAK.value(), FSapling.OAK_SAPLING, ThemeType.FALL_OAK.getTheme()),
    FALL_ELM(GenomeType.ELM.value(), FSapling.OAK_SAPLING, ThemeType.FALL_ELM.getTheme()),
    FALL_MAPLE(GenomeType.OAK.value(), FSapling.OAK_SAPLING, ThemeType.FALL_MAPLE.getTheme()),
    WEIRWOOD(GenomeType.WEIRWOOD.value(), FSapling.BIRCH_SAPLING, ThemeType.WEIRWOOD.getTheme()),
    WHITE_ASH(GenomeType.WHITE_ASH.value(), FSapling.OAK_SAPLING, ThemeType.OAK.getTheme()),

    BLOOD_BUSH(GenomeType.BLOOD_BUSH.value(), FSapling.NETHER_WART, ThemeType.BLOOD_BUSH.getTheme(), HabitatType.NETHER.getHabitat(), EffectType.NETHER.getEffects()),
    FLAME_TREE(GenomeType.FLAME_TREE.value(), FSapling.NETHER_WART, ThemeType.FLAME_TREE.getTheme(), HabitatType.NETHER.getHabitat(), EffectType.NETHER.getEffects()),

    ;

    private FractalGrowthPattern growthPattern;
    private FSapling fSapling;
    private IHabitat habitat;
    private IEffects effects;
    private ITheme theme;
    private String plantingPermString;
    private String formattedName;

    Species(IGenome genome, FSapling fSapling, ITheme theme, IHabitat habitat, IEffects effects) {
        init(genome, fSapling, theme, habitat, effects);
    }

    Species(IGenome genome, FSapling fSapling, ITheme theme) {
        init(genome, fSapling, theme, HabitatType.FOREST.getHabitat(), EffectType.FOREST.getEffects());
    }

    private void init(IGenome genome, FSapling fSapling, ITheme theme, IHabitat habitat, IEffects effects) {
        this.growthPattern = new FractalGrowthPattern(genome);
        this.fSapling = fSapling;
        this.habitat = habitat;
        this.effects = effects;
        this.theme = theme;
        this.plantingPermString = "forest.plant." + toString().replace("_", "").toLowerCase();
        this.formattedName = ChatUtil.format(toString());
    }

    public ITree getIndividual(Player planter, Location seed) {
        return new FractalTreeBuilder(this, planter, seed, growthPattern.generateBlueprint());
    }

    public ItemStack getSapling() {
        String species = toString();
        ItemStack itemStack = fSapling.getItemStack();
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + formattedName + " Sapling");
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.DARK_GREEN + "Species: " + ChatColor.GRAY + species);
        lore.addAll(Encyclopedia.getSaplingDetails(this));
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public IHabitat getHabitat() {
        return habitat;
    }

    public IEffects getEffects() {
        return effects;
    }

    public ITheme getTheme() {
        return theme;
    }

    public String format() {
        return formattedName;
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
