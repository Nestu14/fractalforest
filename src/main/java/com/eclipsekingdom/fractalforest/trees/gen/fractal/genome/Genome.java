package com.eclipsekingdom.fractalforest.trees.gen.fractal.genome;

import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.gene.*;
import com.eclipsekingdom.fractalforest.util.Scale;

public class Genome implements IGenome {

    private IClumpGene clumpGene;
    private ISplitGene splitGene;
    private IAngleGene angleGene;
    private IDecayGene decayGene;
    private ITrunkGene trunkGene;
    private ILeafGene leafGene;
    private IRootGene rootGene;

    public Genome(IClumpGene clumpGene, ISplitGene splitGene, IAngleGene angleGene, IDecayGene decayGene, ITrunkGene trunkGene, ILeafGene leafGene, IRootGene rootGene) {
        this.clumpGene = clumpGene;
        this.splitGene = splitGene;
        this.angleGene = angleGene;
        this.decayGene = decayGene;
        this.trunkGene = trunkGene;
        this.leafGene = leafGene;
        this.rootGene = rootGene;
    }

    @Override
    public IClumpGene getClump() {
        return clumpGene;
    }

    @Override
    public ISplitGene getSplit() {
        return splitGene;
    }

    @Override
    public IAngleGene getAngle() {
        return angleGene;
    }

    @Override
    public IDecayGene getDecay() {
        return decayGene;
    }

    @Override
    public ITrunkGene getTrunk() {
        return trunkGene;
    }

    @Override
    public ILeafGene getLeaf() {
        return leafGene;
    }

    @Override
    public IRootGene getRoot() {
        return rootGene;
    }


}
