package com.eclipsekingdom.fractalforest.gui.pop;

import com.eclipsekingdom.fractalforest.gui.MenuUtil;
import com.eclipsekingdom.fractalforest.gui.Page;
import com.eclipsekingdom.fractalforest.gui.pop.page.PopPageContents;
import com.eclipsekingdom.fractalforest.gui.pop.page.PopPageType;
import com.eclipsekingdom.fractalforest.gui.pop.session.PopSessionData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PopPage extends Page {

    private PopPageContents pageContents;
    private Inventory baseInventory;
    private PopPageType previousPage;

    public PopPage(PopPageType previousPage, int rows, String title, PopPageContents pageContents) {
        super(rows, title);
        this.previousPage = previousPage;
        this.pageContents = pageContents;
        this.baseInventory = MenuUtil.createGenericPop(this);
    }

    public Inventory getInventory(PopSessionData popSessionData) {
        return pageContents.populate(baseInventory, popSessionData);
    }

    public void processClick(Player player, Inventory menu, PopSessionData popSessionData, int slot) {
        if (previousPage != null && slot == 0) {
            MenuUtil.playClickSound(player);
            PopPage prev = previousPage.getPage();
            popSessionData.setTransitioning(true);
            player.openInventory(prev.getInventory(popSessionData));
            popSessionData.setTransitioning(false);
            popSessionData.setCurrent(prev);
        } else {
            pageContents.processClick(player, menu, popSessionData, slot);
        }
    }

    @Override
    public boolean hasPrevious() {
        return previousPage != null;
    }
}
