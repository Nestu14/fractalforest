package com.eclipsekingdom.proctree.util.random;


import java.util.Random;

public interface IWeighted<T> {

    int getWeight();

    T get(Random rand);

}