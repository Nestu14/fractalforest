package com.eclipsekingdom.fractalforest.worldgen.pop;

import com.eclipsekingdom.fractalforest.trees.ITree;
import com.eclipsekingdom.fractalforest.trees.Species;
import com.eclipsekingdom.fractalforest.trees.habitat.IHabitat;
import com.eclipsekingdom.fractalforest.util.TreeUtil;
import com.eclipsekingdom.fractalforest.worldgen.pop.util.TreeBiome;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.generator.BlockPopulator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public class TreePopulator extends BlockPopulator {

    private String name;
    private LinkedHashMap<TreeBiome, List<TreeSpawner>> biomeToTreeSpawner;
    private static Random rand = new Random();

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
                    Species species = spawner.getSpecies();
                    IHabitat habitat = species.getHabitat();
                    int amount = spawner.nextAmount();
                    for (int i = 0; i < amount; i++) {
                        int overflow = spawner.getOverflow();
                        int x = chunkX + random.nextInt(15 + overflow * 2) - overflow;
                        int z = chunkZ + random.nextInt(15 + overflow * 2) - overflow;
                        Location location = getHighestValid(habitat, world, x, z);
                        if (location != null) {
                            ITree tree = species.getIndividual(null, location);
                            tree.growInstant();
                        }
                    }

                }
            }
        }

    }

    private Location getHighestValid(IHabitat habitat, World world, int x, int z) {
        Location location = (world.getEnvironment() == Environment.NETHER && rand.nextDouble() < 0.5) ? getHighestLowerNetherAt(world, x, z).getLocation() : world.getHighestBlockAt(x, z).getLocation();
        int count = 0;
        while (!habitat.canPlantAt(location) && count < 55) {
            location.add(0, -1, 0);
            count++;
        }
        if (habitat.canPlantAt(location)) {
            return location;
        } else {
            return null;
        }
    }

    private Block getHighestLowerNetherAt(World world, int x, int z) {
        Location location = new Location(world, x, 64, z);
        Block block = location.getBlock();
        while (block.isEmpty() && location.getY() > 0) {
            location.add(0, -1, 0);
            block = location.getBlock();
        }
        return block.isEmpty() ? null : block;
    }

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
