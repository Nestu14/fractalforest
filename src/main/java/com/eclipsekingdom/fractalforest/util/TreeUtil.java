package com.eclipsekingdom.fractalforest.util;

import com.eclipsekingdom.fractalforest.worldgen.pop.TreeSpawner;
import com.eclipsekingdom.fractalforest.worldgen.pop.util.TreeBiome;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Leaves;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TreeUtil {

    public static void callEvent(Event event) {
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    public static LinkedHashMap<TreeBiome, List<TreeSpawner>> clone(LinkedHashMap<TreeBiome, List<TreeSpawner>> biomeToTreeSpawner) {
        LinkedHashMap<TreeBiome, List<TreeSpawner>> clone = new LinkedHashMap<>();
        for (Map.Entry<TreeBiome, List<TreeSpawner>> entry : biomeToTreeSpawner.entrySet()) {
            List<TreeSpawner> clonedSpawners = new ArrayList<>();
            for (TreeSpawner treeSpawner : entry.getValue()) {
                clonedSpawners.add(treeSpawner.clone());
            }
            clone.put(entry.getKey(), clonedSpawners);
        }
        return clone;
    }

    public static void makeLeafPermanent(Block block){
        BlockData blockData = block.getState().getBlockData();
        if(blockData instanceof Leaves){
            Leaves leaf = (Leaves) blockData;
            leaf.setPersistent(true);
            block.setBlockData(leaf);
        }
    }

}
