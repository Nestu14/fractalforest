package com.eclipsekingdom.fractalforest.populator;

import com.eclipsekingdom.fractalforest.phylo.Species;
import com.eclipsekingdom.fractalforest.trees.ITree;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.generator.BlockPopulator;

import java.util.*;

public class TreePopulator extends BlockPopulator {

    private String name;
    private boolean enabled;
    private boolean initialized;
    private List<World> worlds;
    private LinkedHashMap<Biome, List<TreeSpawner>> biomeToTreeSpawner;
    private double TREE_CHANCE = 0.02;

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
        initialized = true;
        addToWorlds();
        player.sendMessage(ChatColor.GREEN + "Tree populator created");
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
        if (random.nextDouble() < TREE_CHANCE) {
            int x = chunkX + random.nextInt(15);
            int z = chunkZ + random.nextInt(15);
            attemptTreeSpawn(world, x, z);
        }
    }


    private void attemptTreeSpawn(World world, int x, int z) {
        Biome biome = world.getBiome(x, z);
        if (biomeToTreeSpawner.containsKey(biome)) {
            ITree tree = Species.ELM.getIndividual(new Location(world, x, world.getHighestBlockYAt(x, z), z));
            tree.grow(0);
        }
    }

    //TODO get highest dirt block!!!!!!!


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
        if (enabled) {
            if (initialized) {
                addToWorlds();
            }
        } else {
            for (World world : worlds) {
                world.getPopulators().remove(this);
            }
        }
    }

    public LinkedHashMap<Biome, List<TreeSpawner>> getBiomeToTreeSpawner() {
        return biomeToTreeSpawner;
    }
}
