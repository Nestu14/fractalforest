package com.eclipsekingdom.proctree.managers;

import com.eclipsekingdom.proctree.BranchDirection;
import com.eclipsekingdom.proctree.ProcTree;
import com.eclipsekingdom.proctree.functions.BranchFunction;
import com.eclipsekingdom.proctree.functions.LinearBranchFunction;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.HashSet;
import java.util.Random;

public class TreeManager {

    private ProcTree plugin;

    public TreeManager(ProcTree plugin){
        this.plugin = plugin;
    }


    public void spawnOak(Location location){


        Random random = new Random();

        //height
        final int MIN_TRUNK_HEIGHT = 4;
        final int MAX_TRUNK_HEIGHT = 12;
        final int TRUNK_HEIGHT = random.nextInt(MAX_TRUNK_HEIGHT - MIN_TRUNK_HEIGHT + 1) + MIN_TRUNK_HEIGHT;



        Location treeLocation = location.getWorld().getHighestBlockAt(location).getLocation();
        if(treeLocation.clone().add(0,-1,0).getBlock().isLiquid()) return;


        //trunk
        for(int y = 0; y<TRUNK_HEIGHT; y++){
            Location trunkLocation = treeLocation.clone().add(0,y,0);
            Block trunkBlock = trunkLocation.getBlock();
            placeBlock(trunkBlock,Material.OAK_LOG);

            //leaves at top of tree
            if(y == TRUNK_HEIGHT - 1){
                addLeaves(trunkLocation, 2, 80, random);
            }

            //branch
            if(y>3){

                final int BRANCH_CHANCE = 60; //out of 100
                final int MIN_BRANCH_AMOUNT = 1;
                final int MAX_BRANCH_AMOUNT = 5;

                if(random.nextInt(100) <= BRANCH_CHANCE){

                    final int BRANCH_AMOUNT = random.nextInt(MAX_BRANCH_AMOUNT - MIN_BRANCH_AMOUNT + 1) + MIN_BRANCH_AMOUNT;

                    HashSet<BranchDirection> selectedDirections = new HashSet<>();
                    for(int i = 0; i< BRANCH_AMOUNT; i++){
                        if(i == 0){
                            selectedDirections.add(BranchDirection.PLUSX);
                        }else if(i == 1){
                            selectedDirections.add(BranchDirection.MINUSX);
                        }else if(i == 2){
                            selectedDirections.add(BranchDirection.PLUSZ);
                        }else if(i == 3){
                            selectedDirections.add(BranchDirection.MINUSZ);
                        }
                    }

                    for(BranchDirection branchDirection: selectedDirections){
                        final int MIN_BRANCH_CURVE_LIMIT = 1;
                        final int MAX__BRANCH_CURVE_LIMIT = 4;
                        final int BRANCH_CURVE_LIMIT = random.nextInt(MAX__BRANCH_CURVE_LIMIT - MIN_BRANCH_CURVE_LIMIT + 1) + MIN_BRANCH_CURVE_LIMIT;

                        final int MIN_BRANCH_HEIGHT_LIMIT = 1;
                        final int MAX_BRANCH_HEIGHT_LIMIT = 6;
                        final int BRANCH_HEIGHT_LIMIT = random.nextInt(MAX_BRANCH_HEIGHT_LIMIT - MIN_BRANCH_HEIGHT_LIMIT + 1) + MIN_BRANCH_HEIGHT_LIMIT;

                        final int MIN_BRANCH_LENGTH_LIMIT = 1;
                        final int MAX_BRANCH_LENGTH_LIMIT = 6;
                        final int BRANCH_LENGTH_LIMIT = random.nextInt(MAX_BRANCH_LENGTH_LIMIT - MIN_BRANCH_LENGTH_LIMIT + 1) + MIN_BRANCH_LENGTH_LIMIT;

                        final int MIN_BRANCH_CURVE_CO = 0;
                        final int MAx_BRANCH_CURVE_CO = 2;
                        final double BRANCH_CURVE_CO = MIN_BRANCH_CURVE_CO + (random.nextDouble() * (MAx_BRANCH_CURVE_CO - MIN_BRANCH_CURVE_CO));

                        final int MIN_BRANCH_HEIGHT_CO = 0;
                        final int MAX_BRANCH_HEIGHT_CO = 2;
                        final double BRANCH_HEIGHT_CO = MIN_BRANCH_HEIGHT_CO + (random.nextDouble() * (MAX_BRANCH_HEIGHT_CO - MIN_BRANCH_HEIGHT_CO));

                        LinearBranchFunction heightFunc = new LinearBranchFunction("y = "+ BRANCH_HEIGHT_CO +"x",BRANCH_HEIGHT_CO);
                        LinearBranchFunction curveFunc = new LinearBranchFunction("y = "+ BRANCH_CURVE_CO +"x", BRANCH_CURVE_CO);
                        branch(trunkBlock, branchDirection, heightFunc, curveFunc, BRANCH_LENGTH_LIMIT, BRANCH_CURVE_LIMIT, BRANCH_HEIGHT_LIMIT);
                    }


                }
            }

        }


        //roots


        //

    }



    private void placeBlock(Block block, Material material){
        if(block.isPassable()){
            block.setType(material);
        }
    }

    private void branch(Block startBlock, BranchDirection branchDirection, BranchFunction curveUp, BranchFunction curveSide, int maxLength, int maxCurve, int maxHeight){

        Location startLocation = startBlock.getLocation();
        Random random = new Random();

        int previousHeight = 0;
        for(int l = 0; l < maxLength; l++){
            int branchLength = l;
            int branchHeight = curveUp.f(l);
            int branchCurve = curveSide.f(l);
            if(branchCurve > maxCurve || branchHeight > maxHeight) return;

            if (branchDirection == BranchDirection.PLUSX) {
                Location newGrowth = startBlock.getLocation().clone().add(branchLength,branchHeight,branchCurve);
                placeBlock(newGrowth.getBlock(),Material.OAK_LOG);
                if(random.nextInt(100) <= 50){
                    addLeaves(newGrowth, 2, 77, random);
                }
            }else if(branchDirection == BranchDirection.MINUSX){
                Location newGrowth = startBlock.getLocation().clone().add(-1*branchLength,branchHeight,branchCurve);
                placeBlock(newGrowth.getBlock(),Material.OAK_LOG);
                if(random.nextInt(100) <= 50){
                    addLeaves(newGrowth, 2, 77, random);
                }
            }else if(branchDirection == BranchDirection.PLUSZ){
                Location newGrowth = startBlock.getLocation().clone().add(branchCurve,branchHeight,branchLength);
                placeBlock(newGrowth.getBlock(),Material.OAK_LOG);
                if(random.nextInt(100) <= 50){
                    addLeaves(newGrowth, 2, 77, random);
                }
            }else if(branchDirection == BranchDirection.MINUSZ){
                Location newGrowth = startBlock.getLocation().clone().add(branchCurve,branchHeight,-1*branchLength);
                placeBlock(newGrowth.getBlock(),Material.OAK_LOG);
                if(random.nextInt(100) <= 50){
                    addLeaves(newGrowth, 2, 77, random);
                }
            }
        }


    }


    private void addLeaves(Location location, int radius, int spawnChance, Random random){
        for(int x = radius*-1; x<radius; x++){
            for(int y = radius*-1; y<radius; y++){
                for(int z = radius*-1; z<radius; z++){
                    Location leafLocation = location.clone().add(x,y,z);
                    if(location.distance(leafLocation) <= radius + 0.5){
                        if(random.nextInt(100) <= spawnChance){
                            if(random.nextBoolean()){
                                placeBlock(location.clone().add(x,y,z).getBlock(),Material.OAK_LEAVES);
                            }else{
                                placeBlock(location.clone().add(x,y,z).getBlock(),Material.JUNGLE_LEAVES);
                            }
                        }
                    }
                }
            }
        }

    }


}
