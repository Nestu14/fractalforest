package com.eclipsekingdom.fractalforest.trees.gen;

import com.eclipsekingdom.fractalforest.util.math.TreeMath;
import org.bukkit.util.Vector;

public class Branch {

    protected Vector begin;
    protected Vector end;
    protected double radius;

    public Branch(Vector begin, Vector end, double radius) {
        this.begin = begin;
        this.end = end;
        this.radius = radius;
    }

    public Vector getBegin() {
        return begin.clone();
    }

    public Vector getEnd() {
        return end.clone();
    }

    public double getRadius() {
        return radius;
    }

    public double getLength() {
        return begin.distance(end);
    }

    public Vector getDirection() {
        return this.end.clone().subtract(this.begin).normalize();
    }

    public enum Thickness {
        THIN, THICK
    }

    public Thickness getThickness() {
        return radius > 0.33 ? Thickness.THICK : Thickness.THIN;
    }

    public Branch split(Vector axis, double angle, double decay) {
        Vector dir = getDirection();
        dir = TreeMath.getRotatedVector(dir, axis, angle);
        if (decay > 1) decay = 1;
        dir.multiply(getLength() * (1 - decay));
        Vector newEnd = new Vector(end.getX() + dir.getX(), end.getY() + dir.getY(), end.getZ() + dir.getZ());
        Branch branch = new Branch(end, newEnd, radius * (1 - decay));
        if (branch.getLength() < 1) {
            return null;
        } else {
            return branch;
        }
    }

}
