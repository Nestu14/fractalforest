package com.eclipsekingdom.fractalforest.worldgen.pop;

import com.eclipsekingdom.fractalforest.worldgen.pop.util.TreeBiome;
import com.eclipsekingdom.fractalforest.trees.Species;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Presets {

    public static List<TreePopulator> makePresets() {
        List<TreePopulator> presets = new ArrayList<>();

        LinkedHashMap<TreeBiome, List<TreeSpawner>> fallMap = new LinkedHashMap<>();
        List<TreeSpawner> fallForest = new ArrayList<>();
        fallForest.add(new TreeSpawner(Species.FALL_OAK, 0.04, 0, 1, 0));
        fallForest.add(new TreeSpawner(Species.FALL_MAPLE, 0.04, 0, 1, 0));
        fallForest.add(new TreeSpawner(Species.FALL_ELM, 0.02, 0, 1, 0));
        fallMap.put(TreeBiome.FOREST, fallForest);
        List<TreeSpawner> fallPlains = new ArrayList<>();
        fallPlains.add(new TreeSpawner(Species.FALL_OAK, 0.08, 0, 1, 0));
        fallPlains.add(new TreeSpawner(Species.FALL_MAPLE, 0.08, 0, 1, 0));
        fallPlains.add(new TreeSpawner(Species.FALL_ELM, 0.04, 0, 1, 0));
        fallMap.put(TreeBiome.PLAINS, fallPlains);
        List<TreeSpawner> fallBirch = new ArrayList<>();
        fallBirch.add(new TreeSpawner(Species.FALL_BIRCH, 0.22, 0, 1, 0));
        fallMap.put(TreeBiome.BIRCH_FOREST, fallBirch);
        TreePopulator fall = new TreePopulator("Fall", fallMap);
        presets.add(fall);


        LinkedHashMap<TreeBiome, List<TreeSpawner>> forestMap = new LinkedHashMap<>();
        List<TreeSpawner> forestForest = new ArrayList<>();
        forestForest.add(new TreeSpawner(Species.OAK, 0.04, 0, 1, 0));
        forestForest.add(new TreeSpawner(Species.BUCK_EYE, 0.04, 0, 1, 0));
        forestForest.add(new TreeSpawner(Species.ELM, 0.02, 0, 1, 0));
        forestForest.add(new TreeSpawner(Species.BIRCH, 0.01, 0, 1, 0));
        forestForest.add(new TreeSpawner(Species.WHITE_ASH, 0.05, 1, 3, 20));
        forestMap.put(TreeBiome.FOREST, forestForest);
        List<TreeSpawner> forestPlains = new ArrayList<>();
        forestPlains.add(new TreeSpawner(Species.OAK, 0.08, 0, 1, 0));
        forestPlains.add(new TreeSpawner(Species.BUCK_EYE, 0.08, 0, 1, 0));
        forestPlains.add(new TreeSpawner(Species.ELM, 0.04, 0, 1, 0));
        forestPlains.add(new TreeSpawner(Species.WHITE_ASH, 0.06, 1, 3, 20));
        forestMap.put(TreeBiome.PLAINS, forestPlains);
        List<TreeSpawner> forestBirch = new ArrayList<>();
        forestBirch.add(new TreeSpawner(Species.BIRCH, 0.22, 0, 1, 0));
        forestMap.put(TreeBiome.BIRCH_FOREST, forestBirch);
        List<TreeSpawner> forestFlower = new ArrayList<>();
        forestFlower.add(new TreeSpawner(Species.FLOWERING_HAWTHORN, 0.08, 0, 1, 0));
        forestMap.put(TreeBiome.FLOWER_FOREST, forestFlower);
        TreePopulator forest = new TreePopulator("Forest", forestMap);
        presets.add(forest);

        LinkedHashMap<TreeBiome, List<TreeSpawner>> netherMap = new LinkedHashMap<>();
        List<TreeSpawner> netherForest = new ArrayList<>();
        netherForest.add(new TreeSpawner(Species.BLOOD_BUSH, 0.046, 3, 9, 8));
        netherForest.add(new TreeSpawner(Species.FLAME_TREE, 0.09, 1, 1, 0));
        netherMap.put(TreeBiome.NETHER, netherForest);
        TreePopulator nether = new TreePopulator("Nether", netherMap);
        presets.add(nether);

        LinkedHashMap<TreeBiome, List<TreeSpawner>> endMap = new LinkedHashMap<>();
        List<TreeSpawner> endForest = new ArrayList<>();
        endForest.add(new TreeSpawner(Species.SECCHI, 0.022, 2, 9, 8));
        endForest.add(new TreeSpawner(Species.CYGNI, 0.012, 1, 1, 0));
        endMap.put(TreeBiome.END, endForest);
        TreePopulator end = new TreePopulator("End", endMap);
        presets.add(end);

        return presets;
    }

}
