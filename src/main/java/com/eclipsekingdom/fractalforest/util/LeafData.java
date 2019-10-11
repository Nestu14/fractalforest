package com.eclipsekingdom.fractalforest.util;

import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Leaves;

public class LeafData {

    public static void makePermanent(Block block){
        BlockData blockData = block.getState().getBlockData();
        if(blockData instanceof Leaves){
            Leaves leaf = (Leaves) blockData;
            leaf.setPersistent(true);
            block.setBlockData(leaf);
        }
    }

}
