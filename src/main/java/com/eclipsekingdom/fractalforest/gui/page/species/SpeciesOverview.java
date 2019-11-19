package com.eclipsekingdom.fractalforest.gui.page.species;

import com.eclipsekingdom.fractalforest.gui.SessionData;
import com.eclipsekingdom.fractalforest.gui.page.Icons;
import com.eclipsekingdom.fractalforest.gui.page.PageContents;
import com.eclipsekingdom.fractalforest.phylo.Species;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SpeciesOverview implements PageContents {
    @Override
    public Inventory populate(Inventory menu, SessionData sessionData) {

        Species[] species = Species.values();
        int offsetY = sessionData.getPageOffsetY();

        int index = 10;
        for (int i = 0; i < 28; i++) {
            int speciesIndex = i + (7 * offsetY);
            if ( speciesIndex < species.length ) {
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
    public void processClick(Player player, Inventory menu, SessionData sessionData, int slot) {

    }
}
