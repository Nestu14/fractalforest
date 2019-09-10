package com.eclipsekingdom.proctree.trees.growth;

import com.eclipsekingdom.proctree.trees.fractal.Branch;
import com.eclipsekingdom.proctree.trees.Leaves;
import com.eclipsekingdom.proctree.trees.Root;

import java.util.List;

public interface IGrowth {
    void generateTree();
    Branch getTrunk();
    List<Branch> getBranches();
    List<Root> getRoots();
    List<Leaves> getLeaves();

}
