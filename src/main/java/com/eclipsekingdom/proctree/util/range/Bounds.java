package com.eclipsekingdom.proctree.util.range;

import com.eclipsekingdom.proctree.util.TreeMath;

public class Bounds extends Range {

    public Bounds(double min, double max) {
        super(min, max);
    }

    public double nextValue(){
        return TreeMath.randomDouble(min, max);
    }

}
