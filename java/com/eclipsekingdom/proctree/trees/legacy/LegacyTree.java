package com.eclipsekingdom.proctree.trees.fractal.legacy;

import com.eclipsekingdom.proctree.BranchDirection;
import com.eclipsekingdom.proctree.functions.BranchFunction;
import com.eclipsekingdom.proctree.functions.LinearBranchFunction;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Random;

public class LegacyTree {

    private Location location;

    public LegacyTree(Location location){
        this.location = location;
    }


    public void build(){
        Random random = new Random();

        //height
        final int MIN_TRUNK_HEIGHT = 5;
        final int MAX_TRUNK_HEIGHT = 12;
        final int TRUNK_HEIGHT = random.nextInt(MAX_TRUNK_HEIGHT - MIN_TRUNK_HEIGHT + 1) + MIN_TRUNK_HEIGHT;


        Location treeLocation = location.getWorld().getHighestBlockAt(location).getLocation();
        if(treeLocation.clone().add(0,-1,0).getBlock().isLiquid()) return;


        //trunk
        for(int y = 0; y<TRUNK_HEIGHT; y++){
            Location trunkLocation = treeLocation.clone().add(0,y,0);
            Block trunkBlock = trunkLocation.getBlock();
            placeBlock(trunkBlock, Material.OAK_LOG);

            //leaves at top of tree
            if(y == TRUNK_HEIGHT - 1){
                addLeaves(trunkLocation, 2, 80, random);
            }

            //branch
            if(y > (TRUNK_HEIGHT / 3)){

                final int BRANCH_CHANCE = 66; //out of 100
                final int MIN_BRANCH_AMOUNT = 1;
                final int MAX_BRANCH_AMOUNT = 7;

                if(random.nextInt(100) <= BRANCH_CHANCE){

                    final int BRANCH_AMOUNT = random.nextInt(MAX_BRANCH_AMOUNT - MIN_BRANCH_AMOUNT + 1) + MIN_BRANCH_AMOUNT;

                    HashSet<BranchDirection> selectedDirections = new HashSet<>();
                    for(int i = 0; i< BRANCH_AMOUNT; i++){
                        int branchDrection = random.nextInt(4);
                        if(branchDrection == 0){
                            selectedDirections.add(BranchDirection.PLUSX);
                        }else if(branchDrection == 1){
                            selectedDirections.add(BranchDirection.MINUSX);
                        }else if(branchDrection == 2){
                            selectedDirections.add(BranchDirection.PLUSZ);
                        }else if(branchDrection == 3){
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
                        final int MAX_BRANCH_LENGTH_LIMIT = (int)((TRUNK_HEIGHT-y)/3) + 1;
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
    }

    private void placeBlock(Block block, Material material){
        if(block.isPassable() || block.getType() == Material.JUNGLE_LEAVES || block.getType() == Material.OAK_LEAVES){
            block.setType(material);
        }
    }

    private void branch(Block startBlock, BranchDirection branchDirection, BranchFunction curveUp, BranchFunction curveSide, int maxLength, int maxCurve, int maxHeight){
        Random random = new Random();
        if(maxCurve == 0){
            addLeaves(startBlock.getLocation(),1,100, random);
            return;
        }

        Material branchMaterial = Material.OAK_LOG;
        if(maxLength <= 3){
            branchMaterial = Material.SPRUCE_FENCE;
        }

        Location prevGrowth = null;
        for(int l = 0; l < maxLength; l++){
            int branchLength = l;
            int branchHeight = curveUp.f(l);
            int branchCurve = curveSide.f(l);
            if(branchCurve > maxCurve || branchHeight > maxHeight){
                if(prevGrowth != null){
                    addLeaves(prevGrowth,1,100, random);
                }
                return;
            }

            Location newGrowth = null;
            if (branchDirection == BranchDirection.PLUSX) {
                newGrowth = startBlock.getLocation().clone().add(branchLength,branchHeight,branchCurve);
            }else if(branchDirection == BranchDirection.MINUSX){
                newGrowth = startBlock.getLocation().clone().add(-1*branchLength,branchHeight,branchCurve);
            }else if(branchDirection == BranchDirection.PLUSZ){
                newGrowth = startBlock.getLocation().clone().add(branchCurve,branchHeight,branchLength);
            }else if(branchDirection == BranchDirection.MINUSZ){
                newGrowth = startBlock.getLocation().clone().add(branchCurve,branchHeight,-1*branchLength);
            }

            if(l == maxLength - 1){
                addLeaves(newGrowth, 1, 100, random);
            }

            if(newGrowth != null){
                placeBlock(newGrowth.getBlock(),branchMaterial);
                if(random.nextInt(100) <= 50 || l+1 == maxLength){
                    addLeaves(newGrowth, 1, 77, random);
                }
                if(prevGrowth != null){
                    connectBranchSegments(newGrowth, prevGrowth, branchMaterial, curveUp, curveSide, maxLength, maxCurve, maxHeight, l);
                }
                prevGrowth = newGrowth;
            }

            //fractal branch chance
            int BRANCH_CHANCE = 50;
            if(random.nextInt(100) <= BRANCH_CHANCE){
                BranchDirection fractleBranchDirection = null;
                int fbranchDir = random.nextInt(4);
                if(fbranchDir == 0){
                    fractleBranchDirection = BranchDirection.PLUSX;
                }else if(fbranchDir == 1){
                    fractleBranchDirection = BranchDirection.MINUSX;
                }else if(fbranchDir == 2){
                    fractleBranchDirection = BranchDirection.PLUSZ;
                }else if(fbranchDir == 3){
                    fractleBranchDirection = BranchDirection.MINUSZ;
                }
                branch(newGrowth.getBlock(), fractleBranchDirection, curveUp, curveSide, (int)((maxLength-l)/3), maxCurve, maxHeight);
            }

        }

    }

    private void connectBranchSegments(Location newGrowth, Location oldGrowth, Material branchMaterial, BranchFunction curveUp, BranchFunction curveSide, int maxLength, int maxCurve, int maxHeight, int l){
        double gapDistance = newGrowth.distance(oldGrowth);
        if(gapDistance <= 1){
            return;
        }else{
            Vector gapDirection = new Vector(newGrowth.getX() - oldGrowth.getX(), newGrowth.getY() - oldGrowth.getY(), newGrowth.getZ() - oldGrowth.getZ());
            BlockIterator blockIterator = new BlockIterator(newGrowth.getWorld(),newGrowth.toVector(),gapDirection,0.0D, (int)gapDistance+1);
            while(blockIterator.hasNext()){
                Block currentBlock = blockIterator.next();
                currentBlock.setType(branchMaterial);
                if(!blockIterator.hasNext()){
                    addLeaves(currentBlock.getLocation(), 1, 100, new Random());
                }else{
                    //fractal branch chance
                    int BRANCH_CHANCE = 50;
                    Random random = new Random();
                    if(random.nextInt(100) <= BRANCH_CHANCE){
                        BranchDirection fractleBranchDirection = null;
                        int fbranchDir = random.nextInt(4);
                        if(fbranchDir == 0){
                            fractleBranchDirection = BranchDirection.PLUSX;
                        }else if(fbranchDir == 1){
                            fractleBranchDirection = BranchDirection.MINUSX;
                        }else if(fbranchDir == 2){
                            fractleBranchDirection = BranchDirection.PLUSZ;
                        }else if(fbranchDir == 3){
                            fractleBranchDirection = BranchDirection.MINUSZ;
                        }
                        branch(newGrowth.getBlock(), fractleBranchDirection, curveUp, curveSide, (int)((maxLength-l)/3), maxCurve, maxHeight);
                    }
                }
            }
        }
    }


    private void addLeaves(Location location, int radius, int spawnChance, Random random){
        for(int x = radius*-1; x<radius+0.5; x++){
            for(int y = 0; y<radius+0.5; y++){
                for(int z = radius*-1; z<radius+0.5; z++){
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
