package com.eclipsekingdom.proctree.util;

import com.eclipsekingdom.proctree.trees.fractal.FractalBranch;
import org.bukkit.util.Vector;

import java.util.Random;

public class TreeMathUtil {


    public static Vector getRandomPerpVector(FractalBranch branch){
        Vector rV = new Vector(Math.random(), Math.random(), Math.random());
        Vector bV = branch.getDirection();
        Vector pV = rV.subtract(bV.multiply(dot(rV, bV) / dot(bV, bV)));
        return pV;
    }

    public static double dot(Vector a, Vector b){
        return a.getX()*b.getX() + a.getY()*b.getY() + a.getZ()*b.getZ();
    }



    public static double random(double min, double max){
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }


    public static double map(double oldValue, double oldMin, double oldMax, double newMin, double newMax){
        double OldRange = (oldMax - oldMin);
        double newValue;
        if (OldRange == 0) {
            newValue = newMin;
        }else{
            double newRange = (newMax - newMin);
            newValue = (((oldValue - oldMin) * newRange) / OldRange) + newMin;
        }
        return newValue;
    }

    public static double getMagnitude(Vector v){
        return Math.sqrt(v.getX()*v.getX() + v.getY()*v.getY() + v.getZ()*v.getZ());
    }

    public static void setMagnitude(Vector v, double mag){
        double s = Math.sqrt((v.getX()*v.getX()+v.getY()*v.getY()+v.getZ()*v.getZ())/(mag*mag));
        v.multiply(s);
    }

}
