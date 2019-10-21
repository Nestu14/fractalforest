package com.eclipsekingdom.fractalforest.gui.pop.session;

import com.eclipsekingdom.fractalforest.gui.pop.PopPage;
import com.eclipsekingdom.fractalforest.populator.TreePopulator;

public class PopSessionData {

    private TreePopulator populator;
    private boolean initialCreate;
    private PopPage current;
    private boolean transitioning;

    public PopSessionData(PopPage current, TreePopulator populator, boolean initialCreate) {
        this.current = current;
        this.populator = populator;
        this.transitioning = false;
        this.initialCreate = initialCreate;
    }

    public TreePopulator getPopulator() {
        return populator;
    }

    public boolean isInitialCreate() {
        return initialCreate;
    }

    public PopPage getCurrent() {
        return current;
    }

    public void setCurrent(PopPage current) {
        this.current = current;
    }

    public boolean isTransitioning() {
        return transitioning;
    }

    public void setTransitioning(boolean transitioning){
        this.transitioning = transitioning;
    }

}
