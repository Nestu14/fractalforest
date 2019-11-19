package com.eclipsekingdom.fractalforest.gen.pop;

import java.util.ArrayList;
import java.util.List;

public class PopCache {

    private static PopFlatFile popFlatFile = new PopFlatFile();
    private static List<TreePopulator> tPops = new ArrayList<>();

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
        for (TreePopulator pop : tPops) {
            if (pop.getName().equalsIgnoreCase(name)) {
                return pop;
            }
        }
        return null;
    }

    public static List<TreePopulator> getPopulators() {
        return tPops;
    }

    public static boolean hasPopulator(String name) {
        for (TreePopulator pop : tPops) {
            if (pop.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

}
