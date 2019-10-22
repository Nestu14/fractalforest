package com.eclipsekingdom.fractalforest.populator;

import com.eclipsekingdom.fractalforest.trees.ITree;
import com.google.common.collect.ImmutableSet;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.generator.BlockPopulator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public class TreePopulator extends BlockPopulator {

    private String name;
    private boolean enabled;
    private boolean initialized;
    private List<World> worlds;
    private LinkedHashMap<Biome, List<TreeSpawner>> biomeToTreeSpawner;

    public static TreePopulator defaultPopulator(String name, World world) {
        ArrayList<World> worlds = new ArrayList<>();
        worlds.add(world);
        LinkedHashMap<Biome, List<TreeSpawner>> biomeToTreeSpawner = new LinkedHashMap<>();
        biomeToTreeSpawner.put(Biome.FOREST, TreeSpawner.defaultTreeSpawners());
        return new TreePopulator(name, worlds, true, biomeToTreeSpawner);
    }

    public TreePopulator(String name, List<World> worlds, boolean enabled, LinkedHashMap<Biome, List<TreeSpawner>> biomeToTreeSpawner) {
        this.initialized = false;
        this.name = name;
        this.worlds = worlds;
        this.enabled = enabled;
        this.biomeToTreeSpawner = biomeToTreeSpawner;
    }

    public void initialize(Player player) {
        initialize();
        player.sendMessage(ChatColor.GREEN + "Tree populator " + ChatColor.GRAY + name + ChatColor.GREEN + " created");
    }

    public void initialize() {
        initialized = true;
        addToWorlds();
        PopCache.registerPopulator(this);
    }

    private void addToWorlds() {
        for (World world : worlds) {
            if (world != null) {
                List<BlockPopulator> populators = world.getPopulators();
                for (int i = populators.size() - 1; i >= 0; i--) {
                    BlockPopulator populator = populators.get(i);
                    if (populator.toString().contains("TreePopulator")) {
                        populators.remove(populator);
                    }
                }
                world.getPopulators().add(this);
            }
        }
    }

    @Override
    public void populate(World world, Random random, Chunk source) {
        int chunkX = source.getX() * 16;
        int chunkZ = source.getZ() * 16;

        Biome biome = world.getBiome(chunkX, chunkZ);
        if (biomeToTreeSpawner.containsKey(biome)) {
            List<TreeSpawner> spawners = biomeToTreeSpawner.get(biome);
            for (TreeSpawner spawner : spawners) {
                if (random.nextDouble() < spawner.getChance()) {
                    int amount = spawner.nextAmount();
                    for (int i = 0; i < amount; i++) {
                        int x = chunkX + random.nextInt(15);
                        int z = chunkZ + random.nextInt(15);
                        Location location = getHighestValid(world, x, z);
                        if (location != null) {
                            ITree tree = spawner.getSpecies().getIndividual(location);
                            tree.grow(0);
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
        while (!isValid(material) && count < 55) {
            location.add(0, -1, 0);
            block = location.getBlock();
            material = block.getType();
            count++;
        }
        if (isValid(material)) {
            return location;
        } else {
            return null;
        }
    }


    private boolean isValid(Material material) {
        return material.isSolid() && !leaves.contains(material) && !logs.contains(material);
    }

    private ImmutableSet<Material> leaves = new ImmutableSet.Builder<Material>()
            .add(Material.OAK_LEAVES)
            .add(Material.BIRCH_LEAVES)
            .add(Material.ACACIA_LEAVES)
            .add(Material.DARK_OAK_LEAVES)
            .add(Material.JUNGLE_LEAVES)
            .add(Material.SPRUCE_LEAVES)
            .build();


    private ImmutableSet<Material> logs = new ImmutableSet.Builder<Material>()
            .add(Material.OAK_LOG)
            .add(Material.BIRCH_LOG)
            .add(Material.ACACIA_LOG)
            .add(Material.DARK_OAK_LOG)
            .add(Material.JUNGLE_LOG)
            .add(Material.SPRUCE_LOG)
            .add(Material.OAK_WOOD)
            .add(Material.BIRCH_WOOD)
            .add(Material.ACACIA_WOOD)
            .add(Material.DARK_OAK_WOOD)
            .add(Material.JUNGLE_WOOD)
            .add(Material.SPRUCE_WOOD)
            .build();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<World> getWorlds() {
        return worlds;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (initialized) {
            if (enabled) {
                addToWorlds();
            } else {
                for (World world : worlds) {
                    world.getPopulators().remove(this);
                }
            }
        }
    }

    public LinkedHashMap<Biome, List<TreeSpawner>> getBiomeToTreeSpawner() {
        return biomeToTreeSpawner;
    }
}
