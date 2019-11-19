package com.eclipsekingdom.fractalforest.gui.page.gen;

import com.eclipsekingdom.fractalforest.gen.Generator;
import com.eclipsekingdom.fractalforest.gen.WorldData;
import com.eclipsekingdom.fractalforest.gen.pop.PopCache;
import com.eclipsekingdom.fractalforest.gen.pop.TreePopulator;
import com.eclipsekingdom.fractalforest.gui.SessionData;
import com.eclipsekingdom.fractalforest.gui.page.Icons;
import com.eclipsekingdom.fractalforest.gui.page.PageContents;
import com.google.common.collect.ImmutableSet;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PopSelection implements PageContents {

    @Override
    public Inventory populate(Inventory menu, SessionData sessionData) {
        World world = sessionData.getGenData().getCurrentWorld();
        List<TreePopulator> populators = new ArrayList<>();
        populators.addAll(PopCache.getPopulators());
        populators.add(null);

        WorldData worldData = Generator.getWorldData(world);
        if (worldData.hasTreePopulator()) {
            menu.setItem(4, Icons.createIcon(Material.WHEAT_SEEDS, worldData.getTreePopulator().getName()));
        } else {
            menu.setItem(4, Icons.createIcon(Material.BARRIER, "None"));
        }

        int offsetY = sessionData.getPageOffsetY();
        int index = 10;
        for (int i = 0; i < 28; i++) {
            int popIndex = i + (7 * offsetY);
            if (popIndex < populators.size()) {
                TreePopulator populator = populators.get(popIndex);
                if (populator != null) {
                    menu.setItem(index, Icons.createPopItem(populator));
                } else {
                    menu.setItem(index, Icons.createIcon(Material.BARRIER, "None"));
                }
            } else {
                menu.setItem(index, Icons.BACKGROUND_ITEM);
            }
            index += ((index + 2) % 9 == 0 ? 3 : 1);
        }

        menu.setItem(17, Icons.createIcon(Material.TRIPWIRE_HOOK, "Scroll Up"));
        menu.setItem(26, Icons.createIcon(Material.STONE_BUTTON, "+" + sessionData.getPageOffsetY()));
        menu.setItem(35, Icons.createIcon(Material.HOPPER, "Scroll Down"));

        return menu;
    }

    @Override
    public void processClick(Player player, Inventory menu, SessionData sessionData, int slot, ClickType clickType) {

        if (slot == 17) {
            sessionData.scrollUp(player, this, menu);
        } else if (slot == 35) {
            sessionData.scrollDown(player, this, menu);
        } else {
            ItemStack itemStack = menu.getItem(slot);
            if (itemStack != null && popMaterial.contains(itemStack.getType())) {
                TreePopulator treePopulator = PopCache.getPopulator(itemStack.getItemMeta().getDisplayName());
                World world = sessionData.getGenData().getCurrentWorld();
                WorldData worldData = Generator.getWorldData(world);
                worldData.setTreePopulator(world, treePopulator);
                populate(menu, sessionData);
            }
        }
    }

    private Set<Material> popMaterial = new ImmutableSet.Builder<Material>()
            .add(Material.WHEAT_SEEDS)
            .add(Material.BARRIER)
            .build();

}
