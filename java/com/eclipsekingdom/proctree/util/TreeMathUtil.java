package com.eclipsekingdom.proctree.util;

import org.bukkit.util.Vector;

import java.util.Random;

public class TreeMathUtil {


    public static Vector getRandomPerpVector(Vector v){
        Vector rV = new Vector(Math.random(), Math.random(), Math.random());
        Vector pV = rV.subtract(v.clone().multiply(dot(rV, v) / dot(v, v)));
        return pV;
    }

    public static Vector getPerpVector(Vector v1, Vector v2){
        double x = v1.getY()*v2.getZ() - v1.getZ()*v2.getY();
        double y = v1.getZ()*v2.getX() - v1.getX()*v2.getZ();
        double z = v1.getX()*v2.getY() - v1.getY()*v2.getX();
        return new Vector(x,y,z);
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

    public static Vector setMagnitude(Vector v, double mag){
        double s = Math.sqrt((mag*mag)/(v.getX()*v.getX()+v.getY()*v.getY()+v.getZ()*v.getZ()));
        return v.clone().multiply(s);
    }

}
