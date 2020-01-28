package com.eclipsekingdom.fractalforest.gui.page.gen;

import com.eclipsekingdom.fractalforest.gui.SessionData;
import com.eclipsekingdom.fractalforest.gui.page.Icons;
import com.eclipsekingdom.fractalforest.gui.page.MenuUtil;
import com.eclipsekingdom.fractalforest.gui.page.PageContents;
import com.eclipsekingdom.fractalforest.util.X.XMaterial;
import com.eclipsekingdom.fractalforest.worldgen.Generator;
import com.eclipsekingdom.fractalforest.worldgen.WorldData;
import com.eclipsekingdom.fractalforest.worldgen.pop.PopCache;
import com.eclipsekingdom.fractalforest.worldgen.pop.TreePopulator;
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

import static com.eclipsekingdom.fractalforest.sys.language.Message.*;

public class PopSelection implements PageContents {

    @Override
    public Inventory populate(Inventory menu, SessionData sessionData) {
        World world = sessionData.getGenData().getCurrentWorld();
        List<TreePopulator> populators = new ArrayList<>();
        populators.addAll(PopCache.getPopulators());
        populators.add(null);

        WorldData worldData = Generator.getWorldData(world);
        if (worldData.hasTreePopulator()) {
            menu.setItem(4, Icons.createPopItem(worldData.getTreePopulator()));
        } else {
            menu.setItem(4, Icons.createIcon(Material.BARRIER, LABEL_NONE.toString()));
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
                    menu.setItem(index, Icons.createIcon(Material.BARRIER, LABEL_NONE.toString()));
                }
            } else {
                menu.setItem(index, Icons.BACKGROUND_ITEM);
            }
            index += ((index + 2) % 9 == 0 ? 3 : 1);
        }

        menu.setItem(17, Icons.createIcon(Material.TRIPWIRE_HOOK, MENU_SCROLL_UP.toString()));
        menu.setItem(26, Icons.createIcon(Material.STONE_BUTTON, "+" + sessionData.getPageOffsetY()));
        menu.setItem(35, Icons.createIcon(Material.HOPPER, MENU_SCROLL_DOWN.toString()));

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
                MenuUtil.playClickSound(player);
                TreePopulator treePopulator = PopCache.getPopulator(itemStack.getItemMeta().getDisplayName());
                World world = sessionData.getGenData().getCurrentWorld();
                WorldData worldData = Generator.getWorldData(world);
                worldData.setTreePopulator(world, treePopulator);
                sessionData.registerEdit();
                populate(menu, sessionData);
            }
        }
    }

    private Set<Material> popMaterial = new ImmutableSet.Builder<Material>()
            .add(XMaterial.WHEAT_SEEDS.parseMaterial())
            .add(Material.BARRIER)
            .build();

}
