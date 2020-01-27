package com.eclipsekingdom.fractalforest.util;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.type.Leaves;

public class LeafDataUtil {

    public static void makePermanent(Block block) {
        BlockState state = block.getState();
        if (state.getBlockData() instanceof Leaves) {
            Leaves leaf = (Leaves) state.getBlockData();
            leaf.setPersistent(true);
            block.setBlockData(leaf);
        }
    }

}
