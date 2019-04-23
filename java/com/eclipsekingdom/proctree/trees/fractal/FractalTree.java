package com.eclipsekingdom.proctree.trees.fractal;

import com.eclipsekingdom.proctree.util.TreeMathUtil;
import org.bukkit.Location;
import org.bukkit.Material;
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

    private FractalBranch root;
    public FractalBranch getRoot() {
        return root;
    }

    private ArrayList<FractalBranch> branches = new ArrayList<>();
    public ArrayList<FractalBranch> getBranches() {
        return branches;
    }
    public void addBranch(FractalBranch branch){
        branches.add(branch);
    }

    private double branchIterations = 0;
    public double getBranchIterations(){
        return branchIterations;
    }
    public void increment(){
        branchIterations++;
    }

    public FractalTree(Location location){
        this.root = generateRoot(location);
        addBranch(root);
    }

    public abstract void show();

    public abstract void generate();

    public abstract FractalBranch generateRoot(Location location);


    /* ---  Single Iteration Methods  --- */

    public void split(int splitNumber, boolean splitOnSameLine,double angleRange, double minLenRed, double maxLenRed, double minRadRed, double maxRadRed){
        for(int i = getBranches().size() - 1; i>=0; i--){
            if(splitNumber == 2){
                splitBranchTwo(branches.get(i), angleRange, minLenRed, maxLenRed, minRadRed, maxRadRed, splitOnSameLine);
            }else if(splitNumber == 3){
                splitBranchThree(branches.get(i), angleRange, minLenRed, maxLenRed, minRadRed, maxRadRed, splitOnSameLine);
            }
        }
        increment();
    }

    public void splitWR(int splitNumber, boolean splitOnSameLine, double minThickness, double maxThickness, double minAngle, double maxAngle, double minLenRed, double maxLenRed, double minRadRed, double maxRadRed){ //weighted angle ranges
        for(int i = getBranches().size() - 1; i>=0; i--){
            double angleRange = TreeMathUtil.map(branches.get(i).getRadius(), minThickness, maxThickness, maxAngle, minAngle);
            split(splitNumber, splitOnSameLine, angleRange, minLenRed, maxLenRed, minRadRed, maxRadRed);
        }
        increment();
    }


    /* ---  Single Branch Split methods  --- */
    private void splitBranchTwo(FractalBranch branch, double angleRange, double minLenRed, double maxLenRed, double minRadRed, double maxRadRed, boolean splitOnSameLine){
        if(!branch.isFinished()){
            Vector rPerp = TreeMathUtil.getRandomPerpVector(branch);
            Vector axisA;
            Vector axisB;
            double angleA;
            double angleB;
            if(splitOnSameLine){
                axisA = rPerp;
                axisB = rPerp;
                angleA = TreeMathUtil.random(-1 * angleRange, 0);
                angleB = TreeMathUtil.random(0, angleRange);
            }else{
                Vector startingA = rPerp;
                Vector startingB = rPerp.clone().rotateAroundAxis(branch.getDirection(), Math.PI);
                axisA = startingA.rotateAroundAxis(branch.getDirection(), Math.PI);
                axisB = startingB.rotateAroundAxis(branch.getDirection(), Math.PI);
                angleA = TreeMathUtil.random(0, angleRange);
                angleB = TreeMathUtil.random(0, angleRange);
            }
            FractalBranch ba = branch.branch(axisA, angleA, angleRange, minLenRed, maxLenRed, minRadRed, maxRadRed);
            FractalBranch bb = branch.branch(axisB, angleB, angleRange, minLenRed, maxLenRed, minRadRed, maxRadRed);
            if (ba != null && bb != null) {
                addBranch(ba);
                addBranch(bb);
            }
        }
    }

    private void splitBranchThree(FractalBranch branch, double angleRange, double minLenRed, double maxLenRed, double minRadRed, double maxRadRed, boolean splitOnSameLine){
        if(!branch.isFinished()){
            Vector rPerp = TreeMathUtil.getRandomPerpVector(branch);
            Vector axisA;
            Vector axisB;
            Vector axisC;
            double angleA;
            double angleB;
            double angleC;
            if(splitOnSameLine){
                axisA = rPerp;
                axisB = rPerp;
                axisC = rPerp;
                angleA = TreeMathUtil.random(-1 * angleRange, -1 * angleRange/3);
                angleB = TreeMathUtil.random(-1 * angleRange/3, angleRange/3);
                angleC = TreeMathUtil.random(angleRange/3, angleRange);
            }else{
                //clump branches together by rotating by less ******
                Vector startingA = rPerp;
                Vector startingB = rPerp.clone().rotateAroundAxis(branch.getDirection(), 1 * Math.PI * 2 / 3);
                Vector startingC = rPerp.clone().rotateAroundAxis(branch.getDirection(), 2 * Math.PI * 2 / 3);

                axisA = startingA.rotateAroundAxis(branch.getDirection(), Math.PI * 2 / 3);
                axisB = startingB.rotateAroundAxis(branch.getDirection(), Math.PI * 2 / 3);
                axisC = startingC.rotateAroundAxis(branch.getDirection(), Math.PI * 2 / 3);
                angleA = TreeMathUtil.random(0, angleRange);
                angleB = TreeMathUtil.random(0, angleRange);
                angleC = TreeMathUtil.random(0, angleRange);
            }
            FractalBranch ba = branch.branch(axisA, angleA, angleRange, minLenRed, maxLenRed, minRadRed, maxRadRed);
            FractalBranch bb = branch.branch(axisB, angleB, angleRange, minLenRed, maxLenRed, minRadRed, maxRadRed);
            FractalBranch bc = branch.branch(axisC, angleC, angleRange, minLenRed, maxLenRed, minRadRed, maxRadRed);
            if (ba != null && bb != null && bc != null) {
                addBranch(ba);
                addBranch(bb);
                addBranch(bc);
            }
        }
    }

}







