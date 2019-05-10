package com.eclipsekingdom.proctree.functions;

public class SineBranchFunction extends BranchFunction {

    // y = a sin(bx)
    private double a;
    private double b;

    public SineBranchFunction(String functionString, double a, double b) {
        super(functionString);
        this.a = a;
        this.b = b;
    }

    @Override
    public double f(double x) {
        return a * Math.sin(b * x);
    }
}
