package com.eclipsekingdom.fractalforest.trees.fractal;

import com.eclipsekingdom.fractalforest.trees.LeafCluster;
import com.eclipsekingdom.fractalforest.trees.Branch;
import com.eclipsekingdom.fractalforest.trees.Root;
import com.eclipsekingdom.fractalforest.trees.fractal.genome.IGenome;
import com.eclipsekingdom.fractalforest.trees.fractal.genome.gene.*;
import com.eclipsekingdom.fractalforest.util.TreeMath;
import org.bukkit.util.Vector;

import java.util.*;

public class FractalGrowthPattern {

    private ISplitGene splitGene;
    private IClumpGene clumpGene;
    private IAngleGene angleGene;
    private IDecayGene decayGene;
    private ITrunkGene trunkGene;
    private ILeafGene leafGene;
    private IRootGene rootGene;
    private int iterations = 0;

    private Branch trunk;
    private List<Root> roots = new ArrayList<>();
    private List<List<Branch>> branches = new ArrayList<>();
    private List<List<LeafCluster>> leafClusters = new ArrayList<>();

    public FractalGrowthPattern(IGenome genome){
        this.splitGene = genome.getSplit();
        this.clumpGene = genome.getClump();
        this.angleGene = genome.getAngle();
        this.decayGene = genome.getDecay();
        this.trunkGene = genome.getTrunk();
        this.leafGene = genome.getLeaf();
        this.rootGene = genome.getRoot();
        generateBlueprint();
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

    private void generateBlueprint() {
        trunk = trunkGene.next();
        LeafCluster trunkLeafCluster = getCluster(trunk);
        List<LeafCluster> phaseLeafCluster = new ArrayList<>();
        if(trunkLeafCluster != null){
            phaseLeafCluster.add(trunkLeafCluster);
        }
        leafClusters.add(phaseLeafCluster);
        List<Branch> prevLayer = Collections.singletonList(trunk);
        branches.add(null);
        for(int i = 0; i<6; i++){
            prevLayer = iterate(prevLayer);
            branches.add(prevLayer);
            List<LeafCluster> phaseLeafClusters = new ArrayList<>();
            for(Branch branch: prevLayer){
                LeafCluster branchLeafCluster = getCluster(branch);
                if(branchLeafCluster != null){
                    phaseLeafClusters.add(branchLeafCluster);
                }
            }
            leafClusters.add(phaseLeafClusters);
        }
        for(int i=0; i<rootGene.nextAmount();i++){
            roots.add(rootGene.next(trunk));
        }
    }

    private LeafCluster getCluster(Branch branch){
        if(branch.getRadius() <= leafGene.getMaxBranchRadius()){
            return new LeafCluster(branch.getEnd(), branch.getRadius()*leafGene.getRadiusMultiplier());
        }else{
            return null;
        }
    }

    public List<Root> getRoots(){
        return roots;
    }

    public Branch getTrunk(){
        return trunk;
    }

    public List<List<Branch>> getBranches(){
        return branches;
    }

    public List<List<LeafCluster>> getLeafClusters(){
        return leafClusters;
    }

}
