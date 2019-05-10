package com.eclipsekingdom.proctree.trees.fractal;

import com.eclipsekingdom.proctree.FunctionIterator;
import com.eclipsekingdom.proctree.util.TreeMathUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;
import org.bukkit.World;

import java.util.Random;

public class FractalBranch {

    private Vector begin;
    private Vector end;
    private double radius;
    public double getRadius(){
        return radius;
    }
    private boolean finished;
    public boolean isFinished(){
        return finished;
    }
    private World world;

    private Material primaryMaterial = Material.OAK_WOOD;
    private Material secondaryMaterial = Material.SPRUCE_FENCE;

    private Material primaryLeaf = Material.OAK_LEAVES;
    private Material secondaryLeaf = Material.JUNGLE_LEAVES;

    public FractalBranch(World world, Vector begin, Vector end, double radius){
        this.finished = false;
        this.begin = begin;
        this.end = end;
        this.radius = radius;
        this.world = world;
    }

    public void build(double leaveRadiusMultiplier, double maxRadiusCutoff, double leafSpawnChance){
        FunctionIterator functionIterator = new FunctionIterator(world,this.begin, this.end, radius);
        for(Block b: functionIterator){
            if(b.isPassable() || b.getType() == primaryLeaf || b.getType() == secondaryLeaf || b.getType() == primaryMaterial){
                if(radius > 0.5){
                    b.setType(primaryMaterial);
                }else{
                    b.setType(secondaryMaterial);
                }
            }
        }
        if(getRadius() * leaveRadiusMultiplier < maxRadiusCutoff){
            growLeaves(end.toLocation(world), getRadius()*leaveRadiusMultiplier, leafSpawnChance);
        }
    }

    public void growLeaves(Location loc, double radius, double leafSpawnChance){
        for(int x = (int)radius*-1; x<radius+0.5; x++) {
            for (int y = (int)(radius*-0.5); y < radius + 0.5; y++) {
                for (int z = (int)radius * -1; z < radius + 0.5; z++) {
                    if(Math.random() > 1 - leafSpawnChance){
                        Block target = this.world.getBlockAt(loc.clone().add(x,y,z));
                        if(loc.distance(target.getLocation()) <= radius + 0.5){
                            if(target.isPassable()){
                                if(new Random().nextBoolean()){
                                    target.setType(primaryLeaf);
                                }else{
                                    target.setType(secondaryLeaf);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public FractalBranch branch(Vector axis, double angle, double angleRange, double minLenRed, double maxLenRed, double minRadRed, double maxRadRed){
        this.finished = true;
        Vector dir = getDirection();
        double mag = TreeMathUtil.getMagnitude(dir);
        dir = dir.rotateAroundAxis(axis, angle);
        dir = TreeMathUtil.setMagnitude(dir, mag);
        double dirRed = TreeMathUtil.map(Math.abs(angle), 0, angleRange, minLenRed, maxLenRed);
        dir.multiply(dirRed);
        Vector newEnd = new Vector(this.end.getX() + dir.getX(), this.end.getY() + dir.getY(), this.end.getZ() + dir.getZ());
        double newRadius = TreeMathUtil.map(Math.abs(angle), 0, angleRange, minRadRed * radius, maxRadRed * radius);
        FractalBranch b = new FractalBranch(this.world, this.end, newEnd, newRadius);
        if(b.getLen() < 1) {
            return null;
        }else{
            return b;
        }
    }


    public Vector getDirection(){
        return this.end.clone().subtract(this.begin);
    }

    public double getLen(){
        return Math.sqrt( (this.begin.getX() - this.end.getX()) * (this.begin.getX() - this.end.getX()) +
                (this.begin.getY() - this.end.getY()) * (this.begin.getY() - this.end.getY()) +
                (this.begin.getZ() - this.end.getZ()) * (this.begin.getZ() - this.end.getZ()));
    }

}
