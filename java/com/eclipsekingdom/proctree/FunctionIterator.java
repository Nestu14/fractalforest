package com.eclipsekingdom.proctree;
import com.eclipsekingdom.proctree.functions.BranchFunction;
import com.eclipsekingdom.proctree.util.TreeMathUtil;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class FunctionIterator extends ArrayList<Block> {


    //vector
    public FunctionIterator(World world, Vector begin, Vector end, double radius){

        Vector direction = new Vector(end.getX() - begin.getX(), end.getY() - begin.getY(), end.getZ() - begin.getZ());
        Vector dirNorm = TreeMathUtil.setMagnitude(direction, 1);
        Vector rPerp = TreeMathUtil.getRandomPerpVector(direction);
        Vector rPerpNorm = TreeMathUtil.setMagnitude(rPerp, 1);



        for (double i = 0; i < Math.abs(TreeMathUtil.getMagnitude(direction)); i += 0.5) {
            for (double r = 0; r < Math.PI * 2; r += Math.PI / 32) {
                for (double j = 0; j < radius; j += 0.5) {
                    Vector up = dirNorm.clone().multiply(i);
                    Vector out = TreeMathUtil.setMagnitude(rPerpNorm.rotateAroundAxis(direction,r), j);
                    Vector point = world.getBlockAt(begin.clone().add(up).add(out).toLocation(world)).getLocation().add(0.5, 0, 0.5).toVector();
                    if (isInRadius(point, begin, direction, radius)) {
                        Block block = world.getBlockAt(point.toLocation(world));
                        if (!contains(block)) add(block);
                    }
                }
            }
        }


        /*
        Vector direction = new Vector(end.getX() - begin.getX(), end.getY() - begin.getY(), end.getZ() - begin.getZ() );

        Vector rPerp = TreeMathUtil.getRandomPerpVector(direction);
        Vector rdPerp = TreeMathUtil.getPerpVector(rPerp, direction);


        rPerp = TreeMathUtil.setMagnitude(rPerp, radius);
        rdPerp = TreeMathUtil.setMagnitude(rdPerp,radius);


        Vector c1 = rPerp.clone().add(rdPerp);
        Vector c2 = c1.clone().multiply(-1);
        c2 = c2.add(direction);

        c1 = c1.add(begin);
        c2 = c2.add(begin);

        //this would work if I just loop from minx - radiusm to maxx + radius (for y and z also)

        for(double x = min(c1.getX(), c2.getX()); x < max(c1.getX(), c2.getX()); x +=0.5){
            for(double y = min(c1.getY(), c2.getY()); y < max(c1.getY(), c2.getY()); y +=0.5){
                for(double z = min(c1.getZ(), c2.getZ()); z < max(c1.getZ(), c2.getZ()); z +=0.5){
                    Vector point = world.getBlockAt(new Vector(x,y,z).toLocation(world)).getLocation().add(0.5,0,0.5).toVector();
                    if(isBetween(point, begin, direction, radius)){
                        Block block = world.getBlockAt(point.toLocation(world));
                        if(!contains(block)) add(block);
                    }
                }
            }
        }

        */

    }


    /*

    when iterating with a function, check if every block placed within length, because the last section to be added will overshoot

    will undershoot atm
     */

    //function
    public FunctionIterator(World world, Vector begin, double len, Vector rX, Vector rY, double radius, BranchFunction function){


        double prevX = 0;
        double prevY = function.f(prevX);
        double x = 1;
        double y = function.f(x);
        double cLen = distFromOrigin(x, y);



        while(cLen < len){


            //segment
            Vector sBegin = begin.clone().add(translatePointOnPlane(prevX, prevY, rX, rY));
            Vector sEnd = begin.clone().add(translatePointOnPlane(x, y, rX, rY));

            for(Block b: new FunctionIterator(world, sBegin, sEnd, radius)){
                if(!contains(b)){
                    add(b);
                }
            }


            //itterate
            prevX = x;
            prevY = y;
            x ++;
            y = function.f(x);
            cLen = distFromOrigin(x, y);

        }

    }


    // utility

    private Vector translatePointOnPlane(double x, double y, Vector rX, Vector rY){

        rX = TreeMathUtil.setMagnitude(rX, 1);
        rY = TreeMathUtil.setMagnitude(rY, 1);

        return rX.clone().multiply(x).add(rY.clone().multiply(y));

    }

    private double distFromOrigin(double x, double y){
        return Math.sqrt(x*x + y*y);
    }

    private double min(double o1, double o2){
        if(o1 < o2){
            return o1;
        }else{
            return o2;
        }
    }

    private double max(double o1, double o2){
        if(o1 < o2){
            return o2;
        }else{
            return o1;
        }
    }

    private boolean isInRadius(Vector M0, Vector M1, Vector s, double radius){
        Vector M0M1 = new Vector(M1.getX() - M0.getX(), M1.getY() - M0.getY(),M1.getZ() - M0.getZ());
        Vector M0M1xs = M0M1.crossProduct(s);
        double d = Math.abs(TreeMathUtil.getMagnitude(M0M1xs)/TreeMathUtil.getMagnitude(s));
        return d < radius;
    }



}
