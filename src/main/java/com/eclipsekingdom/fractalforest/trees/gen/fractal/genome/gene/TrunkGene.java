package com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.gene;

import com.eclipsekingdom.fractalforest.trees.gen.Branch;
import com.eclipsekingdom.fractalforest.util.math.TreeMath;
import com.eclipsekingdom.fractalforest.util.math.range.Bounds;
import org.bukkit.util.Vector;

public class TrunkGene implements ITrunkGene {

    private Bounds heightBounds;
    private Bounds radiusBounds;
    private Bounds offSetBounds;

    public TrunkGene(Bounds heightBounds, Bounds radiusBounds, Bounds offSetBounds){
        this.heightBounds = heightBounds;
        this.radiusBounds = radiusBounds;
        this.offSetBounds = offSetBounds;
    }

    @Override
    public Branch next() {
        double height = heightBounds.nextValue();
        double radius = TreeMath.map(height, heightBounds, radiusBounds);
        Vector begin = new Vector(0, 0, 0);
        Vector end = new Vector(offSetBounds.nextValue(), height, offSetBounds.nextValue());
        return new Branch(begin, end, radius);
    }

}
