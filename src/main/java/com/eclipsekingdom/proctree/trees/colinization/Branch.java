package com.eclipsekingdom.proctree.trees.colinization;

import org.bukkit.util.Vector;

public class Branch {

    private Branch parent;
    private Vector growDirection;
    private Vector originalGrowDirection;
    private int growCount;
    private Vector position;

    public Branch(Branch parent, Vector position, Vector growDirection){
        this.parent = parent;
        this.position = position;
        this.growDirection = growDirection;
        this.originalGrowDirection = growDirection;
    }

    public void reset(){
        growCount = 0;
        growDirection = originalGrowDirection;
    }

    public Branch getParent() {
        return parent;
    }

    public Vector getGrowDirection() {
        return growDirection.clone();
    }

    public void setGrowDirection(Vector growDirection) {
        this.growDirection = growDirection;
    }

    public Vector getOriginalGrowDirection() {
        return originalGrowDirection.clone();
    }

    public void setOriginalGrowDirection(Vector originalGrowDirection) {
        this.originalGrowDirection = originalGrowDirection;
    }

    public int getGrowCount() {
        return growCount;
    }

    public void setGrowCount(int growCount) {
        this.growCount = growCount;
    }

    public Vector getPosition() {
        return position.clone();
    }

    public Vector getEnd(int length){
        return getPosition().add(getGrowDirection().multiply(length));
    }
}
