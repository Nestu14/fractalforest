package com.eclipsekingdom.fractalforest.gen.pop;

import com.eclipsekingdom.fractalforest.gen.pop.util.TreeBiome;
import com.eclipsekingdom.fractalforest.phylo.Species;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Presets {

    public static List<TreePopulator> makePresets() {
        List<TreePopulator> presets = new ArrayList<>();

        LinkedHashMap<TreeBiome, List<TreeSpawner>> fallMap = new LinkedHashMap<>();
        List<TreeSpawner> fallForest = new ArrayList<>();
        fallForest.add(new TreeSpawner(Species.FALL_OAK, 0.04, 0, 1));
        fallForest.add(new TreeSpawner(Species.FALL_MAPLE, 0.04, 0, 1));
        fallForest.add(new TreeSpawner(Species.FALL_ELM, 0.02, 0, 1));
        fallMap.put(TreeBiome.FOREST, fallForest);
        List<TreeSpawner> fallPlains = new ArrayList<>();
        fallPlains.add(new TreeSpawner(Species.FALL_OAK, 0.08, 0, 1));
        fallPlains.add(new TreeSpawner(Species.FALL_MAPLE, 0.08, 0, 1));
        fallPlains.add(new TreeSpawner(Species.FALL_ELM, 0.04, 0, 1));
        fallMap.put(TreeBiome.PLAINS, fallPlains);
        List<TreeSpawner> fallBirch = new ArrayList<>();
        fallBirch.add(new TreeSpawner(Species.FALL_BIRCH, 0.22, 0, 1));
        fallMap.put(TreeBiome.BIRCH_FOREST, fallBirch);
        TreePopulator fall = new TreePopulator("Fall", fallMap);
        presets.add(fall);


        LinkedHashMap<TreeBiome, List<TreeSpawner>> forestMap = new LinkedHashMap<>();
        List<TreeSpawner> forestForest = new ArrayList<>();
        forestForest.add(new TreeSpawner(Species.OAK, 0.04, 0, 1));
        forestForest.add(new TreeSpawner(Species.BUCK_EYE, 0.04, 0, 1));
        forestForest.add(new TreeSpawner(Species.ELM, 0.02, 0, 1));
        forestForest.add(new TreeSpawner(Species.BIRCH, 0.01, 0, 1));
        forestMap.put(TreeBiome.FOREST, forestForest);
        List<TreeSpawner> forestPlains = new ArrayList<>();
        forestPlains.add(new TreeSpawner(Species.OAK, 0.08, 0, 1));
        forestPlains.add(new TreeSpawner(Species.BUCK_EYE, 0.08, 0, 1));
        forestPlains.add(new TreeSpawner(Species.ELM, 0.04, 0, 1));
        forestMap.put(TreeBiome.PLAINS, forestPlains);
        List<TreeSpawner> forestBirch = new ArrayList<>();
        forestBirch.add(new TreeSpawner(Species.BIRCH, 0.22, 0, 1));
        forestMap.put(TreeBiome.BIRCH_FOREST, forestBirch);
        List<TreeSpawner> forestFlower = new ArrayList<>();
        forestFlower.add(new TreeSpawner(Species.FLOWERING_HAWTHORN, 0.08, 0, 1));
        forestMap.put(TreeBiome.FLOWER_FOREST, forestFlower);
        TreePopulator forest = new TreePopulator("Forest", forestMap);
        presets.add(forest);

        return presets;
    }

}
