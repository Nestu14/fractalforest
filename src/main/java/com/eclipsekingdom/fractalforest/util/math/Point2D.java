package com.eclipsekingdom.fractalforest.util.math;

public class Point2D {

    private double x;
    private double y;

    public Point2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distance(Point2D point){
        double xDif = x-point.x;
        double yDif = y-point.y;
        return Math.sqrt(xDif*xDif + yDif*yDif);
    }

}
