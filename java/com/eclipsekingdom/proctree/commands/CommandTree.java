package com.eclipsekingdom.proctree.commands;

import com.eclipsekingdom.proctree.ProcTree;
import com.eclipsekingdom.proctree.managers.TreeManager;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

        if(args.length > 0 && args[0].equalsIgnoreCase("test")){
            Block target = player.getTargetBlock(null, 130);
            treeManager.test(target.getLocation());

        }

        return false;
    }

}
