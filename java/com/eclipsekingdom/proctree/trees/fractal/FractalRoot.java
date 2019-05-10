package com.eclipsekingdom.proctree.trees.fractal;

import com.eclipsekingdom.proctree.FunctionIterator;
import com.eclipsekingdom.proctree.functions.BranchFunction;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

public class FractalRoot {

    private double length;
    private BranchFunction function;
    private Vector rX;
    private Vector rY;
    private Vector origin;
    private double radius;
    private World world;

    private Material material = Material.OAK_WOOD;


    public FractalRoot(World world, double length, BranchFunction function, Vector rX, Vector rY, Vector origin, double radius){
        this.world = world;
        this.length = length;
        this.function = function;
        this.rX = rX;
        this.rY = rY;
        this.origin = origin;
        this.radius = radius;
    }

    public void build(){

        FunctionIterator functionIterator = new FunctionIterator(world, origin, length, rX, rY, radius, function);
        for(Block b: functionIterator){
            b.setType(material);
        }
    }

}
