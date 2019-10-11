package com.eclipsekingdom.fractalforest.util.functions;

public class Linear extends Function {

    private double m;
    private double b;

    public Linear(double m, double b) {
        super("y = " + m + " * x + " + b); // y = mx + b
        this.m = m;
        this.b = b;
    }

    @Override
    public double f(double x){
        return m * x + b;
    }

}
