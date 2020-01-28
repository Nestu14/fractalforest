package com.eclipsekingdom.fractalforest.util.X;

import com.eclipsekingdom.fractalforest.sys.Version;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;

public enum FMaterial {

    NETHER_WART_BLOCK(XMaterial.NETHER_WART_BLOCK),
    NETHER_BRICK_FENCE(XMaterial.NETHER_BRICK_FENCE),
    RED_NETHER_BRICK_WALL(XMaterial.RED_NETHER_BRICK_WALL),
    NETHER_BRICKS(XMaterial.NETHER_BRICKS),
    RED_NETHER_BRICKS(XMaterial.RED_NETHER_BRICKS),
    NETHERRACK(Material.NETHERRACK),
    MAGMA_BLOCK(XMaterial.MAGMA_BLOCK),
    GLOWSTONE(Material.GLOWSTONE),
    ANDESITE_WALL(Material.ANDESITE_WALL),
    DIORITE_WALL(Material.DIORITE_WALL),
    OBSIDIAN(Material.OBSIDIAN),
    MUSHROOM(XMaterial.MUSHROOM_STEM.parseMaterial()),

    OAK_WOOD(XMaterial.OAK_WOOD, 0),
    SPRUCE_WOOD(XMaterial.SPRUCE_WOOD, 1),
    BIRCH_WOOD(XMaterial.BIRCH_WOOD, 2),
    JUNGLE_WOOD(XMaterial.JUNGLE_WOOD, 3),
    ACACIA_WOOD(XMaterial.ACACIA_WOOD, 4),
    DARK_OAK_WOOD(XMaterial.DARK_OAK_WOOD, 5),

    OAK_FENCE(XMaterial.OAK_FENCE),
    SPRUCE_FENCE(Material.SPRUCE_FENCE),
    BIRCH_FENCE(Material.BIRCH_FENCE),
    JUNGLE_FENCE(Material.JUNGLE_FENCE),
    ACACIA_FENCE(Material.ACACIA_FENCE),
    DARK_OAK_FENCE(Material.DARK_OAK_FENCE),

    OAK_LEAVES(XMaterial.OAK_LEAVES, 4),
    SPRUCE_LEAVES(XMaterial.SPRUCE_LEAVES, 5),
    BIRCH_LEAVES(XMaterial.BIRCH_LEAVES, 6),
    JUNGLE_LEAVES(XMaterial.JUNGLE_LEAVES, 7),
    ACACIA_LEAVES(XMaterial.ACACIA_LEAVES, 4),
    DARK_OAK_LEAVES(XMaterial.DARK_OAK_LEAVES, 5),

    WHITE_TERRACOTTA(XMaterial.WHITE_TERRACOTTA, 0),
    ORANGE_TERRACOTTA(XMaterial.ORANGE_TERRACOTTA, 1),
    MAGENTA_TERRACOTTA(XMaterial.MAGENTA_TERRACOTTA, 2),
    LIGHT_BLUE_TERRACOTTA(XMaterial.LIGHT_BLUE_TERRACOTTA, 3),
    YELLOW_TERRACOTTA(XMaterial.YELLOW_TERRACOTTA, 4),
    LIME_TERRACOTTA(XMaterial.LIME_TERRACOTTA, 5),
    PINK_TERRACOTTA(XMaterial.PINK_TERRACOTTA, 6),
    GRAY_TERRACOTTA(XMaterial.GRAY_TERRACOTTA, 7),
    LIGHT_GRAY_TERRACOTTA(XMaterial.LIGHT_GRAY_TERRACOTTA, 8),
    CYAN_TERRACOTTA(XMaterial.CYAN_TERRACOTTA, 9),
    PURPLE_TERRACOTTA(XMaterial.PURPLE_TERRACOTTA, 10),
    BLUE_TERRACOTTA(XMaterial.BLUE_TERRACOTTA, 11),
    BROWN_TERRACOTTA(XMaterial.BROWN_TERRACOTTA, 12),
    GREEN_TERRACOTTA(XMaterial.GREEN_TERRACOTTA, 13),
    RED_TERRACOTTA(XMaterial.RED_TERRACOTTA, 14),
    BLACK_TERRACOTTA(XMaterial.BLACK_TERRACOTTA, 15),

    WHITE_CONCRETE(XMaterial.WHITE_CONCRETE, 0),
    ORANGE_CONCRETE(XMaterial.ORANGE_CONCRETE, 1),
    MAGENTA_CONCRETE(XMaterial.MAGENTA_CONCRETE, 2),
    LIGHT_BLUE_CONCRETE(XMaterial.LIGHT_BLUE_CONCRETE, 3),
    YELLOW_CONCRETE(XMaterial.YELLOW_CONCRETE, 4),
    LIME_CONCRETE(XMaterial.LIME_CONCRETE, 5),
    PINK_CONCRETE(XMaterial.PINK_CONCRETE, 6),
    GRAY_CONCRETE(XMaterial.GRAY_CONCRETE, 7),
    LIGHT_GRAY_CONCRETE(XMaterial.LIGHT_GRAY_CONCRETE, 8),
    CYAN_CONCRETE(XMaterial.CYAN_CONCRETE, 9),
    PURPLE_CONCRETE(XMaterial.PURPLE_CONCRETE, 10),
    BLUE_CONCRETE(XMaterial.BLUE_CONCRETE, 11),
    BROWN_CONCRETE(XMaterial.BROWN_CONCRETE, 12),
    GREEN_CONCRETE(XMaterial.GREEN_CONCRETE, 13),
    RED_CONCRETE(XMaterial.RED_CONCRETE, 14),
    BLACK_CONCRETE(XMaterial.BLACK_CONCRETE, 15),

    WHITE_WOOL(XMaterial.WHITE_WOOL, 0),
    ORANGE_WOOL(XMaterial.ORANGE_WOOL, 1),
    MAGENTA_WOOL(XMaterial.MAGENTA_WOOL, 2),
    LIGHT_BLUE_WOOL(XMaterial.LIGHT_BLUE_WOOL, 3),
    YELLOW_WOOL(XMaterial.YELLOW_WOOL, 4),
    LIME_WOOL(XMaterial.LIME_WOOL, 5),
    PINK_WOOL(XMaterial.PINK_WOOL, 6),
    GRAY_WOOL(XMaterial.GRAY_WOOL, 7),
    LIGHT_GRAY_WOOL(XMaterial.LIGHT_GRAY_WOOL, 8),
    CYAN_WOOL(XMaterial.CYAN_WOOL, 9),
    PURPLE_WOOL(XMaterial.PURPLE_WOOL, 10),
    BLUE_WOOL(XMaterial.BLUE_WOOL, 11),
    BROWN_WOOL(XMaterial.BROWN_WOOL, 12),
    GREEN_WOOL(XMaterial.GREEN_WOOL, 13),
    RED_WOOL(XMaterial.RED_WOOL, 14),
    BLACK_WOOL(XMaterial.BLACK_WOOL, 15),


    WHITE_STAINED_GLASS(XMaterial.WHITE_STAINED_GLASS, 0),
    ORANGE_STAINED_GLASS(XMaterial.ORANGE_STAINED_GLASS, 1),
    MAGENTA_STAINED_GLASS(XMaterial.MAGENTA_STAINED_GLASS, 2),
    LIGHT_BLUE_STAINED_GLASS(XMaterial.LIGHT_BLUE_STAINED_GLASS, 3),
    YELLOW_STAINED_GLASS(XMaterial.YELLOW_STAINED_GLASS, 4),
    LIME_STAINED_GLASS(XMaterial.LIME_STAINED_GLASS, 5),
    PINK_STAINED_GLASS(XMaterial.PINK_STAINED_GLASS, 6),
    GRAY_STAINED_GLASS(XMaterial.GRAY_STAINED_GLASS, 7),
    LIGHT_GRAY_STAINED_GLASS(XMaterial.LIGHT_GRAY_STAINED_GLASS, 8),
    CYAN_STAINED_GLASS(XMaterial.CYAN_STAINED_GLASS, 9),
    PURPLE_STAINED_GLASS(XMaterial.PURPLE_STAINED_GLASS, 10),
    BLUE_STAINED_GLASS(XMaterial.BLUE_STAINED_GLASS, 11),
    BROWN_STAINED_GLASS(XMaterial.BROWN_STAINED_GLASS, 12),
    GREEN_STAINED_GLASS(XMaterial.GREEN_STAINED_GLASS, 13),
    RED_STAINED_GLASS(XMaterial.RED_STAINED_GLASS, 14),
    BLACK_STAINED_GLASS(XMaterial.BLACK_STAINED_GLASS, 15),

    ;
    private static boolean legacy = Version.current.value <= 112;

    private Material material;
    private byte aByte;


    FMaterial(Material material) {
        this.material = material;
        aByte = -1;
    }

    FMaterial(XMaterial material) {
        this.material = material.parseMaterial();
        aByte = -1;
    }

    FMaterial(XMaterial material, int aByte) {
        this.material = material.parseMaterial();
        this.aByte = (byte) aByte;
    }

    public Material getMaterial() {
        return material;
    }

    public void toBlock(Block block) {
        block.setType(material);
        if (legacy) {
            BlockState state = block.getState();
            state.setRawData(aByte);
            state.update(true);
        }
    }


}
