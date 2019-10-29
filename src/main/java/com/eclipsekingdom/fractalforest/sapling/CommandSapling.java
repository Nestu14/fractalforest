package com.eclipsekingdom.fractalforest.sapling;

import com.eclipsekingdom.fractalforest.Permissions;
import com.eclipsekingdom.fractalforest.phylo.Species;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static com.eclipsekingdom.fractalforest.util.language.Message.WARN_NO_PERMISSION;

public class CommandSapling implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(Permissions.canSummonSapling(player)){
                if(args.length > 0){
                    if(args[0].equalsIgnoreCase("list")){
                        sendSaplingList(player);
                    }else{
                        Species species = Species.from(args[0]);
                        if(species != null){
                            int amount = 1;
                            if(args.length > 1){
                                try{
                                    amount = Integer.parseInt(args[1]);
                                }catch (Exception e){
                                    amount = 1;
                                }
                            }
                            ItemStack sapling = species.getSapling();
                            sapling.setAmount(amount);
                            player.getInventory().addItem(sapling);
                        }else{
                            player.sendMessage(ChatColor.RED + "Unrecognized species");
                            player.sendMessage(ChatColor.GRAY + "Use " +ChatColor.RED+  "/sapling list " + ChatColor.GRAY + "to list all saplings");
                        }
                    }
                }else{
                    player.sendMessage(ChatColor.RED + "Format is /sapling [type]");
                }
            }else{
                player.sendMessage(ChatColor.RED + WARN_NO_PERMISSION.toString());
            }

        }
        return false;
    }

    private void sendSaplingList(Player player){
        player.sendMessage(ChatColor.DARK_GREEN + "Saplings:");
        for(Species species: Species.values()){
            player.sendMessage("- " + species.toString());
        }
    }

            /*
            SpaceColinizationGrowth spaceColinizationGrowth = new SpaceColinizationGrowth(77, 1, 7, 9, 9, 1, 7);
            spaceColinizationGrowth.generateTree();
            Vector seed = target.getLocation().toVector();
            World world = target.getLocation().getWorld();
            for(Segment b: spaceColinizationGrowth.getBranches()){
                double radius = (b.getPosition().getY() < 9)? 1: 0.5;
                for(Block block: new SegmentIterator(world,b.getPosition().add(seed),b.getEnd(1).add(seed), radius)){
                    block.setType(Material.OAK_LOG);
                }
            }
            for(LeafCluster leaves: spaceColinizationGrowth.getLeaves()){
                buildLeaves(world, seed, leaves);
            }
            */
}
