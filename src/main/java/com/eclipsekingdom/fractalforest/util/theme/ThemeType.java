package com.eclipsekingdom.fractalforest.util.theme;

import java.util.Random;

public enum ThemeType {
    OAK, GOLDEN, BIRCH, FALL_BIRCH, FALL_OAK, FALL_ELM, FALL_MAPLE, FLOWERING_HAWTHORN, WEIRWOOD, FLAME_TREE;;

    public ITheme value() {

        switch (this) {
            case OAK:
                return new OakTheme();
            case GOLDEN:
                return new GoldenTheme();
            case BIRCH:
                return new BirchTheme();
            case FALL_BIRCH:
                return new FallBirchTheme();
            case FALL_OAK:
                return new FallOakTheme();
            case FALL_ELM:
                return new FallElmTheme();
            case FALL_MAPLE:
                return new FallMapleTheme();
            case FLOWERING_HAWTHORN:
                return new FloweringHawthornTheme();
            case WEIRWOOD:
                return new WeirwoodTheme();
            case FLAME_TREE:
                return new FlameTheme();
            default:
                return new OakTheme();
        }

    }

    public static ITheme getRandom(Random random) {
        return ThemeType.values()[random.nextInt(ThemeType.values().length)].value();
    }
}
