package com.eclipsekingdom.fractalforest.trees;

import com.eclipsekingdom.fractalforest.trees.effect.IEffects;
import com.eclipsekingdom.fractalforest.util.theme.ITheme;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Random;

public abstract class Tree implements ITree {

    private static Random rand = new Random();

    protected Species species;
    protected Location seed;
    protected Vector origin;
    protected ITheme theme;
    protected World world;
    protected Random random = Tree.rand;
    protected Player planter;
    protected IEffects effects;

    public Tree(Species species, Player planter, Location seed) {
        this.species = species;
        this.planter = planter;
        this.origin = seed.toVector();
        this.seed = seed;
        this.world = seed.getWorld();
        this.theme = species.getTheme();
        this.effects = species.getEffects();
    }

    public boolean hasPlanter() {
        return planter != null;
    }

    public Player getPlanter() {
        return planter;
    }

    @Override
    public Species getSpecies() {
        return species;
    }

}
