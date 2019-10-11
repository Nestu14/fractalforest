package com.eclipsekingdom.fractalforest.trees;

import org.bukkit.util.Vector;

public class LeafCluster {

    Vector center;
    double radius;

    public LeafCluster(Vector center, double radius){
        this.center = center;
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Vector getCenter() {
        return center.clone();
    }

    public void setCenter(Vector center) {
        this.center = center;
    }
}
