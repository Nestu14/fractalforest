package com.eclipsekingdom.fractalforest.gui.page.species;

import com.eclipsekingdom.fractalforest.gui.SessionData;
import com.eclipsekingdom.fractalforest.gui.page.Icons;
import com.eclipsekingdom.fractalforest.gui.page.PageContents;
import com.eclipsekingdom.fractalforest.phylo.Species;
import com.google.common.collect.ImmutableSet;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public class SpeciesOverview implements PageContents {
    @Override
    public Inventory populate(Inventory menu, SessionData sessionData) {

        Species[] species = Species.values();
        int offsetY = sessionData.getPageOffsetY();

        int index = 10;
        for (int i = 0; i < 28; i++) {
            int speciesIndex = i + (7 * offsetY);
            if (speciesIndex < species.length) {
                menu.setItem(index, species[speciesIndex].getSapling());
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
            if (itemStack != null && saplingMaterials.contains(itemStack.getType())) {
                ItemStack cursor = player.getItemOnCursor();
                if (cursor == null || cursor.getType() == Material.AIR) {
                    if (clickType.isShiftClick()) {
                        ItemStack toGive = itemStack.clone();
                        toGive.setAmount(64);
                        player.setItemOnCursor(toGive);
                    } else {
                        player.setItemOnCursor(itemStack.clone());
                    }
                } else {
                    if (cursor.hasItemMeta() && cursor.getItemMeta().equals(itemStack.getItemMeta())) {
                        int amount = clickType.isShiftClick() ? 64 : cursor.getAmount() + 1;
                        cursor.setAmount(amount);
                        player.setItemOnCursor(cursor);
                    } else {
                        player.setItemOnCursor(AIR);
                    }
                }
            } else if (itemStack != null && itemStack.getType() == Material.WHITE_STAINED_GLASS_PANE) {
                player.setItemOnCursor(AIR);
            }
        }
    }

    private static Set<Material> saplingMaterials = new ImmutableSet.Builder<Material>()
            .add(Material.OAK_SAPLING)
            .add(Material.SPRUCE_SAPLING)
            .add(Material.ACACIA_SAPLING)
            .add(Material.BIRCH_SAPLING)
            .add(Material.DARK_OAK_SAPLING)
            .add(Material.JUNGLE_SAPLING)
            .build();

    private ItemStack AIR = new ItemStack(Material.AIR);
}
