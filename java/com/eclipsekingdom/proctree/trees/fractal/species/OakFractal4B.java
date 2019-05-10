package com.eclipsekingdom.proctree.trees.fractal.species;

import com.eclipsekingdom.proctree.trees.fractal.FractalBranch;
import com.eclipsekingdom.proctree.trees.fractal.FractalTree;
import com.eclipsekingdom.proctree.util.TreeMathUtil;
import org.bukkit.Location;

public class OakFractal4B extends FractalTree {

    public OakFractal4B(Location location) {
        super(location);
        setSpecies("OakFractal4B");
    }

    @Override
    public void show(){
        for(int i = 0; i<3; i++){
            generate();
        }
        for(FractalBranch b: getBranches()){
            b.build(3.5,2.5, 0.89);
        }
    }

    @Override
    public void generate(){
        super.split(4,Math.PI/3, 0.99, 0.22, 0.99, 0.22, 0);
    }


    @Override
    public FractalBranch generateTrunk(Location location){
        double rootHeight = TreeMathUtil.random(3,6);
        return new FractalBranch(location.getWorld(), location.toVector(), location.add(0, rootHeight,0).toVector(), 1);
    }

}
