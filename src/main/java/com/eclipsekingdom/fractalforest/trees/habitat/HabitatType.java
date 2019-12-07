package com.eclipsekingdom.fractalforest.trees.habitat;

public enum HabitatType {
    FOREST(new Forest()),
    NETHER(new Nether());

    private IHabitat habitat;


    HabitatType(IHabitat habitat) {
        this.habitat = habitat;
    }

    public IHabitat getHabitat() {
        return habitat;
    }
}
