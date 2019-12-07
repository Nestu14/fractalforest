package com.eclipsekingdom.fractalforest.trees.effect;

public enum EffectType {

    FOREST(new ForestEffects()),
    NETHER(new NetherEffects());

    private IEffects effects;


    EffectType(IEffects effects) {
        this.effects = effects;
    }

    public IEffects getEffects() {
        return effects;
    }

}
