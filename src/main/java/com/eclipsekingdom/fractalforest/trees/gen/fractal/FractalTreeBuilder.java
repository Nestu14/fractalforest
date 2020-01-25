package com.eclipsekingdom.fractalforest.trees.gen.fractal;

import com.eclipsekingdom.fractalforest.FractalForest;
import com.eclipsekingdom.fractalforest.encyclopedia.RecordSpecimenEvent;
import com.eclipsekingdom.fractalforest.encyclopedia.Specimen;
import com.eclipsekingdom.fractalforest.protection.RegionValidation;
import com.eclipsekingdom.fractalforest.protection.WhiteListedBlocks;
import com.eclipsekingdom.fractalforest.trees.Species;
import com.eclipsekingdom.fractalforest.trees.Tree;
import com.eclipsekingdom.fractalforest.trees.gen.Branch;
import com.eclipsekingdom.fractalforest.trees.gen.LeafCluster;
import com.eclipsekingdom.fractalforest.trees.gen.Root;
import com.eclipsekingdom.fractalforest.util.TreeUtil;
import com.eclipsekingdom.fractalforest.sys.config.PluginConfig;
import com.eclipsekingdom.fractalforest.util.math.FunctionIterator;
import com.eclipsekingdom.fractalforest.util.math.SegmentIterator;
import com.eclipsekingdom.fractalforest.sys.PluginBase;
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

    private static Map<UUID, Set<Location>> locationsCache = new HashMap<>();

    @Override
    public void growPhased(int phaseTicks) {
        UUID PID = UUID.randomUUID();
        int phase = 0;
        boolean isAnimated = phaseTicks > 0;
        if (isAnimated) {
            locationsCache.put(PID, new HashSet<>());
        }
        boolean finished = false;
        while (!finished) {
            finished = true;

            if (phase == 0) {
                finished = false;
                if (isAnimated) {
                    locationsCache.get(PID).addAll(buildBranch(trunk));
                } else {
                    buildBranch(trunk);
                }
            }
            if (phase == 1) {
                finished = false;
                if (isAnimated) {
                    Bukkit.getScheduler().scheduleSyncDelayedTask(FractalForest.plugin, () -> {
                        for (Root root : roots) {
                            locationsCache.get(PID).addAll(buildRoot(root));
                        }
                    }, phase * phaseTicks);
                } else {
                    for (Root root : roots) {
                        buildRoot(root);
                    }
                }
            }

            if (this.branches.size() > phase) {
                finished = false;
                List<Branch> branches = this.branches.get(phase);
                if (branches != null) {
                    if (isAnimated) {
                        Bukkit.getScheduler().scheduleSyncDelayedTask(FractalForest.plugin, () -> {
                            for (Branch branch : branches) {
                                locationsCache.get(PID).addAll(buildBranch(branch));
                            }
                        }, phase * phaseTicks);
                    } else {
                        for (Branch branch : branches) {
                            buildBranch(branch);
                        }
                    }
                }
            }

            if (this.leafClusters.size() > phase) {
                finished = false;
                List<LeafCluster> leafClusters = this.leafClusters.get(phase);
                if (leafClusters != null) {
                    if (isAnimated) {
                        Bukkit.getScheduler().scheduleSyncDelayedTask(FractalForest.plugin, () -> {
                            for (LeafCluster leafCluster : leafClusters) {
                                locationsCache.get(PID).addAll(buildLeafCluster(leafCluster));
                            }
                        }, phase * phaseTicks);
                    } else {
                        for (LeafCluster leafCluster : leafClusters) {
                            buildLeafCluster(leafCluster);
                        }
                    }
                }
            }

            phase++;
        }

        if (isAnimated) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(FractalForest.plugin, () -> {
                TreeUtil.callEvent(new RecordSpecimenEvent(species.toString(), new Specimen(locationsCache.get(PID))));
                locationsCache.remove(PID);
            }, phase * phaseTicks);
        }

    }

    @Override
    public void growInstant() {
        growPhased(0);
    }

    private Set<Location> buildBranch(Branch branch) {
        Set<Location> branchLocations = new HashSet<>();
        IMaterialFactory materialFactory = branch.getThickness() == Branch.Thickness.THICK ? theme.getThickBranch() : theme.getThinBranch();
        for (Block block : new SegmentIterator(world, branch.getBegin().add(origin), branch.getEnd().add(origin), branch.getRadius())) {
            if (block.isPassable() || WhiteListedBlocks.trunkWhitelist.contains(block.getType())) {
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
        block.setType(materialFactory.select(random));
    }

    private Set<Location> buildRoot(Root root) {
        Set<Location> rootLocations = new HashSet<>();
        for (Block block : new FunctionIterator(world, root.getOrigin().add(origin), root.getPlane(), root.getLength(), root.getRadius(), root.getFunction())) {
            if (block.isPassable() || WhiteListedBlocks.rootWhiteList.contains(block.getType())) {
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
        block.setType(rootFactory.select(random));
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
        target.setType(leafFactory.select(random));
        if (!PluginConfig.isProceduralTreeLeafDecay()) {
            TreeUtil.makeLeafPermanent(target);
        }
    }

}
