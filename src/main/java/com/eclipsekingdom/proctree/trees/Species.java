package com.eclipsekingdom.proctree.trees;

import com.eclipsekingdom.proctree.trees.fractal.genome.Genome;
import com.eclipsekingdom.proctree.trees.growth.FractalGrowth;
import com.eclipsekingdom.proctree.trees.theme.Theme;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public enum Species {
    OAK_SMALL(Theme.OAK, Genome.OAK_SMALL),
    OAK_MEDIUM(Theme.OAK, Genome.OAK_MEDIUM),
    OAK_BIG(Theme.OAK, Genome.OAK_BIG),
    OAK_MASSIVE(Theme.OAK, Genome.OAK_MASSIVE)
    ;

    private Tree tree;
    private ItemStack sapling;

    Species(Theme theme, Genome genome){
        this.tree = new Tree(theme.getTheme(), new FractalGrowth(genome.getGenome()));
        this.sapling = buildSapling(this.toString());
    }

    public Tree getTree(){
        return tree;
    }

    public ItemStack getSapling() {
        return sapling;
    }

    private static ItemStack buildSapling(String string){
        ItemStack itemStack = new ItemStack(Material.OAK_SAPLING);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Fractal Sapling");
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(Collections.singletonList(ChatColor.DARK_GREEN + "Species: " + ChatColor.GRAY + string));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static Species from(String string){
        for(Species species: values()){
            if(species.toString().equalsIgnoreCase(string)){
                return species;
            }
        }
        return null;
    }

}
