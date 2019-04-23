package com.eclipsekingdom.proctree.trees.fractal;

import com.eclipsekingdom.proctree.util.TreeMathUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;
import org.bukkit.World;

import java.util.Random;

public class FractalBranch {

    private Vector begin;
    public Vector getBegin(){
        return begin;
    }
    private Vector end;
    public Vector getEnd() {
        return end;
    }
    private double radius;
    public double getRadius(){
        return radius;
    }
    private boolean finished = false;
    public boolean isFinished(){
        return finished;
    }
    private World world;
    public World getWorld() {
        return world;
    }

    private Material primaryMaterial = Material.OAK_LOG;
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

    public void buildWithLeaves(double leaveRadiusMultiplier, double maxRadiusCutoff, double leafSpawnChance){
        if(getLen() < 1 || getLen() > 30 ) return;
        try{
            int count = 0;
            BlockIterator blockIterator = null;
            while(blockIterator == null && count < 5){
                count++;
                double add = 0.01;
                blockIterator = new BlockIterator(this.world,this.begin.clone().add(new Vector(add,add,add)),getDirection(),0.0D, (int)getLen());
            }

            while(blockIterator.hasNext()){
                Block currentBlock = blockIterator.next();
                //double dist = currentBlock.getLocation().getDirection().distance(getDirection()); //find distance of block to line created by end and begin
                if(currentBlock.isPassable() || currentBlock.getType() == Material.OAK_LEAVES || currentBlock.getType() == Material.JUNGLE_LEAVES){
                    if(radius > 0.5){
                        currentBlock.setType(primaryMaterial);
                    }else{
                        currentBlock.setType(secondaryMaterial);
                    }
                }
                if(!blockIterator.hasNext()){
                    if(getRadius() * leaveRadiusMultiplier < maxRadiusCutoff){
                        growLeaves(currentBlock.getLocation(), getRadius()*leaveRadiusMultiplier, leafSpawnChance);
                    }
                }
            }
        }catch (Exception e){
            Bukkit.getConsoleSender().sendMessage(e.getMessage());
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
        if(getLen() > 0.5){
            double mag = TreeMathUtil.getMagnitude(dir);
            dir = dir.rotateAroundAxis(axis, angle);
            TreeMathUtil.setMagnitude(dir, mag);
            double dirRed = TreeMathUtil.map(Math.abs(angle), 0, angleRange, minLenRed, maxLenRed);
            dir.multiply(dirRed);
            Vector newEnd = new Vector(this.end.getX() + dir.getX(), this.end.getY() + dir.getY(), this.end.getZ() + dir.getZ());
            double newRadius = TreeMathUtil.map(Math.abs(angle), 0, angleRange, minRadRed * radius, maxRadRed * radius);
            FractalBranch b = new FractalBranch(this.world, this.end, newEnd, newRadius);
            if(b.getLen() >= 1){
                return b;
            }else{
                return null;
            }
        }else{
            return null;
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



    private void print(String message){
        Bukkit.getServer().getConsoleSender().sendMessage(message);
    }

}
