package com.eclipsekingdom.fractalforest.trees;

import com.eclipsekingdom.fractalforest.trees.effect.EffectType;
import com.eclipsekingdom.fractalforest.trees.effect.IEffects;
import com.eclipsekingdom.fractalforest.trees.gen.fractal.FractalGrowthPattern;
import com.eclipsekingdom.fractalforest.trees.gen.fractal.FractalTreeBuilder;
import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.GenomeType;
import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.IGenome;
import com.eclipsekingdom.fractalforest.trees.habitat.HabitatType;
import com.eclipsekingdom.fractalforest.trees.habitat.IHabitat;
import com.eclipsekingdom.fractalforest.util.ChatUtil;
import com.eclipsekingdom.fractalforest.util.Scale;
import com.eclipsekingdom.fractalforest.util.X.FSapling;
import com.eclipsekingdom.fractalforest.util.theme.ITheme;
import com.eclipsekingdom.fractalforest.util.theme.ThemeType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;

import static com.eclipsekingdom.fractalforest.sys.language.Message.LABEL_SAPLING;
import static com.eclipsekingdom.fractalforest.sys.language.Message.LABEL_SPECIES;

public enum Species {
    MAGNOLIA(Scale.SMALL, GenomeType.MAGNOLIA.value(), FSapling.OAK_SAPLING, ThemeType.OAK.getTheme()),
    BUCK_EYE(Scale.MEDIUM, GenomeType.BUCK_EYE.value(), FSapling.OAK_SAPLING, ThemeType.OAK.getTheme()),
    FLOWERING_HAWTHORN(Scale.MEDIUM, GenomeType.BUCK_EYE.value(), FSapling.OAK_SAPLING, ThemeType.FLOWERING_HAWTHORN.getTheme()),
    OAK(Scale.MASSIVE, GenomeType.OAK.value(), FSapling.OAK_SAPLING, ThemeType.OAK.getTheme()),
    ELM(Scale.MASSIVE, GenomeType.ELM.value(), FSapling.OAK_SAPLING, ThemeType.OAK.getTheme()),
    BIRCH(Scale.BIG, GenomeType.BIRCH.value(), FSapling.BIRCH_SAPLING, ThemeType.BIRCH.getTheme()),
    FALL_BIRCH(Scale.BIG, GenomeType.BIRCH.value(), FSapling.BIRCH_SAPLING, ThemeType.FALL_BIRCH.getTheme()),
    FALL_OAK(Scale.MASSIVE, GenomeType.OAK.value(), FSapling.OAK_SAPLING, ThemeType.FALL_OAK.getTheme()),
    FALL_ELM(Scale.MASSIVE, GenomeType.ELM.value(), FSapling.OAK_SAPLING, ThemeType.FALL_ELM.getTheme()),
    FALL_MAPLE(Scale.MASSIVE, GenomeType.OAK.value(), FSapling.OAK_SAPLING, ThemeType.FALL_MAPLE.getTheme()),
    WEIRWOOD(Scale.MASSIVE, GenomeType.WEIRWOOD.value(), FSapling.BIRCH_SAPLING, ThemeType.WEIRWOOD.getTheme()),
    WHITE_ASH(Scale.BIG, GenomeType.WHITE_ASH.value(), FSapling.ACACIA_SAPLING, ThemeType.WHITE_ASH.getTheme()),
    NARROW_LEAF_ASH(Scale.BIG, GenomeType.NARROW_LEAF_ASH.value(), FSapling.ACACIA_SAPLING, ThemeType.WHITE_ASH.getTheme()),
    SHANTUNG_MAPLE(Scale.MASSIVE, GenomeType.SHANTUNG_MAPLE.value(), FSapling.OAK_SAPLING, ThemeType.OAK.getTheme()),
    HICKORY(Scale.BIG, GenomeType.HICKORY.value(), FSapling.OAK_SAPLING, ThemeType.OAK.getTheme()),

    BLOOD_BUSH(Scale.SMALL, GenomeType.BLOOD_BUSH.value(), FSapling.NETHER_WART, ThemeType.BLOOD_BUSH.getTheme(), HabitatType.NETHER.getHabitat(), EffectType.NETHER.getEffects()),
    FLAME_TREE(Scale.MEDIUM, GenomeType.FLAME_TREE.value(), FSapling.NETHER_WART, ThemeType.FLAME_TREE.getTheme(), HabitatType.NETHER.getHabitat(), EffectType.NETHER.getEffects()),

    CYGNI(Scale.MEDIUM, GenomeType.CYGNI.value(), FSapling.CHORUS, ThemeType.CYGNI.getTheme(), HabitatType.END.getHabitat(), EffectType.END.getEffects()),
    SECCHI(Scale.SMALL, GenomeType.SECCHI.value(), FSapling.CHORUS, ThemeType.SECCHI.getTheme(), HabitatType.END.getHabitat(), EffectType.END.getEffects()),

    ;

    private Scale scale;
    private FractalGrowthPattern growthPattern;
    private FSapling fSapling;
    private IHabitat habitat;
    private IEffects effects;
    private ITheme theme;
    private String plantingPermString;
    private String formattedName;

    Species(Scale scale, IGenome genome, FSapling fSapling, ITheme theme, IHabitat habitat, IEffects effects) {
        init(scale, genome, fSapling, theme, habitat, effects);
    }

    Species(Scale scale, IGenome genome, FSapling fSapling, ITheme theme) {
        init(scale, genome, fSapling, theme, HabitatType.FOREST.getHabitat(), EffectType.FOREST.getEffects());
    }

    private void init(Scale scale, IGenome genome, FSapling fSapling, ITheme theme, IHabitat habitat, IEffects effects) {
        this.scale = scale;
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
        meta.setDisplayName(ChatColor.GREEN + formattedName + " " + LABEL_SAPLING);
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.DARK_GREEN + LABEL_SPECIES.toString() + ": " + ChatColor.GRAY + species);
        lore.add(ChatColor.DARK_GREEN + "Size: " + ChatColor.GRAY + scale.getFormatted());
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
