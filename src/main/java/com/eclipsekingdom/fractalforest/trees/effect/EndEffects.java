package com.eclipsekingdom.fractalforest.trees.effect;

import com.eclipsekingdom.fractalforest.util.X.XSound;
import com.eclipsekingdom.fractalforest.util.math.TreeMath;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;

public class EndEffects implements IEffects {

    @Override
    public void playGrowthSound(Location location) {
        if (XSound.ENTITY_PUFFER_FISH_BLOW_OUT.isSupported()) {
            location.getWorld().playSound(location, XSound.ENTITY_PUFFER_FISH_BLOW_OUT.parseSound(), 0.5f, 1.2f);
        } else if (XSound.ENTITY_ENDER_EYE_DEATH.isSupported()) {
            location.getWorld().playSound(location, XSound.ENTITY_ENDER_EYE_DEATH.parseSound(), 0.5f, 1.2f);
        }
    }

    @Override
    public void playSaplingParticles(Location location) {
        if (EffectType.superLegacy) {
            for (int i = 0; i < 5; i++) {
                double offX = TreeMath.randomDouble(-0.5, 0.5);
                double offY = TreeMath.randomDouble(-0.7, 0.7);
                double offZ = TreeMath.randomDouble(-0.5, 0.5);
                location.getWorld().playEffect(location.clone().add(offX, offY, offZ), Effect.valueOf("PORTAL"), 1);
            }
        } else {
            location.getWorld().spawnParticle(Particle.PORTAL, location, 7, 0.5, 0.7, 0.5);
        }
    }
}
