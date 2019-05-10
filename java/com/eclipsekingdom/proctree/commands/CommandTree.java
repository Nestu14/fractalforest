package com.eclipsekingdom.proctree.commands;

import com.eclipsekingdom.proctree.FunctionIterator;
import com.eclipsekingdom.proctree.ProcTree;
import com.eclipsekingdom.proctree.functions.SineBranchFunction;
import com.eclipsekingdom.proctree.managers.TreeManager;
import com.eclipsekingdom.proctree.util.TreeMathUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class CommandTree implements CommandExecutor {

    private ProcTree plugin;
    private TreeManager treeManager;

    public CommandTree(ProcTree plugin){
        this.plugin = plugin;
        this.treeManager = plugin.getTreeManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;

        if(!player.hasPermission("tree.*")){
            player.sendMessage(ChatColor.RED + "You do not have permission for this command");
            return false;
        }

        if(args.length > 0 && args[0].equalsIgnoreCase("oak")){
            treeManager.spawnOak(player.getTargetBlock(null, 130).getLocation());
        }

        if(args.length > 0 && args[0].equalsIgnoreCase("foakmb2")){
            Block target = player.getTargetBlock(null, 130);
            treeManager.spawnFOak2B(target.getLocation());
        }

        if(args.length > 0 && args[0].equalsIgnoreCase("foakmb3")){
            Block target = player.getTargetBlock(null, 130);
            treeManager.spawnFOak3B(target.getLocation());
        }

        if(args.length > 0 && args[0].equalsIgnoreCase("foakb4")){
            Block target = player.getTargetBlock(null, 130);
            treeManager.spawnFOak4B(target.getLocation());
        }

        if(args.length > 0 && args[0].equalsIgnoreCase("foakmixed")){
            Block target = player.getTargetBlock(null, 130);
            treeManager.spawnFOakMixed(target.getLocation());
        }


        if(args.length > 0 && args[0].equalsIgnoreCase("test")){

            Vector rX = player.getLocation().getDirection();
            Vector rY = TreeMathUtil.getRandomPerpVector(rX);

            FunctionIterator functionIterator = new FunctionIterator(player.getWorld(),player.getLocation().toVector(), 60, rX, rY, 1.2, new SineBranchFunction("y = 4sin(x)", 4, 0.5));
            for(Block b: functionIterator){
                b.setType(Material.SPONGE);
            }

        }

        return false;
    }

}
