package com.eclipsekingdom.fractalforest.util;

import com.eclipsekingdom.fractalforest.util.range.Range;
import org.bukkit.util.Vector;

import java.util.Random;

public class TreeMath {

    public static Random random = new Random();

    public static Vector getRandomPerpVector(Vector v){
        Vector randomVector = new Vector(randomDouble(-1,1), randomDouble(-1,1), randomDouble(-1,1));
        return randomVector.subtract(v.clone().multiply(randomVector.dot(v) / v.dot(v)));
    }

    public static Vector getPerpVector(Vector v1, Vector v2){
        double x = v1.getY()*v2.getZ() - v1.getZ()*v2.getY();
        double y = v1.getZ()*v2.getX() - v1.getX()*v2.getZ();
        double z = v1.getX()*v2.getY() - v1.getY()*v2.getX();
        return new Vector(x,y,z);
    }

    public static double randomDouble(double min, double max){
        return min + (max - min) * random.nextDouble();
    }

    public static int randomInt(int min, int max){
        return random.nextInt(max - min + 1) + min;
    }

    public static double map(double value, Range oldRange, Range newRange){
        double oldMagnitude = oldRange.asDouble();
        if(oldMagnitude != 0){
            double newMagnitude = newRange.asDouble();
            return (((value - oldRange.getMin()) * newMagnitude) / oldMagnitude) + newRange.getMin();
        }else{
            return value;
        }
    }

    public static double getMagnitude(Vector v){
        return Math.sqrt(v.getX()*v.getX() + v.getY()*v.getY() + v.getZ()*v.getZ());
    }

    public static Vector setMagnitude(Vector v, double mag){
        double s = Math.sqrt((mag*mag)/(v.getX()*v.getX()+v.getY()*v.getY()+v.getZ()*v.getZ()));
        return v.clone().multiply(s);
    }

}
