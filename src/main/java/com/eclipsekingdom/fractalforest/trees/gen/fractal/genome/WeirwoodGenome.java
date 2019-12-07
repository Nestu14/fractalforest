package com.eclipsekingdom.fractalforest.trees.gen.fractal.genome;

import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.gene.*;
import com.eclipsekingdom.fractalforest.util.math.functions.Exponential;
import com.eclipsekingdom.fractalforest.util.math.range.Bounds;

public class WeirwoodGenome extends Genome {

    public WeirwoodGenome() {
        super(new ClumpGene(0.05),
                new SplitGene(4,5),
                new AngleGene(new Bounds(0, Math.PI/3)),
                new DecayGene(new Bounds(0.22,0.33)),
                new TrunkGene(new Bounds(7,8), new Bounds(0.7,1), new Bounds(-0.7,0.7)),
                new LeafGene(0.69, 0.9),
                new RootGene(5,7,new Exponential(1,1.2), new Bounds(0.5,0.7),new Bounds(3,7)));
    }

}
