package com.eclipsekingdom.proctree.trees.colinization;

import com.eclipsekingdom.proctree.trees.Leaves;
import com.eclipsekingdom.proctree.util.shape.IShape;
import com.eclipsekingdom.proctree.util.shape.RectangularSolid;
import org.bukkit.util.Vector;

import java.util.*;

public class SpaceColinizationGrowth {

    private boolean finished = false;
    private Vector origin = new Vector(0,0,0);
    private int leafCount;
    private int trunkHeight;
    private int treeWidth;
    private int treeHeight;
    private int minDistance;
    private int maxDistance;
    private int branchLength;

    private Branch root;
    private List<Leaf> leaves = new ArrayList<>();
    private Map<Vector, Branch> branches = new HashMap<>();

    private IShape crown;

    public SpaceColinizationGrowth(int leafCount, int branchLength, int trunkHeight, int treeWidth, int treeHeight, int minDistance, int maxDistance){
        this.leafCount = leafCount;
        this.branchLength = branchLength;
        this.trunkHeight = trunkHeight;
        this.treeWidth = treeWidth;
        this.treeHeight = treeHeight;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
    }

    private void generateTrunk() {
        root = new Branch(null, origin, new Vector(0, 1,0));
        branches.put(root.getPosition(), root);

        Branch current = new Branch(root, new Vector(origin.getX(), origin.getY() + branchLength, origin.getZ()), new Vector(0, 1,0));
        branches.put(current.getPosition(), current);

        while ((root.getPosition().distance(current.getPosition()) < trunkHeight)){
            Branch trunk = new Branch(current, new Vector(current.getPosition().getX(), current.getPosition().getY() + branchLength, current.getPosition().getZ()), new Vector(0, 1,0));
            branches.put(trunk.getPosition(), trunk);
            current = trunk;
        }
    }

    public void generateTree() {
        reset();
        generateCrown();
        generateTrunk();
        while(!finished){
            grow();
        }
    }

    private void reset(){
        root = null;
        leaves.clear();
        branches.clear();
    }

    private void generateCrown() {
        crown = new RectangularSolid(new Vector(origin.getX() - treeWidth/2d, origin.getY() + trunkHeight, origin.getZ() - treeWidth/2d), new Vector(origin.getX()+treeWidth/2d, origin.getY()+treeHeight+trunkHeight, origin.getZ() + treeWidth/2d));
        for (int i = 0; i < leafCount; i++) {
            Leaf leaf = new Leaf(crown.nextPoint());
            leaves.add(leaf);
        }
    }

    private void grow() {
        if (finished) return;

        if (leaves.size() == 0) {
            finished = true;
            return;
        }

        for (int i = 0; i < leaves.size(); i++) {
            boolean leafRemoved = false;

            leaves.get(i).setClosestBranch(null);
            Vector direction;

            for (Branch branch : branches.values()) {
                direction = leaves.get(i).getPosition().subtract(branch.getPosition());
                float distance = (float) Math.round(direction.length());
                direction.normalize();
                if (distance <= minDistance) {
                    leaves.remove(leaves.get(i));
                    i--;
                    leafRemoved = true;
                    break;
                } else if (distance <= maxDistance) {
                    if (leaves.get(i).getClosestBranch() == null) {
                        leaves.get(i).setClosestBranch(branch);
                    } else if (leaves.get(i).getPosition().distance(leaves.get(i).getClosestBranch().getPosition()) > distance) {
                        leaves.get(i).setClosestBranch(branch);
                    }
                }
            }

            if (!leafRemoved) {
                if (leaves.get(i).getClosestBranch() != null) {
                    Vector dir = leaves.get(i).getPosition().subtract(leaves.get(i).getClosestBranch().getPosition());
                    dir.normalize();
                    leaves.get(i).getClosestBranch().setGrowDirection(leaves.get(i).getClosestBranch().getGrowDirection().add(dir));
                    leaves.get(i).getClosestBranch().setGrowCount(leaves.get(i).getClosestBranch().getGrowCount() + 1);
                }
            }
        }

        HashSet<Branch> newBranches = new HashSet<>();
        for (Branch branch : branches.values()) {
            if (branch.getGrowCount() > 0) {
                Vector avgDirection = branch.getGrowDirection().multiply(1 / (double)branch.getGrowCount());
                avgDirection.normalize();
                Branch newBranch = new Branch(branch, branch.getPosition().add(avgDirection).multiply(branchLength), avgDirection);
                newBranches.add(newBranch);
                branch.reset();
            }else{
                if(branch.getPosition().getY() > origin.getY() + trunkHeight){
                    if(!positionToLeaves.containsKey(branch.getEnd(branchLength))){
                        positionToLeaves.put(branch.getEnd(branchLength),new Leaves(branch.getEnd(branchLength), 1));
                    }
                }
            }
        }

        boolean branchAdded = false;
        for (Branch branch : newBranches) {
            Branch existing = branches.get(branch.getPosition());
            if (existing == null) {
                branches.put(branch.getPosition(), branch);
                branchAdded = true;

            }
        }

        if (!branchAdded) {
            finished = true;
        }

    }

    public Collection<Branch> getBranches () {
        return branches.values();
    }

    public Map<Vector,Leaves> positionToLeaves = new HashMap<>();

    public Collection<Leaves> getLeaves(){
        return positionToLeaves.values();
    }

}
