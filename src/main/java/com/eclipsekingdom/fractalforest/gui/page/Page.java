package com.eclipsekingdom.fractalforest.gui.page;

import com.eclipsekingdom.fractalforest.gui.SessionData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Page {

    private String title;
    private int rows;
    private PageContents contents;
    private Inventory baseInventory;
    private PageType previousPage;

    public Page(MenuType menuType, int rows, String title, PageContents contents, PageType previousPage) {
        this.rows = rows;
        this.title = title;
        this.contents = contents;
        this.previousPage = previousPage;
        this.baseInventory = MenuUtil.createGeneric(this, menuType);
    }

    public String getTitle() {
        return title;
    }

    public int getRows() {
        return rows;
    }

    public Inventory getInventory(SessionData sessionData) {
        return contents.populate(baseInventory, sessionData);
    }

    public void processClick(Player player, Inventory menu, SessionData sessionData, int slot) {
        if (previousPage != null && slot == 0) {
            sessionData.transition(player, previousPage);
        } else {
            contents.processClick(player, menu, sessionData, slot);
        }
    }

    public boolean hasPrevious() {
        return previousPage != null;
    }


}
