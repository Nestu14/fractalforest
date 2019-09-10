package com.eclipsekingdom.proctree.trees;

import com.eclipsekingdom.proctree.trees.fractal.Branch;
import com.eclipsekingdom.proctree.trees.material.IMaterialFactory;
import com.eclipsekingdom.proctree.trees.theme.ITheme;
import com.eclipsekingdom.proctree.util.FunctionIterator;
import com.eclipsekingdom.proctree.util.LeafData;
import com.eclipsekingdom.proctree.util.SegmentIterator;
import com.google.common.collect.ImmutableSet;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.Random;

public class Seed {

    private static Random random = new Random();

    private World world;
    private Vector origin;
    private Tree tree;
    private ITheme theme;

    private IMaterialFactory rootFactory;
    private IMaterialFactory leafFactory;

    public Seed(Tree tree, Location location){
        this.world = location.getWorld();
        this.origin = location.toVector();
        this.tree = tree;
        this.theme = tree.getTheme();
        this.rootFactory = theme.getRoot();
        this.leafFactory = theme.getLeaf();
        plant();
        tree.reGenerate();
    }

    private void plant(){
        for(Branch branch: tree.getBranches()){
            buildBranch(branch);
        }
        for(Root root: tree.getRoots()){
            buildRoot(root);
        }
        for(Leaves leaves: tree.getLeaves()){
            buildLeaves(leaves);
        }
    }

    private void buildBranch(Branch branch){
        IMaterialFactory materialFactory = branch.getThickness() == Branch.Thickness.THICK? theme.getThickBranch(): theme.getThinBranch();
        for(Block block: new SegmentIterator(world,branch.getBegin().add(origin),branch.getEnd().add(origin), branch.getRadius())){
            if(canPlaceOverroide(block)){
                block.setType(materialFactory.select(random));
            }
        }
    }

    private void buildRoot(Root root){
        for(Block block: new FunctionIterator(world, root.getOrigin().add(origin), root.getPlane(), root.getLength(), root.getRadius(), root.getFunction())){
            block.setType(rootFactory.select(random));
        }
    }

    public boolean canPlaceOverroide(Block block){
        return block.isPassable() || overrideMaterials.contains(block.getType());
    }

    private static ImmutableSet<Material> overrideMaterials = new ImmutableSet.Builder<Material>()
            .add(Material.GRASS)
            .add(Material.OAK_SAPLING)
            .add(Material.TALL_GRASS)
            .add(Material.FERN)
            .add(Material.LARGE_FERN)
            .build();

    private void buildLeaves(Leaves leaves){
        double radius = leaves.getRadius();
        Vector center = leaves.getCenter().add(origin);
        for(int x = (int)radius*-1; x<radius+0.5; x++) {
            for (int y = (int)(radius*-0.5); y < radius + 0.5; y++) {
                for (int z = (int)radius * -1; z < radius + 0.5; z++) {
                    Vector current = center.clone().add(new Vector(x,y,z));
                    if(center.distance(current) <= radius + 0.5){
                        Block target = current.toLocation(world).getBlock();
                        target.setType(leafFactory.select(random));
                        LeafData.makePermanent(target);
                    }
                }
            }
        }
    }

}
