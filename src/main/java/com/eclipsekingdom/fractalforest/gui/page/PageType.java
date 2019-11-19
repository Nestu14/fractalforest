package com.eclipsekingdom.fractalforest.gui.page;

import com.eclipsekingdom.fractalforest.gui.page.pop.*;
import com.eclipsekingdom.fractalforest.gui.page.sapling.SaplingOverview;

import static com.eclipsekingdom.fractalforest.gui.page.MenuType.SAPLING;
import static com.eclipsekingdom.fractalforest.gui.page.MenuType.TREE_POPULATOR;

public enum PageType {

    HOME(new Page(TREE_POPULATOR, 3, "Overview", new Home(), null)),
    WORLD_OVERVIEW(new Page(TREE_POPULATOR, 4, "Worlds", new WorldOverview(), HOME)),
    WORLD_SELECT(new Page(TREE_POPULATOR, 6, "World Selection", new WorldSelect(), WORLD_OVERVIEW)),
    GENERATION(new Page(TREE_POPULATOR, 6, "Generation", new Generation(), HOME)),
    BIOME_OVERVIEW(new Page(TREE_POPULATOR, 4, "Biomes", new BiomeOverview(), GENERATION)),
    BIOME_SELECT(new Page(TREE_POPULATOR, 6, "Biome Selection", new BiomeSelect(), BIOME_OVERVIEW)),
    TREE_OVERVIEW(new Page(TREE_POPULATOR, 4, "Trees", new TreeOverview(), GENERATION)),
    TREE_SELECT(new Page(TREE_POPULATOR, 6, "Tree Selection", new TreeSelect(), TREE_OVERVIEW)),
    SPAWNER(new Page(TREE_POPULATOR, 3, "Spawner", new Spawner(), GENERATION)),
    CHANCE(new Page(TREE_POPULATOR, 3, "Spawn Chance", new Chance(), SPAWNER)),
    AMOUNT_MIN(new Page(TREE_POPULATOR, 3, "Spawn AmountMax Min", new AmountMin(), SPAWNER)),
    AMOUNT_MAX(new Page(TREE_POPULATOR, 3, "Spawn AmountMax Max", new AmountMax(), SPAWNER)),

    SAPLING_OVERVIEW(new Page(SAPLING, 6, "Selection", new SaplingOverview(), null));

    private Page page;

    PageType(Page page) {
        this.page = page;
    }

    public Page getPage() {
        return page;
    }

}
