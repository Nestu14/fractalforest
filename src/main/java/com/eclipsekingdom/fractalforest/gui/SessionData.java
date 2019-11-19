package com.eclipsekingdom.fractalforest.gui;

import com.eclipsekingdom.fractalforest.gui.page.MenuUtil;
import com.eclipsekingdom.fractalforest.gui.page.Page;
import com.eclipsekingdom.fractalforest.gui.page.PageContents;
import com.eclipsekingdom.fractalforest.gui.page.PageType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SessionData {

    private PopData popData;
    private GenData genData;
    private Type type;

    private Page current;
    private boolean transitioning = false;
    private int pageOffsetX;
    private int pageOffsetY;

    public SessionData(Type type, Page start) {
        this.pageOffsetX = 0;
        this.pageOffsetY = 0;
        this.current = start;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public PopData getPopData() {
        return popData;
    }

    public void setPopData(PopData popData) {
        this.popData = popData;
    }

    public GenData getGenData() {
        return genData;
    }

    public void setGenData(GenData genData) {
        this.genData = genData;
    }

    public Page getCurrent() {
        return current;
    }

    public void transition(Player player, PageType to) {
        MenuUtil.playClickSound(player);
        pageOffsetX = 0;
        pageOffsetY = 0;
        current = to.getPage();
        transitioning = true;
        player.openInventory(current.getInventory(this));
        transitioning = false;
    }

    public boolean isTransitioning() {
        return transitioning;
    }

    public int getPageOffsetX() {
        return pageOffsetX;
    }


    public int getPageOffsetY() {
        return pageOffsetY;
    }


    public void scrollUp(Player player, PageContents contents, Inventory menu) {
        pageOffsetY--;
        if (pageOffsetY < 0) {
            pageOffsetY = 0;
        }
        MenuUtil.playClickSound(player);
        contents.populate(menu, this);
    }

    public void scrollDown(Player player, PageContents contents, Inventory menu) {
        pageOffsetY++;
        MenuUtil.playClickSound(player);
        contents.populate(menu, this);
    }

    public void scrollRight(Player player, PageContents contents, Inventory menu) {
        pageOffsetX++;
        MenuUtil.playClickSound(player);
        contents.populate(menu, this);
    }

    public void scrollLeft(Player player, PageContents contents, Inventory menu) {
        pageOffsetX--;
        if (pageOffsetX < 0) {
            pageOffsetX = 0;
        }
        MenuUtil.playClickSound(player);
        contents.populate(menu, this);
    }

    public enum Type {
        POP, GEN, SAP;
    }

}
