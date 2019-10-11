package com.eclipsekingdom.fractalforest.util.functions;

import com.eclipsekingdom.fractalforest.util.Point2D;

public abstract class Function {

    private String functionString;

    public Function(String functionString){
        this.functionString = functionString;
    }

    @Override
    public String toString(){
        return functionString;
    }

    public abstract double f(double x);

    public Point2D pointAt(double x){
        return new Point2D(x, f(x));
    }

}
