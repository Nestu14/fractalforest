package com.eclipsekingdom.proctree.sapling;

import com.eclipsekingdom.proctree.ProcTree;
import com.eclipsekingdom.proctree.trees.Seed;
import com.eclipsekingdom.proctree.trees.Species;
import com.google.common.collect.ImmutableSet;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SaplingListener implements Listener {

    public SaplingListener(){
        ProcTree plugin = ProcTree.plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlace(PlayerInteractEvent e){
        if(e.hasItem() && e.getAction() == Action.RIGHT_CLICK_BLOCK && saplingBlocks.contains(e.getClickedBlock().getType())){
            ItemStack itemStack = e.getItem();
            if(isSapling(itemStack)){
                Species species = getSpecies(itemStack);
                e.setCancelled(true);
                if(e.getPlayer().getGameMode() != GameMode.CREATIVE){
                    itemStack.setAmount(itemStack.getAmount()-1);
                }
                new Seed(species.getTree(), e.getClickedBlock().getLocation().add(0.5,0,0.5));
            }
        }
    }

    private static ImmutableSet<Material> saplingBlocks = new ImmutableSet.Builder<Material>()
            .add(Material.GRASS_BLOCK)
            .add(Material.GRASS)
            .add(Material.TALL_GRASS)
            .add(Material.FERN)
            .add(Material.LARGE_FERN)
            .build();

    private boolean isSapling(ItemStack itemStack){
        return (itemStack.getType() == Material.OAK_SAPLING && itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore() && itemStack.getItemMeta().getLore().size() > 0);
    }

    private Species getSpecies(ItemStack sapling){
        String string = sapling.getItemMeta().getLore().get(0).split(ChatColor.DARK_GREEN + "Species: " + ChatColor.GRAY)[1];
        return Species.from(string);
    }


}
