package com.eclipsekingdom.fractalforest.util.math.range;

import com.eclipsekingdom.fractalforest.util.math.TreeMath;

public class Bounds extends Range {

    public Bounds(double min, double max) {
        super(min, max);
    }

    public double nextValue(){
        return TreeMath.randomDouble(min, max);
    }

}
