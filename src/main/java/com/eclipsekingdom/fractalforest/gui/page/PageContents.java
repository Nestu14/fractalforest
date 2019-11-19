package com.eclipsekingdom.fractalforest.gui.page;

import com.eclipsekingdom.fractalforest.gui.SessionData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface PageContents {

    Inventory populate(Inventory menu, SessionData sessionData);

    void processClick(Player player, Inventory menu, SessionData sessionData, int slot);

}
