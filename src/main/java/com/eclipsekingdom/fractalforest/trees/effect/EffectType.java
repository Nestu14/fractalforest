package com.eclipsekingdom.fractalforest.trees.effect;

import com.eclipsekingdom.fractalforest.sys.Version;

public enum EffectType {

    FOREST(new ForestEffects()),
    NETHER(new NetherEffects()),
    END(new EndEffects()),
    ;

    public static boolean legacy = Version.current.value <= 112;
    public static boolean superLegacy = Version.current.value <= 108;

    private IEffects effects;

    EffectType(IEffects effects) {
        this.effects = effects;
    }

    public IEffects getEffects() {
        return effects;
    }

}
