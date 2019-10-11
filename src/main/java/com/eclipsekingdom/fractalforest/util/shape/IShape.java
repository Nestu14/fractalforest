package com.eclipsekingdom.fractalforest.util.shape;


import org.bukkit.util.Vector;

public interface IShape {
    boolean contains(Vector point);
    Vector nextPoint();
}
