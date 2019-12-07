package com.eclipsekingdom.fractalforest.trees.effect;

import javax.xml.stream.Location;

public class NetherEffects implements IEffects {

    @Override
    public boolean playGrowthSound(Location location) {
        return false;
    }

    @Override
    public boolean playSaplingParticles(Location location) {
        return false;
    }

}
