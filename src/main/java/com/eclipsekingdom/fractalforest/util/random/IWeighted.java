package com.eclipsekingdom.fractalforest.util.random;


import java.util.Random;

public interface IWeighted<T> {

    int getWeight();

    T get(Random rand);

}