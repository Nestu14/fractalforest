package com.eclipsekingdom.proctree.util.shape;

import com.eclipsekingdom.proctree.util.TreeMath;
import org.bukkit.util.Vector;

public class RectangularSolid implements IShape {
    
    private Vector min;
    private Vector max;
    
    public RectangularSolid(Vector min, Vector max){
        correct(min, max);
        this.min = min;
        this.max = max;
    }

    public static void correct(Vector one, Vector two){

        double temp;

        if(two.getX() < one.getX()){
            temp = two.getX();
            two.setX(one.getX());
            one.setX(temp);
        }

        if(two.getY() < one.getY()){
            temp = two.getY();
            two.setY(one.getY());
            one.setY(temp);
        }


        if(two.getZ() < one.getZ()){
            temp = two.getZ();
            two.setZ(one.getZ());
            one.setZ(temp);
        }

    }
    
    @Override
    public boolean contains(Vector point) {
        return (point.getX() >= min.getX() && point.getX() <= max.getX() &&
                point.getY() >= min.getY() && point.getY() <= max.getY() &&
                point.getZ() >= min.getZ() && point.getZ() <= max.getZ());
    }

    @Override
    public Vector nextPoint() {
        return new Vector(TreeMath.randomDouble(min.getX(), max.getX()),
                TreeMath.randomDouble(min.getY(), max.getY()),
                TreeMath.randomDouble(min.getZ(), max.getZ()));
    }
}
