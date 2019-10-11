package com.eclipsekingdom.fractalforest.trees;

import com.eclipsekingdom.fractalforest.util.theme.ITheme;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.Random;

public abstract class Tree implements ITree {

    private static Random rand = new Random();

    protected Location seed;
    protected Vector origin;
    protected ITheme theme;
    protected World world;
    protected Random random = Tree.rand;

    public Tree(Location seed, ITheme theme){
        this.origin = seed.toVector();
        this.seed = seed;
        this.world = seed.getWorld();
        this.theme = theme;
    }

}
