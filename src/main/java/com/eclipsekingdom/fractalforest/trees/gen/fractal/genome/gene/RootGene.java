package com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.gene;

import com.eclipsekingdom.fractalforest.util.math.functions.Function;
import com.eclipsekingdom.fractalforest.trees.gen.Branch;
import com.eclipsekingdom.fractalforest.trees.gen.Root;
import com.eclipsekingdom.fractalforest.util.math.Plane;
import com.eclipsekingdom.fractalforest.util.math.TreeMath;
import com.eclipsekingdom.fractalforest.util.math.range.Bounds;
import org.bukkit.util.Vector;

public class RootGene implements IRootGene {

    private int min;
    private int max;
    private Bounds radiusBounds;
    private Function curve;
    private Bounds lengthBounds;

    public RootGene(int min, int max, Function curve, Bounds radiusBounds, Bounds lengthBounds){
        this.min = min;
        this.max = max;
        this.curve = curve;
        this.radiusBounds = radiusBounds;
        this.lengthBounds = lengthBounds;
    }

    @Override
    public Root next(Branch trunk) {
        Vector yAxis = trunk.getDirection();
        Vector xAxis = TreeMath.getRandomPerpVector(yAxis);
        Plane plane = new Plane(xAxis, yAxis);
        double length = lengthBounds.nextValue();
        return new Root(trunk.getBegin().subtract(xAxis.clone().multiply(length*0.75)), plane,length, radiusBounds.nextValue(),curve);
    }

    @Override
    public int nextAmount() {
        return TreeMath.randomInt(min,max);
    }


}
