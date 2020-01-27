package com.eclipsekingdom.fractalforest.util.X;

import com.eclipsekingdom.fractalforest.sys.Version;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum FSapling {

    OAK_SAPLING(XMaterial.OAK_SAPLING, 0),
    SPRUCE_SAPLING(XMaterial.SPRUCE_SAPLING, 1),
    BIRCH_SAPLING(XMaterial.BIRCH_SAPLING, 2),
    JUNGLE_SAPLING(XMaterial.JUNGLE_SAPLING, 3),
    ACACIA_SAPLING(XMaterial.ACACIA_SAPLING, 4),
    DARK_OAK_SAPLING(XMaterial.DARK_OAK_SAPLING, 5),
    NETHER_WART(XMaterial.NETHER_WART),

    ;

    private static boolean legacy = Version.current.value <= 112;

    private Material material;
    private byte aByte;


    FSapling(Material material) {
        this.material = material;
        aByte = -1;
    }

    FSapling(XMaterial material) {
        this.material = material.parseMaterial();
        aByte = -1;
    }

    FSapling(Material material, int aByte) {
        this.material = material;
        this.aByte = (byte) aByte;
    }

    FSapling(XMaterial material, int aByte) {
        this.material = material.parseMaterial();
        this.aByte = (byte) aByte;
    }

    public ItemStack getItemStack() {
        if (!legacy || aByte == -1) {
            return new ItemStack(material);
        } else {
            return new ItemStack(material, 1, aByte);
        }
    }

    }
