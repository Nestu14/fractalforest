package com.eclipsekingdom.proctree.util.range;

public class Range {

    protected double min;
    protected double max;

    public Range(double min, double max){
        assert min < max : "min value must be less than max value";
        this.min = min;
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double asDouble(){
        return max - min;
    }



}
