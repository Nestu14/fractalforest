package com.eclipsekingdom.fractalforest.trees.habitat;

import javax.xml.stream.Location;

public interface IHabitat {

    boolean canGenAt(Location location);

    boolean canPlantAt(Location location);

}
