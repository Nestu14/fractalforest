package com.eclipsekingdom.fractalforest.gen.pop;

import com.eclipsekingdom.fractalforest.gen.pop.util.TreeBiome;
import com.eclipsekingdom.fractalforest.trees.ITree;
import com.eclipsekingdom.fractalforest.trees.TreeUtil;
import com.google.common.collect.ImmutableSet;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.generator.BlockPopulator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public class TreePopulator extends BlockPopulator {

    private String name;
    private LinkedHashMap<TreeBiome, List<TreeSpawner>> biomeToTreeSpawner;

    public static TreePopulator defaultPopulator(String name) {
        return new TreePopulator(name, new LinkedHashMap<>());
    }

    public TreePopulator(String name, LinkedHashMap<TreeBiome, List<TreeSpawner>> biomeToTreeSpawner) {
        this.name = name;
        this.biomeToTreeSpawner = biomeToTreeSpawner;
    }

    public void initialize(Player player) {
        PopCache.registerPopulator(this);
        player.sendMessage(ChatColor.GREEN + "Tree pop " + ChatColor.GRAY + name + ChatColor.GREEN + " created");
    }

    public TreePopulator clone() {
        return new TreePopulator(getCloneString(), TreeUtil.clone(biomeToTreeSpawner));
    }

    private String getCloneString() {
        String meteorBase = name + "_COPY_";
        int num = 1;
        String attempt = meteorBase + num;
        while (PopCache.hasPopulator(attempt)) {
            num++;
            attempt = meteorBase + num;
        }
        return attempt;
    }


    @Override
    public void populate(World world, Random random, Chunk source) {
        int chunkX = source.getX() * 16;
        int chunkZ = source.getZ() * 16;

        TreeBiome treeBiome = TreeBiome.from(world.getBiome(chunkX, chunkZ));
        if (biomeToTreeSpawner.containsKey(treeBiome)) {
            List<TreeSpawner> spawners = biomeToTreeSpawner.get(treeBiome);
            for (TreeSpawner spawner : spawners) {
                if (random.nextDouble() < spawner.getChance()) {
                    int amount = spawner.nextAmount();
                    for (int i = 0; i < amount; i++) {
                        int x = chunkX + random.nextInt(15);
                        int z = chunkZ + random.nextInt(15);
                        Location location = getHighestValid(world, x, z);
                        if (location != null) {
                            ITree tree = spawner.getSpecies().getIndividual(null, location);
                            tree.growInstant();
                        }
                    }

                }
            }
        }

    }

    private Location getHighestValid(World world, int x, int z) {
        Block block = world.getHighestBlockAt(x, z);
        Location location = block.getLocation();
        Material material = block.getType();
        int count = 0;
        while (!isValid(material, location) && count < 55) {
            location.add(0, -1, 0);
            block = location.getBlock();
            material = block.getType();
            count++;
        }
        if (isValid(material, location)) {
            return location;
        } else {
            return null;
        }
    }


    //TODO change to per species
    private boolean isValid(Material material, Location location) {
        Block above = location.clone().add(0, 1, 0).getBlock();
        return soil.contains(material) && above.isPassable() && above.getType() != Material.WATER;
    }

    private ImmutableSet<Material> soil = new ImmutableSet.Builder<Material>()
            .add(Material.DIRT)
            .add(Material.COARSE_DIRT)
            .add(Material.GRASS_BLOCK)
            .add(Material.SAND)
            .add(Material.GRAVEL)
            .add(Material.MYCELIUM)
            .add(Material.PODZOL)
            .build();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedHashMap<TreeBiome, List<TreeSpawner>> getBiomeToTreeSpawner() {
        return biomeToTreeSpawner;
    }
}
