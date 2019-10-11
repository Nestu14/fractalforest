package com.eclipsekingdom.fractalforest.trees.colinization;

import org.bukkit.util.Vector;

public class Dot {

    public Vector position;
    public Segment closestSegment;

    public Dot(Vector position){
        this.position = position;
    }

    public Vector getPosition() {
        return position.clone();
    }

    public void setPosition(Vector position) {
        this.position = position;
    }


    public Segment getClosestSegment() {
        return closestSegment;
    }

    public void setClosestSegment(Segment closestSegment) {
        this.closestSegment = closestSegment;
    }
}
