package com.eclipsekingdom.proctree.trees.colinization;

import org.bukkit.util.Vector;

public class Leaf {

    public Vector position;
    public Branch closestBranch;

    public Leaf(Vector position){
        this.position = position;
    }

    public Vector getPosition() {
        return position.clone();
    }

    public void setPosition(Vector position) {
        this.position = position;
    }


    public Branch getClosestBranch() {
        return closestBranch;
    }

    public void setClosestBranch(Branch closestBranch) {
        this.closestBranch = closestBranch;
    }
}
