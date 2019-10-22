package com.eclipsekingdom.fractalforest.gui.pop.page;

import com.eclipsekingdom.fractalforest.gui.pop.session.PopSessionData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class PopPageContents {

    public abstract Inventory populate(Inventory menu, PopSessionData popSessionData);

    public abstract void processClick(Player player, Inventory menu, PopSessionData popSessionData, int slot);
}
