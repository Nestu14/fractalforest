package com.eclipsekingdom.fractalforest.util.math.random;


import java.util.Random;

public interface IWeighted<T> {

    int getWeight();

    T get(Random rand);

}