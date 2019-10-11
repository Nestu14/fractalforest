package com.eclipsekingdom.fractalforest.util.functions;

public class Sine extends Function {

    private double a;
    private double b;

    public Sine(double a, double b) {
        super("y = " + a + "* sin("+b+" * x)"); // y = a sin(bx)
        this.a = a;
        this.b = b;
    }

    @Override
    public double f(double x) {
        return a * Math.sin(b * x);
    }

}
