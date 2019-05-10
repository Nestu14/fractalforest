package com.eclipsekingdom.proctree.trees.fractal;

import com.eclipsekingdom.proctree.util.TreeMathUtil;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public abstract class FractalTree {

    private String species = "tree";
    public void setSpecies(String species){
        this.species = species;
    }
    public String getSpecies() {
        return species;
    }

    private FractalBranch trunk;
    public FractalBranch getTrunk() {
        return trunk;
    }

    private ArrayList<FractalBranch> branches = new ArrayList<>();
    public ArrayList<FractalBranch> getBranches() {
        return branches;
    }
    public void addBranch(FractalBranch branch){
        branches.add(branch);
    }

    private ArrayList<FractalRoot> roots = new ArrayList<>();
    public ArrayList<FractalRoot> getRoots() {
        return roots;
    }
    public void addRoot(FractalRoot root){
        roots.add(root);
    }

    private double branchIterations = 0;
    public double getBranchIterations(){
        return branchIterations;
    }
    public void increment(){
        branchIterations++;
    }

    public FractalTree(Location location){
        this.trunk = generateTrunk(location);
        addBranch(trunk);
    }

    public abstract void show();

    public abstract void generate();

    public abstract FractalBranch generateTrunk(Location location);


    /* ---  Single Iteration Methods  --- */

    public void split(int splitNumber,double angleRange, double minLenRed, double maxLenRed, double minRadRed, double maxRadRed, double clumpFactor){
        for(int i = getBranches().size() - 1; i>=0; i--){
            splitBranch(branches.get(i), splitNumber, angleRange, minLenRed, maxLenRed, minRadRed, maxRadRed, clumpFactor);
        }
        increment();
    }

    public void splitMixed(int minSplit, int maxSplit, double angleRange, double minLenRed, double maxLenRed, double minRadRed, double maxRadRed, double clumpFactor){
        for(int i = branches.size() - 1; i >= 0; i--){
            int numBranches = (int)TreeMathUtil.random(minSplit, maxSplit + 1);
            splitBranch(branches.get(i), numBranches, angleRange, minLenRed, maxLenRed, minRadRed, maxRadRed, clumpFactor);
        }
    }


    public void splitMixedWR(int minSplit, int maxSplit, double value, double minValue, double maxValue, double minAngle, double maxAngle, double minLenRed, double maxLenRed, double minRadRed, double maxRadRed, double clumpFactor){
        for(int i = getBranches().size() - 1; i>=0; i--){
            double angleRange = TreeMathUtil.map(value, minValue, maxValue, maxAngle, minAngle);
            splitMixed(minSplit, maxSplit, angleRange, minLenRed, maxLenRed, minRadRed, maxRadRed, clumpFactor);
        }
        increment();
    }


    public void splitWR(int splitNumber, double value, double minValue, double maxValue, double minAngle, double maxAngle, double minLenRed, double maxLenRed, double minRadRed, double maxRadRed, double clumpFactor){
        for(int i = getBranches().size() - 1; i>=0; i--){
            double angleRange = TreeMathUtil.map(value, minValue, maxValue, maxAngle, minAngle);
            split(splitNumber, angleRange, minLenRed, maxLenRed, minRadRed, maxRadRed, clumpFactor);
        }
        increment();
    }



    /* ---  Single Branch Split methods  --- */

    private void splitBranch(FractalBranch branch, int numBranches, double angleRange, double minLenRed, double maxLenRed, double minRadRed, double maxRadRed, double clumpFactor){
        if(!branch.isFinished()){
            Vector rPerp = TreeMathUtil.getRandomPerpVector(branch.getDirection());
            double totalRad = Math.PI * 2 * (1 - clumpFactor);
            for(int i = 0; i< numBranches; i++){
                Vector starting = rPerp.clone().rotateAroundAxis(branch.getDirection(), i * totalRad / numBranches);
                Vector axis = starting.clone().rotateAroundAxis(branch.getDirection(), totalRad / numBranches);
                double angle = TreeMathUtil.random(0,angleRange);
                FractalBranch b = branch.branch(axis, angle, angleRange, minLenRed, maxLenRed, minRadRed, maxRadRed);
                if(b != null){
                    addBranch(b);
                }
            }
        }
    }

}







