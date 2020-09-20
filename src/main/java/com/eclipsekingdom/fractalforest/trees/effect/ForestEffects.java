package com.eclipsekingdom.fractalforest.trees.effect;

import com.eclipsekingdom.fractalforest.util.X.XSound;
import com.eclipsekingdom.fractalforest.util.math.TreeMath;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;

public class ForestEffects implements IEffects {


    @Override
    public void playGrowthSound(Location location) {
        if (XSound.BLOCK_BAMBOO_SAPLING_PLACE.isSupported()) {
            location.getWorld().playSound(location, XSound.BLOCK_BAMBOO_SAPLING_PLACE.parseSound(), 0.5f, 1f);
            location.getWorld().playSound(location, Sound.BLOCK_BAMBOO_PLACE, 1f, 1f);
        } else if (XSound.BLOCK_GRASS_PLACE.isSupported()) {
            location.getWorld().playSound(location, XSound.BLOCK_GRASS_PLACE.parseSound(), 0.5f, 1.2f);
        }
    }

    @Override
    public void playSaplingParticles(Location location) {
        if (EffectType.superLegacy) {
            for (int i = 0; i < 5; i++) {
                double offX = TreeMath.randomDouble(-0.5, 0.5);
                double offY = TreeMath.randomDouble(-0.7, 0.7);
                double offZ = TreeMath.randomDouble(-0.5, 0.5);
                location.getWorld().playEffect(location.clone().add(offX, offY, offZ), Effect.valueOf("HAPPY_VILLAGER"), 1);
            }
        } else {
            location.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, location, 7, 0.5, 0.7, 0.5);
        }
    }
}
