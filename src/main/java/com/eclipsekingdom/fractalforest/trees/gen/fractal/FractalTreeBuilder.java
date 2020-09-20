package com.eclipsekingdom.fractalforest.trees.gen.fractal;

import com.eclipsekingdom.fractalforest.FractalForest;
import com.eclipsekingdom.fractalforest.protection.RegionValidation;
import com.eclipsekingdom.fractalforest.protection.WhiteListedBlocks;
import com.eclipsekingdom.fractalforest.sys.PluginBase;
import com.eclipsekingdom.fractalforest.trees.Species;
import com.eclipsekingdom.fractalforest.trees.Tree;
import com.eclipsekingdom.fractalforest.trees.gen.Branch;
import com.eclipsekingdom.fractalforest.trees.gen.LeafCluster;
import com.eclipsekingdom.fractalforest.trees.gen.Root;
import com.eclipsekingdom.fractalforest.util.TreeUtil;
import com.eclipsekingdom.fractalforest.util.math.FunctionIterator;
import com.eclipsekingdom.fractalforest.util.math.SegmentIterator;
import com.eclipsekingdom.fractalforest.util.theme.material.IMaterialFactory;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
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

    public FractalTreeBuilder(Species species, Player planter, Location seed, FractalBlueprint blueprint) {
        super(species, planter, seed);

        this.selfMaterial = theme.getSelfMaterials();
        this.rootFactory = theme.getRoot();
        this.leafFactory = theme.getLeaf();

        this.trunk = blueprint.getTrunk();
        this.roots = blueprint.getRoots();
        this.branches = blueprint.getBranches();
        this.leafClusters = blueprint.getLeafClusters();
    }

    @Override
    public void growPhased(int phaseTicks) {
        int phase = 0;
        boolean finished = false;
        while (!finished) {
            finished = true;

            if (phase == 0) {
                finished = false;
                species.getEffects().playGrowthSound(trunk.getBegin().clone().add(origin).toLocation(world));
                buildTrunk(trunk);
            }
            if (phase == 1) {
                finished = false;
                Bukkit.getScheduler().scheduleSyncDelayedTask(FractalForest.getPlugin(), () -> {
                    for (Root root : roots) {
                        buildRoot(root);
                    }
                }, phase * phaseTicks);
            }

            if (this.branches.size() > phase) {
                finished = false;
                List<Branch> branches = this.branches.get(phase);
                if (branches != null) {
                    Bukkit.getScheduler().scheduleSyncDelayedTask(FractalForest.getPlugin(), () -> {
                        int count = 0;
                        for (Branch branch : branches) {
                            if (count < 3)
                                species.getEffects().playGrowthSound(branch.getBegin().clone().add(origin).toLocation(world));
                            count++;
                            buildBranch(branch);
                        }
                    }, phase * phaseTicks);
                }
            }

            if (this.leafClusters.size() > phase) {
                finished = false;
                List<LeafCluster> leafClusters = this.leafClusters.get(phase);
                if (leafClusters != null) {
                    Bukkit.getScheduler().scheduleSyncDelayedTask(FractalForest.getPlugin(), () -> {
                        for (LeafCluster leafCluster : leafClusters) {
                            buildLeafCluster(leafCluster);
                        }
                    }, phase * phaseTicks);
                }
            }

            phase++;
        }

    }

    @Override
    public void growInstant() {

        int phase = 0;
        boolean finished = false;
        while (!finished) {
            finished = true;

            if (phase == 0) {
                finished = false;
                buildTrunk(trunk);
            }
            if (phase == 1) {
                finished = false;
                for (Root root : roots) {
                    buildRoot(root);
                }
            }

            if (this.branches.size() > phase) {
                finished = false;
                List<Branch> branches = this.branches.get(phase);
                if (branches != null) {
                    for (Branch branch : branches) {
                        buildBranch(branch);
                    }
                }
            }

            if (this.leafClusters.size() > phase) {
                finished = false;
                List<LeafCluster> leafClusters = this.leafClusters.get(phase);
                if (leafClusters != null) {
                    for (LeafCluster leafCluster : leafClusters) {
                        buildLeafCluster(leafCluster);
                    }
                }
            }

            phase++;
        }

    }


    private Set<Location> buildTrunk(Branch branch) {
        Set<Location> branchLocations = new HashSet<>();
        IMaterialFactory materialFactory = branch.getThickness() == Branch.Thickness.THICK ? theme.getThickBranch() : theme.getThinBranch();
        for (Block block : new SegmentIterator(world, branch.getBegin().add(origin), branch.getEnd().add(origin), branch.getRadius())) {
            Material material = block.getType();
            if (TreeUtil.isPassable(material) || WhiteListedBlocks.trunkWhitelist.contains(material)) {
                attemptBranch(materialFactory, block);
                branchLocations.add(block.getLocation());
            }
        }
        return branchLocations;
    }

    private Set<Location> buildBranch(Branch branch) {
        Set<Location> branchLocations = new HashSet<>();
        IMaterialFactory materialFactory = branch.getThickness() == Branch.Thickness.THICK ? theme.getThickBranch() : theme.getThinBranch();
        for (Block block : new SegmentIterator(world, branch.getBegin().add(origin), branch.getEnd().add(origin), branch.getRadius())) {
            Material material = block.getType();
            if (TreeUtil.isPassable(material) || WhiteListedBlocks.branchWhitelist.contains(material)) {
                attemptBranch(materialFactory, block);
                branchLocations.add(block.getLocation());
            }
        }
        return branchLocations;
    }

    private void attemptBranch(IMaterialFactory materialFactory, Block block) {
        Location location = block.getLocation();
        if (hasPlanter()) {
            Player planter = getPlanter();
            if (RegionValidation.isValidLocation(planter, location)) {
                if (PluginBase.isUsingCoreProtect()) PluginBase.getCoreProtect().registerRemoval(planter, block);
                placeBranch(materialFactory, block);
                if (PluginBase.isUsingCoreProtect()) PluginBase.getCoreProtect().registerPlacement(planter, block);
            }
        } else {
            placeBranch(materialFactory, block);
        }
    }

    private void placeBranch(IMaterialFactory materialFactory, Block block) {
        materialFactory.select(random).toBlock(block);
    }

    private Set<Location> buildRoot(Root root) {
        Set<Location> rootLocations = new HashSet<>();
        for (Block block : new FunctionIterator(world, root.getOrigin().add(origin), root.getPlane(), root.getLength(), root.getRadius(), root.getFunction())) {
            if (TreeUtil.isPassable(block.getType()) || WhiteListedBlocks.rootWhiteList.contains(block.getType())) {
                attemptRoot(block);
                rootLocations.add(block.getLocation());
            }
        }
        return rootLocations;
    }

    private void attemptRoot(Block block) {
        Location location = block.getLocation();
        if (hasPlanter()) {
            Player planter = getPlanter();
            if (RegionValidation.isValidLocation(planter, location)) {
                if (PluginBase.isUsingCoreProtect()) PluginBase.getCoreProtect().registerRemoval(planter, block);
                placeRoot(block);
                if (PluginBase.isUsingCoreProtect()) PluginBase.getCoreProtect().registerPlacement(planter, block);
            }
        } else {
            placeRoot(block);
        }
    }

    private void placeRoot(Block block) {
        rootFactory.select(random).toBlock(block);
    }

    private Set<Location> buildLeafCluster(LeafCluster leafCluster) {
        Set<Location> leafLocations = new HashSet<>();
        double radius = leafCluster.getRadius();
        Vector center = leafCluster.getCenter().add(origin);
        for (int x = (int) radius * -1; x < radius + 0.5; x++) {
            for (int y = (int) (radius * -0.5); y < radius + 0.5; y++) {
                for (int z = (int) radius * -1; z < radius + 0.5; z++) {
                    Vector current = center.clone().add(new Vector(x, y, z));
                    if (center.distance(current) <= radius + 0.5) {
                        attemptLeaf(current);
                        leafLocations.add(current.toLocation(world));
                    }
                }
            }
        }
        return leafLocations;
    }

    private void attemptLeaf(Vector current) {
        Location location = current.toLocation(world);
        Block target = location.getBlock();
        if (target.isEmpty() || selfMaterial.contains(target.getType())) {
            if (hasPlanter()) {
                Player planter = getPlanter();
                if (RegionValidation.isValidLocation(planter, location)) {
                    if (PluginBase.isUsingCoreProtect()) PluginBase.getCoreProtect().registerRemoval(planter, target);
                    placeLeaf(target);
                    if (PluginBase.isUsingCoreProtect()) PluginBase.getCoreProtect().registerPlacement(planter, target);
                }
            } else {
                placeLeaf(target);
            }
        }
    }

    private void placeLeaf(Block target) {
        leafFactory.select(random).toBlock(target);
        TreeUtil.makeLeafPermanent(target);
    }

}
