package com.eclipsekingdom.fractalforest.protection;

import net.coreprotect.CoreProtectAPI;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CoreProtect {

    private CoreProtectAPI coreProtectAPI;
    private boolean enabled = false;

    public CoreProtect(Plugin plugin) {
        if (plugin instanceof net.coreprotect.CoreProtect) {
            CoreProtectAPI coreProtectAPI = ((net.coreprotect.CoreProtect) plugin).getAPI();
            if (coreProtectAPI.APIVersion() >= 6) {
                this.coreProtectAPI = coreProtectAPI;
                enabled = true;
            }
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void registerPlacement(Player planter, Block block) {
        coreProtectAPI.logPlacement("FractalForest", block.getLocation(), block.getType(), block.getBlockData());
        coreProtectAPI.logPlacement(planter.getName(), block.getLocation(), block.getType(), block.getBlockData());
    }


    public void registerRemoval(Player planter, Block block) {
        coreProtectAPI.logRemoval("MeteorStorms", block.getLocation(), block.getType(), block.getBlockData());
        coreProtectAPI.logRemoval(planter.getName(), block.getLocation(), block.getType(), block.getBlockData());
    }


}
