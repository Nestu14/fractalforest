package com.eclipsekingdom.fractalforest.trees.fractal;

import com.eclipsekingdom.fractalforest.FractalForest;
import com.eclipsekingdom.fractalforest.PluginConfig;
import com.eclipsekingdom.fractalforest.protection.WhiteListedBlocks;
import com.eclipsekingdom.fractalforest.trees.Branch;
import com.eclipsekingdom.fractalforest.trees.LeafCluster;
import com.eclipsekingdom.fractalforest.trees.Root;
import com.eclipsekingdom.fractalforest.trees.Tree;
import com.eclipsekingdom.fractalforest.util.FunctionIterator;
import com.eclipsekingdom.fractalforest.util.LeafData;
import com.eclipsekingdom.fractalforest.util.SegmentIterator;
import com.eclipsekingdom.fractalforest.util.theme.ITheme;
import com.eclipsekingdom.fractalforest.util.theme.material.IMaterialFactory;
import com.google.common.collect.ImmutableSet;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.*;

public class FractalTreeBuilder extends Tree {

    private Set<Material> selfMaterial;

    private Branch trunk;
    private List<Root> roots;
    private List<List<Branch>> branches;
    private List<List<LeafCluster>> leafClusters;


    private IMaterialFactory rootFactory;
    private IMaterialFactory leafFactory;

    public FractalTreeBuilder(Location seed, ITheme theme, FractalGrowthPattern fractalGrowthPattern) {
        super(seed, theme);

        this.selfMaterial = theme.getSelfMaterials();
        this.rootFactory = theme.getRoot();
        this.leafFactory = theme.getLeaf();

        this.trunk = fractalGrowthPattern.getTrunk();
        this.roots = fractalGrowthPattern.getRoots();
        this.branches = fractalGrowthPattern.getBranches();
        this.leafClusters = fractalGrowthPattern.getLeafClusters();
    }


    @Override
    public void grow(int phaseTicks) {

        int phase = 0;
        boolean finished = false;
        while(!finished){
            finished = true;

            if(phase ==  0){
                finished = false;
                buildBranch(trunk);
            }
            if(phase == 1){
                finished = false;
                Bukkit.getScheduler().scheduleSyncDelayedTask(FractalForest.plugin, () ->{
                    for(Root root: roots){
                        buildRoot(root);
                    }
                }, phase * phaseTicks);
            }

            if(this.branches.size() > phase){
                finished = false;
                List<Branch> branches = this.branches.get(phase);
                if(branches != null){
                    Bukkit.getScheduler().scheduleSyncDelayedTask(FractalForest.plugin, () ->{
                        for(Branch branch: branches){
                            buildBranch(branch);
                        }
                    }, phase * phaseTicks);
                }
            }

            if(this.leafClusters.size() > phase){
                finished = false;
                List<LeafCluster> leafClusters = this.leafClusters.get(phase);
                if(leafClusters != null){
                    Bukkit.getScheduler().scheduleSyncDelayedTask(FractalForest.plugin, () ->{
                        for(LeafCluster leafCluster: leafClusters){
                            buildLeafCluster(leafCluster);
                        }
                    }, phase * phaseTicks);
                }
            }

            phase++;
        }
    }


    private void buildBranch(Branch branch){
        IMaterialFactory materialFactory = branch.getThickness() == Branch.Thickness.THICK? theme.getThickBranch(): theme.getThinBranch();
        for(Block block: new SegmentIterator(world,branch.getBegin().add(origin),branch.getEnd().add(origin), branch.getRadius())){
            if(block.isPassable() || WhiteListedBlocks.trunkWhitelist.contains(block.getType())){
                block.setType(materialFactory.select(random));
            }
        }
    }

    private void buildRoot(Root root){
        for(Block block: new FunctionIterator(world, root.getOrigin().add(origin), root.getPlane(), root.getLength(), root.getRadius(), root.getFunction())){
            if(block.isPassable() || WhiteListedBlocks.rootWhiteList.contains(block.getType())){
                block.setType(rootFactory.select(random));
            }
        }
    }

    private void buildLeafCluster(LeafCluster leafCluster){
        double radius = leafCluster.getRadius();
        Vector center = leafCluster.getCenter().add(origin);
        for(int x = (int)radius*-1; x<radius+0.5; x++) {
            for (int y = (int)(radius*-0.5); y < radius + 0.5; y++) {
                for (int z = (int)radius * -1; z < radius + 0.5; z++) {
                    Vector current = center.clone().add(new Vector(x,y,z));
                    if(center.distance(current) <= radius + 0.5){
                        Block target = current.toLocation(world).getBlock();
                        if(target.isEmpty() || selfMaterial.contains(target.getType())){
                            target.setType(leafFactory.select(random));
                            if(!PluginConfig.isProceduralTreeLeafDecay()){
                                LeafData.makePermanent(target);
                            }
                        }
                    }
                }
            }
        }
    }




    /*
    public void grow() {
        boolean finished = false;
        int phase = 0;
        while (!finished) {
            finished = true;
            for (Map.Entry<MatterType, TreePhases> entry : phaseMap.entrySet()) {
                TreePhases treePhases = entry.getValue();
                if (treePhases.hasPhase(phase)) {
                    finished = false;
                    List<BlockGroup> groups = treePhases.getGroups(phase);
                    MatterType matterType = entry.getKey();
                    for (BlockGroup blockGroup : groups) {
                        if (!isObstructed(blockGroup, matterType)) {
                            buildMatter(blockGroup, matterType != MatterType.LEAVES);
                        }
                    }
                }
            }
            phase++;
        }
    }

    private List<BlockGroup> obstructedGroups = new ArrayList<>();

    private boolean isObstructed(BlockGroup blockGroup, MatterType matterType) {
        for (BlockGroup obstructedGroup : obstructedGroups) {
            if (blockGroup.isDescendedFrom(obstructedGroup)) {
                return true;
            }
        }
        if (matterType != MatterType.LEAVES) {
            Set<Material> whiteListedBlocks = WhiteListedBlocks.forType(matterType);
            for (Block block : blockGroup.getBlockToMaterial().keySet()) {
                Material material = block.getType();
                if (!block.isEmpty() && !whiteListedBlocks.contains(material) && !selfMaterial.contains(material)) {
                    Bukkit.getConsoleSender().sendMessage("BLOCKED!");
                    return true;
                }
            }
        }
        return false;
    }

    private void buildMatter(BlockGroup blockGroup, boolean replaceSolid) {
        Map<Block, Material> blockToMaterial = blockGroup.getBlockToMaterial();
        if (replaceSolid) {
            for (Block block : blockToMaterial.keySet()) {
                block.setType(blockToMaterial.get(block));
            }
        } else {
            for (Block block : blockToMaterial.keySet()) {
                if (block.isPassable()) {
                    block.setType(blockToMaterial.get(block));
                }
            }
        }
    }



    //TODO ADD MIDDLE CLASS TO CONVERT BETWEEN
    private TreePhases getBranchPhases(ITheme theme, List<List<Branch>> branches) {
        TreePhases treePhases = new TreePhases();
        for(int i=0; i<branches.size(); i++){
            List<Branch> branchesAtPhase = branches.get(i);
            if(branchesAtPhase != null){
                Map<Block, Material> blockToMaterial = new HashMap<>();
                for(Branch branch: branchesAtPhase){
                    IMaterialFactory materialFactory = branch.getThickness() == Branch.Thickness.THICK ? theme.getThickBranch() : theme.getThinBranch();
                    for(Block block : new SegmentIterator(world, branch.getBegin().add(origin), branch.getEnd().add(origin), branch.getRadius())){

                    }
                }
                BlockGroup blockGroup = new BlockGroup();
            }
        }
    }

    /*
    private void buildRoot(List<Block> blocks) {
        for (Block block : new FunctionIterator(world, root.getOrigin().add(origin), root.getPlane(), root.getLength(), root.getRadius(), root.getFunction())) {
            block.setType(rootFactory.select(random));
        }
    }

    private void buildLeaves(List<Block> blocks) {
        double radius = leaves.getRadius();
        Vector center = leaves.getCenter().add(origin);
        for (int x = (int) radius * -1; x < radius + 0.5; x++) {
            for (int y = (int) (radius * -0.5); y < radius + 0.5; y++) {
                for (int z = (int) radius * -1; z < radius + 0.5; z++) {
                    Vector current = center.clone().add(new Vector(x, y, z));
                    if (center.distance(current) <= radius + 0.5) {
                        Block target = current.toLocation(world).getBlock();
                        target.setType(leafFactory.select(random));
                        LeafData.makePermanent(target);
                    }
                }
            }
        }
    }
    */






}
