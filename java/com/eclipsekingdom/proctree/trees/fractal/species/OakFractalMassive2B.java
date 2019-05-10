package com.eclipsekingdom.proctree.trees.fractal.species;

import com.eclipsekingdom.proctree.trees.fractal.FractalBranch;
import com.eclipsekingdom.proctree.trees.fractal.FractalTree;
import com.eclipsekingdom.proctree.util.TreeMathUtil;
import org.bukkit.Location;

public class OakFractalMassive2B extends FractalTree {

    public OakFractalMassive2B(Location location) {
        super(location);
        setSpecies("OakFractalMassive2B");
    }

    @Override
    public void show(){
        for(int i = 0; i<5; i++){
            generate();
        }
        for(FractalBranch b: getBranches()){
            b.build(3.5,3.5, 0.89);
            //b.buildWithLeaves(3.5,3.5, 0.89);
        }
    }

    @Override
    public void generate(){
        super.split(2,Math.PI/3, 0.99, 0.54, 0.99, 0.45, 0);
    }


    @Override
    public FractalBranch generateTrunk(Location location){
        double rootHeight = TreeMathUtil.random(8,12);
        return new FractalBranch(location.getWorld(), location.toVector(), location.add(0, rootHeight,0).toVector(), 3);
    }


}
