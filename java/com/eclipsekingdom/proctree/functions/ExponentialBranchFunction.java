package com.eclipsekingdom.proctree.functions;

public class ExponentialBranchFunction extends BranchFunction {

    //y = ax^b
    private double a;
    private double b;
    private double sX;

    public ExponentialBranchFunction(String functionString, double a, double b) {
        super(functionString);
        this.a = a;
        this.b = b;

    }

    @Override
    public double f(double x) {
        return (Math.pow(x, b) * a);
    }

}
