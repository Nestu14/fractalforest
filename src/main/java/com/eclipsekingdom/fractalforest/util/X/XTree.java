package com.eclipsekingdom.fractalforest.util.X;

import com.eclipsekingdom.fractalforest.sys.Version;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum XTree {

    OAK(XMaterial.OAK_WOOD.parseMaterial(), (byte) 0),
    SPRUCE(XMaterial.SPRUCE_WOOD.parseMaterial(), (byte) 1),
    BIRCH(XMaterial.BIRCH_WOOD.parseMaterial(), (byte) 2),
    JUNGLE(XMaterial.JUNGLE_WOOD.parseMaterial(), (byte) 3),
    ACACIA(XMaterial.ACACIA_WOOD.parseMaterial(), (byte) 0),
    DARK_OAK(XMaterial.DARK_OAK_WOOD.parseMaterial(), (byte) 1),

    ;

    private Material material;
    private byte aByte;

    XTree(Material material, byte aByte) {
        this.material = material;
        this.aByte = aByte;
    }

    public Material getMaterial() {
        return material;
    }

    public byte getaByte() {
        return aByte;
    }

    public ItemStack getItemStack(){
        return Version.current.value >= 113 ? new ItemStack(material) : new ItemStack(material, 1, aByte);
    }



}
