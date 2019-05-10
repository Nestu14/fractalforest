package com.eclipsekingdom.proctree.managers;

import com.eclipsekingdom.proctree.ProcTree;
import com.eclipsekingdom.proctree.trees.fractal.species.OakFractalMassive2B;
import com.eclipsekingdom.proctree.trees.fractal.species.OakFractalMassive3B;
import com.eclipsekingdom.proctree.trees.fractal.species.OakFractal4B;
import com.eclipsekingdom.proctree.trees.fractal.species.OakFractalMixed;
import com.eclipsekingdom.proctree.trees.legacy.LegacyTree;
import org.bukkit.Location;
public class TreeManager {

    private ProcTree plugin;

    public TreeManager(ProcTree plugin){
        this.plugin = plugin;
    }


    public void spawnOak(Location location){

        LegacyTree legacyTree = new LegacyTree(location);
        legacyTree.build();
    }

    public void spawnFOak2B(Location location){
        OakFractalMassive2B t = new OakFractalMassive2B(location);
        t.show();

    }

    public void spawnFOak3B(Location location){
        OakFractalMassive3B t = new OakFractalMassive3B(location);
        t.show();
    }


    public void spawnFOak4B(Location location){
        OakFractal4B t = new OakFractal4B(location);
        t.show();
    }

    public void spawnFOakMixed(Location location){
        OakFractalMixed  t = new OakFractalMixed(location);
        t.show();
    }






}
