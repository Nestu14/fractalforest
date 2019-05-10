package com.eclipsekingdom.proctree.functions;

public abstract class BranchFunction {

    private String functionString;


    public BranchFunction(String functionString){
        this.functionString = functionString;
    }

    public String toString(){
        return functionString;
    }

    public abstract double f(double x);

}
