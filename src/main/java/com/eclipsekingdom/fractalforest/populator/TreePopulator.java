package com.eclipsekingdom.fractalforest.populator;

import com.eclipsekingdom.fractalforest.phylo.Species;
import com.eclipsekingdom.fractalforest.trees.ITree;
import com.google.common.collect.ImmutableSet;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.generator.BlockPopulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class TreePopulator extends BlockPopulator {

    private String name;
    private boolean enabled;
    private List<World> worlds;
    private double TREE_CHANCE = 0.02;

    public static TreePopulator defaultPopulator(String name, World world) {
        ArrayList<World> worlds = new ArrayList<>();
        worlds.add(world);
        return new TreePopulator(name, worlds, true);
    }

    public TreePopulator(String name, List<World> worlds, boolean enabled) {
        this.name = name;
        this.worlds = worlds;
        this.enabled = enabled;
    }

    public void initialize(Player player) {
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
        player.sendMessage(ChatColor.GREEN + "Tree populator created");
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
        if (treeBiomes.contains(biome)) {
            ITree tree = Species.ELM.getIndividual(new Location(world, x, world.getHighestBlockYAt(x, z), z));
            tree.grow(0);
        }
    }

    //TODO get highest dirt block!!!!!!!

    private Set<Biome> treeBiomes = new ImmutableSet.Builder<Biome>()
            .add(Biome.FOREST)
            .add(Biome.FLOWER_FOREST)
            .add(Biome.DARK_FOREST)
            .add(Biome.BIRCH_FOREST)
            .add(Biome.TALL_BIRCH_FOREST)
            .add(Biome.DARK_FOREST_HILLS)
            .add(Biome.BIRCH_FOREST_HILLS)
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
        if(!enabled){
            for(World world: worlds){
                world.getPopulators().remove(this);
            }
        }
    }

}
