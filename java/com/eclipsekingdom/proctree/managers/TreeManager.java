package com.eclipsekingdom.proctree.managers;

import com.eclipsekingdom.proctree.ProcTree;
import com.eclipsekingdom.proctree.trees.fractal.legacy.LegacyTree;
import com.eclipsekingdom.proctree.trees.fractal.species.OakFractalMassiveV1;
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

    public void test(Location location){
        OakFractalMassiveV1 t = new OakFractalMassiveV1(location);
        t.show();

    }






}
