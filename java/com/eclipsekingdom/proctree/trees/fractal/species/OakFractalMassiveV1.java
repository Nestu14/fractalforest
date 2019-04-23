package com.eclipsekingdom.proctree.trees.fractal.species;

import com.eclipsekingdom.proctree.trees.fractal.FractalBranch;
import com.eclipsekingdom.proctree.trees.fractal.FractalTree;
import com.eclipsekingdom.proctree.util.TreeMathUtil;
import org.bukkit.Location;

public class OakFractalMassiveV1 extends FractalTree {

    public OakFractalMassiveV1(Location location) {
        super(location);
        setSpecies("OakFractalMassiveV1");
    }

    @Override
    public void show(){
        for(int i = 0; i<11; i++){
            generate();
        }
        for(FractalBranch b: getBranches()){
            b.buildWithLeaves(3.5,3.5, 0.89);
        }
    }

    @Override
    public void generate(){
        super.split(3,false,Math.PI/3, 0.99, 0.54, 0.99, 0.45);
    }


    @Override
    public FractalBranch generateRoot(Location location){
        double rootHeight = TreeMathUtil.random(8,12);
        return new FractalBranch(location.getWorld(), location.toVector(), location.add(0, rootHeight,0).toVector(), 3);
    }


}
