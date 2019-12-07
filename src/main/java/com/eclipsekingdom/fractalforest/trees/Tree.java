package com.eclipsekingdom.fractalforest.trees;

import com.eclipsekingdom.fractalforest.util.theme.ITheme;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Random;

public abstract class Tree implements ITree {

    private static Random rand = new Random();

    protected String species;
    protected Location seed;
    protected Vector origin;
    protected ITheme theme;
    protected World world;
    protected Random random = Tree.rand;
    protected Player planter;

    public Tree(Species species, Player planter, Location seed, ITheme theme) {
        this.species = species.toString();
        this.planter = planter;
        this.origin = seed.toVector();
        this.seed = seed;
        this.world = seed.getWorld();
        this.theme = theme;
    }

    public boolean hasPlanter() {
        return planter != null;
    }

    public Player getPlanter() {
        return planter;
    }

    @Override
    public String getSpecies() {
        return species;
    }

}
