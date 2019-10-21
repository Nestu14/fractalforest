package com.eclipsekingdom.fractalforest.gui;

public abstract class Page {

    private String title;
    private int rows;

    public Page(int rows, String title) {
        this.rows = rows;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getRows() {
        return rows;
    }

    public abstract boolean hasPrevious();

}
