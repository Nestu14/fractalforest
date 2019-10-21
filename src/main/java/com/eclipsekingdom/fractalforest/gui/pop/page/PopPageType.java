package com.eclipsekingdom.fractalforest.gui.pop.page;

import com.eclipsekingdom.fractalforest.gui.pop.PopPage;

public enum PopPageType {

    HOME(new PopPage(null, 3, "Overview", new Home())),
    WORLD_OVERVIEW(new PopPage(HOME, 4, "Worlds", new WorldOverview())),
    WORLD_SELECT(new PopPage(WORLD_OVERVIEW, 6, "World Selection", new WorldSelect())),
    GENERATION(new PopPage(HOME, 6, "Generation", new Generation())),
    BIOME_OVERVIEW(new PopPage(GENERATION, 4, "Biomes", new BiomeOverview())),
    BIOME_SELECT(new PopPage(BIOME_OVERVIEW, 6,"Biome Selection", new BiomeSelect())),
    TREE_OVERVIEW(new PopPage(GENERATION, 4, "Trees", new TreeOverview())),
    TREE_SELECT(new PopPage(TREE_OVERVIEW, 6, "Tree Selection", new TreeSelect())),
    SPAWNER(new PopPage(HOME, 3, "Spawner", new Spawner())),
    CHANCE(new PopPage(SPAWNER, 3, "Spawn Chance", new Chance())),
    AMOUNT(new PopPage(SPAWNER, 3, "Spawn Amount", new Amount())),

    ;

    private PopPage page;

    PopPageType(PopPage page) {
        this.page = page;
    }

    public PopPage getPage() {
        return page;
    }
}
