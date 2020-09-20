package com.eclipsekingdom.fractalforest.trees.gen.fractal;

import com.eclipsekingdom.fractalforest.trees.gen.Branch;
import com.eclipsekingdom.fractalforest.trees.gen.LeafCluster;
import com.eclipsekingdom.fractalforest.trees.gen.Root;

import java.util.List;

public class FractalBlueprint {

    private Branch trunk;
    private List<Root> roots;
    private List<List<Branch>> branches;
    private List<List<LeafCluster>> leafClusters;

    public FractalBlueprint(Branch trunk, List<Root> roots, List<List<Branch>> branches, List<List<LeafCluster>> leafClusters){
        this.trunk = trunk;
        this.roots = roots;
        this.branches = branches;
        this.leafClusters = leafClusters;
    }

    public Branch getTrunk() {
        return trunk;
    }

    public List<Root> getRoots() {
        return roots;
    }

    public List<List<Branch>> getBranches() {
        return branches;
    }

    public List<List<LeafCluster>> getLeafClusters() {
        return leafClusters;
    }

}
