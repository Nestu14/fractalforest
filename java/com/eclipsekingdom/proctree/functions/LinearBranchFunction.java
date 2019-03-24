package com.eclipsekingdom.proctree.functions;

public class LinearBranchFunction extends BranchFunction {

    // y = ax + 0

    private double coX;

    public LinearBranchFunction(String functionString, double coX) {
        super(functionString);
        this.coX = coX;
    }

    @Override
    public int f(int x){
        return (int)(coX*x);
    }

    @Override
    public int g(int y){
        return (int)(y/coX);
    }

}
