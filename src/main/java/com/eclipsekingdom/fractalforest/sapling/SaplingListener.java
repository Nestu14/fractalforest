package com.eclipsekingdom.fractalforest.sapling;

import com.eclipsekingdom.fractalforest.FractalForest;
import com.eclipsekingdom.fractalforest.phylo.Species;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SaplingListener implements Listener {

    public static Map<Location, ItemStack> locationToSapling = new HashMap<>();

    public static void shutdown() {
        for (Map.Entry<Location, ItemStack> entry : locationToSapling.entrySet()) {
            Location location = entry.getKey();
            location.getWorld().dropItemNaturally(location, entry.getValue());
            Block block = location.getBlock();
            block.setType(Material.AIR);
        }
    }

    public SaplingListener() {
        FractalForest plugin = FractalForest.plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlace(BlockPlaceEvent e) {
        ItemStack itemStack = e.getItemInHand();
        if (itemStack != null) {
            if (isSapling(itemStack)) {
                ItemStack singleSapling = itemStack.clone();
                singleSapling.setAmount(1);
                Species species = getSpecies(itemStack);
                if (e.getPlayer().getGameMode() != GameMode.CREATIVE) itemStack.setAmount(itemStack.getAmount() - 1);
                Location location = e.getBlock().getLocation();
                locationToSapling.put(location, singleSapling);
                new MagicSapling(species, location.clone().add(0.5, 0, 0.5));
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBreak(BlockBreakEvent e) {
        Location location = e.getBlock().getLocation();
        if (locationToSapling.containsKey(location)) {
            if (e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
                e.setDropItems(false);
                ItemStack itemStack = locationToSapling.get(location);
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation().add(0.5, 0, 0.5), itemStack);
                locationToSapling.remove(location);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockMove(BlockFromToEvent e) {
        Location location = e.getToBlock().getLocation();
        if (locationToSapling.containsKey(location)) {
            ItemStack itemStack = locationToSapling.get(location);
            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation().add(0.5, 0, 0.5), itemStack);
            blockedDropLocations.add(location);
            locationToSapling.remove(location);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPiston(BlockPistonExtendEvent e) {
        for(Block block: e.getBlocks()){
            Location location = block.getLocation();
            if (locationToSapling.containsKey(location)) {
                ItemStack itemStack = locationToSapling.get(location);
                block.getWorld().dropItemNaturally(block.getLocation().add(0.5, 0, 0.5), itemStack);
                blockedDropLocations.add(location);
                locationToSapling.remove(location);
                break;
            }
        }
    }

    private Set<Location> blockedDropLocations = new HashSet<>();

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDrop(ItemSpawnEvent e){
        Location location = e.getLocation().getBlock().getLocation();
        if(blockedDropLocations.contains(location)){
            e.setCancelled(true);
            blockedDropLocations.remove(location);
        }
    }

    private boolean isSapling(ItemStack itemStack) {
        return (Tag.SAPLINGS.isTagged(itemStack.getType()) && itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore() && itemStack.getItemMeta().getLore().size() > 0);
    }

    private Species getSpecies(ItemStack sapling) {
        String string = sapling.getItemMeta().getLore().get(0).split(ChatColor.DARK_GREEN + "Species: " + ChatColor.GRAY)[1];
        return Species.from(string);
    }


}
