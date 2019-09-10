package com.eclipsekingdom.proctree.trees;

import org.bukkit.util.Vector;

public class Leaves {

    Vector center;
    double radius;

    public Leaves(Vector center, double radius){
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
