package com.eclipsekingdom.proctree.functions;

public class LinearBranchFunction extends BranchFunction {

    // y = ax + 0

    private double coX;

    public LinearBranchFunction(String functionString, double coX) {
        super(functionString);
        this.coX = coX;
    }

    @Override
    public double f(double x){
        return (int)(coX*x);
    }

}
