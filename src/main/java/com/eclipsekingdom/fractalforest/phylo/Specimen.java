package com.eclipsekingdom.fractalforest.phylo;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Specimen {

    private int height = 0;
    private int spread = 0;
    private int volume = 0;

    public Specimen(Set<Location> locations) {
        List<Location> locationList = new ArrayList<>();
        locationList.addAll(locations);
        examine(locationList);
    }

    private void examine(List<Location> locations) {
        volume = locations.size();
        if (volume > 0) {
            Location first = locations.get(0);
            int minY = first.getBlockY();
            int maxY = first.getBlockY();
            int minX = first.getBlockX();
            int maxX = first.getBlockX();
            for (Location location : locations) {
                int x = location.getBlockX();
                int y = location.getBlockY();
                if (y < minY) {
                    minY = y;
                }
                if (maxY < y) {
                    maxY = y;
                }
                if (x < minX) {
                    minX = x;
                }
                if (maxX < x) {
                    maxX = x;
                }
            }

            height = maxY - minY + 1;
            spread = maxX - minX + 1;

        }
    }

    public int getHeight() {
        return height;
    }

    public int getSpread() {
        return spread;
    }

    public int getVolume() {
        return volume;
    }

}
