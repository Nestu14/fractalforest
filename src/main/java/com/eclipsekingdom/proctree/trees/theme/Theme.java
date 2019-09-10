package com.eclipsekingdom.proctree.trees.theme;

import java.util.Random;

public enum Theme {

    OAK, GOLDEN

    ;

    public ITheme getTheme(){

        switch(this){
            case OAK: return new OakTheme();
            case GOLDEN: return new GoldenTheme();
            default: return new OakTheme();
        }

    }

    public static ITheme getRandom(Random random) {
        return Theme.values()[random.nextInt(Theme.values().length)].getTheme();
    }
}
