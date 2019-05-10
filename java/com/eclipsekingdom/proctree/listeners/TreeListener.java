package com.eclipsekingdom.proctree.listeners;

import com.eclipsekingdom.proctree.ProcTree;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.LeavesDecayEvent;

public class TreeListener implements Listener {

    private ProcTree plugin;

    public TreeListener(ProcTree  plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDecay(LeavesDecayEvent e){
        e.setCancelled(true);
    }


    @EventHandler
    public void onPopOff(BlockPhysicsEvent e){
        if(e.getBlock().getType() == Material.AZURE_BLUET || e.getBlock().getType() == Material.FERN){
            //e.setCancelled(true);
        }
    }

}
