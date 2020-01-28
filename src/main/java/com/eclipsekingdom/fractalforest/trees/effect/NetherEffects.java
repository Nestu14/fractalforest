package com.eclipsekingdom.fractalforest.trees.effect;

import com.eclipsekingdom.fractalforest.util.X.XSound;
import com.eclipsekingdom.fractalforest.util.math.TreeMath;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;

public class NetherEffects implements IEffects {

    @Override
    public void playGrowthSound(Location location) {
        if (XSound.ITEM_NETHER_WART_PLANT.isSupported()) {
            location.getWorld().playSound(location, XSound.ITEM_NETHER_WART_PLANT.parseSound(), 0.5f, 1.2f);
        }
    }

    @Override
    public void playSaplingParticles(Location location) {
        if (EffectType.legacy) {
            if (EffectType.superLegacy) {
                for (int i = 0; i < 5; i++) {
                    double offX = TreeMath.randomDouble(-0.5, 0.5);
                    double offY = TreeMath.randomDouble(-0.7, 0.7);
                    double offZ = TreeMath.randomDouble(-0.5, 0.5);
                    location.getWorld().playEffect(location.clone().add(offX, offY, offZ), Effect.valueOf("COLOURED_DUST"), 1);
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    double offX = TreeMath.randomDouble(-0.5, 0.5);
                    double offY = TreeMath.randomDouble(-0.7, 0.7);
                    double offZ = TreeMath.randomDouble(-0.5, 0.5);
                    location.getWorld().spawnParticle(Particle.REDSTONE, location.getX() + offX, location.getY() + offY, location.getZ() + offZ, 0, 255, 0, 0, 1);
                }
            }
        } else {
            location.getWorld().spawnParticle(Particle.REDSTONE, location, 7, 0.5, 0.7, 0.5, new Particle.DustOptions(Color.RED, 0.77f));
        }
    }

}
