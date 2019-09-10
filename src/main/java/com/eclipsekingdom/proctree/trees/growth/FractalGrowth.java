package com.eclipsekingdom.proctree.trees.growth;

import com.eclipsekingdom.proctree.trees.fractal.Branch;
import com.eclipsekingdom.proctree.trees.Leaves;
import com.eclipsekingdom.proctree.trees.Root;
import com.eclipsekingdom.proctree.trees.fractal.genome.IGenome;
import com.eclipsekingdom.proctree.trees.fractal.genome.gene.*;
import com.eclipsekingdom.proctree.util.TreeMath;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FractalGrowth implements IGrowth {

    private ISplitGene splitGene;
    private IClumpGene clumpGene;
    private IAngleGene angleGene;
    private IDecayGene decayGene;
    private ITrunkGene trunkGene;
    private ILeafGene leafGene;
    private IRootGene rootGene;
    private int iterations = 0;

    protected Branch trunk;
    protected List<Branch> branches = new ArrayList<>();
    protected List<Root> roots = new ArrayList<>();
    protected List<Leaves> leaves = new ArrayList<>();

    public FractalGrowth(IGenome genome){
        this.splitGene = genome.getSplit();
        this.clumpGene = genome.getClump();
        this.angleGene = genome.getAngle();
        this.decayGene = genome.getDecay();
        this.trunkGene = genome.getTrunk();
        this.leafGene = genome.getLeaf();
        this.rootGene = genome.getRoot();
    }

    public List<Branch> iterate(List<Branch> branchLayer){
        List<Branch> outerLayer = new ArrayList<>();
        for(Branch branch: branchLayer){
            outerLayer.addAll(splitBranch(branch, splitGene.next()));
        }

        iterations++;
        return outerLayer;
    }

    private List<Branch> splitBranch(Branch branch, int numBranches){
        List<Branch> childBranches = new ArrayList<>();
        Vector randomPerp = TreeMath.getRandomPerpVector(branch.getDirection());
        double totalRadians = Math.PI * 2 * (1 - clumpGene.next());
        for(int i = 0; i< numBranches; i++){
            double angle = angleGene.next();
            Vector starting = randomPerp.clone().rotateAroundAxis(branch.getDirection(), i * totalRadians / (double) numBranches);
            Vector axis = starting.clone().rotateAroundAxis(branch.getDirection(), totalRadians / (double) numBranches);
            double decay = decayGene.next(angle, angleGene.getBounds());
            Branch child = branch.split(axis, angle, decay);
            if(child != null){
                childBranches.add(child);
            }
        }
        return childBranches;
    }

    @Override
    public void generateTree() {
        reset();
        trunk = trunkGene.next();
        addLeaves(trunk);
        List<Branch> prevLayer = Collections.singletonList(trunk);
        branches.addAll(prevLayer);
        for(int i = 0; i<6; i++){
            prevLayer = iterate(prevLayer);
            for(Branch branch: prevLayer){
                branches.add(branch);
                addLeaves(branch);
            }
        }
        for(int i=0; i<rootGene.nextAmount();i++){
            roots.add(rootGene.next(trunk));
        }
    }

    private void reset(){
        trunk = null;
        branches.clear();
        roots.clear();
        leaves.clear();
    }

    private void addLeaves(Branch branch){
        if(branch.getRadius() <= leafGene.getMaxBranchRadius()){
            leaves.add(new Leaves(branch.getEnd(), branch.getRadius()*leafGene.getRadiusMultiplier()));
        }
    }

    public Branch getTrunk() {
        return trunk;
    }

    @Override
    public List<Branch> getBranches() {
        return branches;
    }

    @Override
    public List<Root> getRoots() {
        return roots;
    }

    @Override
    public List<Leaves> getLeaves() {
        return leaves;
    }
}
