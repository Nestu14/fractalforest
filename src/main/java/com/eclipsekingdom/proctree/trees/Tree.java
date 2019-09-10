package com.eclipsekingdom.proctree.trees;

import com.eclipsekingdom.proctree.trees.fractal.Branch;
import com.eclipsekingdom.proctree.trees.growth.IGrowth;
import com.eclipsekingdom.proctree.trees.theme.ITheme;

import java.util.List;

public class Tree {

    private ITheme theme;
    private IGrowth growth;
    private Branch trunk;
    private List<Branch> branches;
    private List<Root> roots;
    private List<Leaves> leaves;

    public Tree(ITheme theme, IGrowth growth){
        this.theme = theme;
        this.growth = growth;
        growAbstractTree();
    }

    public ITheme getTheme() {
        return theme;
    }

    public void setTheme(ITheme theme) {
        this.theme = theme;
    }

    public IGrowth getGrowth(){
        return growth;
    }

    public void setGrowth(IGrowth IGrowth){
        this.growth = IGrowth;
    }

    public void reGenerate(){
        growAbstractTree();
    }

    private void growAbstractTree(){
        growth.generateTree();
        this.trunk = growth.getTrunk();
        this.branches = growth.getBranches();
        this.roots = growth.getRoots();
        this.leaves = growth.getLeaves();
    }

    public Branch getTrunk() {
        return trunk;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public List<Root> getRoots() {
        return roots;
    }

    public List<Leaves> getLeaves() {
        return leaves;
    }

}
