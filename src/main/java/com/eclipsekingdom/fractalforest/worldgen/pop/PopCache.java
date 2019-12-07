package com.eclipsekingdom.fractalforest.worldgen.pop;

import java.util.ArrayList;
import java.util.List;

public class PopCache {

    private static PopFlatFile popFlatFile = new PopFlatFile();
    private static List<TreePopulator> tPops = new ArrayList<>();
    private static List<TreePopulator> presets = Presets.makePresets();

    public PopCache() {
        tPops.addAll(popFlatFile.fetch());
    }

    public static void save() {
        popFlatFile.store(tPops);
    }

    public static void registerPopulator(TreePopulator pop) {
        if (!tPops.contains(pop)) {
            tPops.add(pop);
        }
    }

    public static void removePopulator(TreePopulator pop) {
        while (tPops.contains(pop)) {
            tPops.remove(pop);
        }
    }

    public static TreePopulator getPopulator(String name) {
        for (TreePopulator pop : presets) {
            if (pop.getName().equalsIgnoreCase(name)) {
                return pop;
            }
        }
        for (TreePopulator pop : tPops) {
            if (pop.getName().equalsIgnoreCase(name)) {
                return pop;
            }
        }
        return null;
    }

    public static List<TreePopulator> getPopulators() {
        List<TreePopulator> types = new ArrayList<>();
        types.addAll(tPops);
        types.addAll(presets);
        return types;
    }

    public static boolean hasPopulator(String name) {
        for (TreePopulator pop : presets) {
            if (pop.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        for (TreePopulator pop : tPops) {
            if (pop.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }


    public static boolean isPreset(String name) {
        for (TreePopulator type : presets) {
            if (type.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

}
