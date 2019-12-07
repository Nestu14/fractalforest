package com.eclipsekingdom.fractalforest.util.math.shape;


import org.bukkit.util.Vector;

public interface IShape {
    boolean contains(Vector point);
    Vector nextPoint();
}
