package com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.type;

import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.Genome;
import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.gene.*;
import com.eclipsekingdom.fractalforest.util.math.functions.Exponential;
import com.eclipsekingdom.fractalforest.util.math.range.Bounds;

public class MagnoliaGenome extends Genome {

    public MagnoliaGenome() {
        super(new ClumpGene(0),
                new SplitGene(3,4),
                new AngleGene(new Bounds(Math.PI/4, Math.PI /2)),
                new DecayGene(new Bounds(0.55,0.66)),
                new TrunkGene(new Bounds(3,4), new Bounds(0.5,0.5), new Bounds(-0.5,0.5)),
                new LeafGene(1, 3),
                new RootGene(0,0,new Exponential(1,2), new Bounds(0.4,0.5),new Bounds(1,2)));
    }

}
