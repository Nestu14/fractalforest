package com.eclipsekingdom.proctree.trees.fractal.species;

import com.eclipsekingdom.proctree.functions.ExponentialBranchFunction;
import com.eclipsekingdom.proctree.trees.fractal.FractalBranch;
import com.eclipsekingdom.proctree.trees.fractal.FractalRoot;
import com.eclipsekingdom.proctree.trees.fractal.FractalTree;
import com.eclipsekingdom.proctree.util.TreeMathUtil;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class OakFractalMixed extends FractalTree {

    private World world;
    private Location location;

    public OakFractalMixed(Location location) {
        super(location);
        setSpecies("OakFractal4B");
    }

    @Override
    public void show(){
        for(int i = 0; i<6; i++){
            generate();
        }
        for(int i=0; i<5;i++){

            Vector rY = getTrunk().getDirection();
            Vector rX = TreeMathUtil.getRandomPerpVector(rY).rotateAroundAxis(rY,TreeMathUtil.random(0,Math.PI * 2));
            addRoot(new FractalRoot(world, 4.7, new ExponentialBranchFunction("y=1(x)^1.3", 1, 1.2), rX, rY, location.toVector().subtract(rX.clone().multiply(3)),0.8));

        }
        for(FractalBranch b: getBranches()){
            b.build(3.3,3.5, 0.91);
        }
        for(FractalRoot r: getRoots()){
            r.build();
        }
    }

    @Override
    public void generate(){
        super.splitMixed(2, 4,Math.PI/3, 0.88, 0.54, 0.88, 0.54, 0);
    }


    @Override
    public FractalBranch generateTrunk(Location location){
        this.world = location.getWorld();
        this.location = location;
        double rootHeight = TreeMathUtil.random(6,9);
        double offBeginX = TreeMathUtil.random(0,1);
        double offBeginY = TreeMathUtil.random(0,1);
        double offEndX = TreeMathUtil.random(0,1);
        double offEndY = TreeMathUtil.random(0,1);
        return new FractalBranch(location.getWorld(), location.clone().add(offBeginX,0,offBeginY).toVector(), location.clone().add(offEndX, rootHeight,offEndY).toVector(), rootHeight/5);
    }

}
