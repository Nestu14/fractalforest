package com.eclipsekingdom.fractalforest.gui.page;

import com.eclipsekingdom.fractalforest.gui.page.gen.GeneratorHome;
import com.eclipsekingdom.fractalforest.gui.page.gen.WorldOverview;
import com.eclipsekingdom.fractalforest.gui.page.gen.WorldSelect;
import com.eclipsekingdom.fractalforest.gui.page.pop.*;
import com.eclipsekingdom.fractalforest.gui.page.sapling.SaplingOverview;

import static com.eclipsekingdom.fractalforest.gui.page.MenuType.*;

public enum PageType {

    POP_HOME(new Page(POP, 6, "Overview", new PopHome(), null)),
    BIOME_OVERVIEW(new Page(POP, 4, "Biomes", new BiomeOverview(), POP_HOME)),
    BIOME_SELECT(new Page(POP, 6, "Biome Selection", new BiomeSelect(), BIOME_OVERVIEW)),
    TREE_OVERVIEW(new Page(POP, 4, "Trees", new TreeOverview(), POP_HOME)),
    TREE_SELECT(new Page(POP, 6, "Tree Selection", new TreeSelect(), TREE_OVERVIEW)),
    SPAWNER(new Page(POP, 3, "Spawner", new Spawner(), POP_HOME)),
    CHANCE(new Page(POP, 3, "Spawn Chance", new Chance(), SPAWNER)),
    AMOUNT_MIN(new Page(POP, 3, "Spawn AmountMax Min", new AmountMin(), SPAWNER)),
    AMOUNT_MAX(new Page(POP, 3, "Spawn AmountMax Max", new AmountMax(), SPAWNER)),

    SAPLING_OVERVIEW(new Page(SAPLING, 6, "Selection", new SaplingOverview(), null)),

    GEN_HOME(new Page(GEN, 6, "Overview", new GeneratorHome(), null)),
    WORLD_OVERVIEW(new Page(GEN, 4, "Worlds", new WorldOverview(), GEN_HOME)),
    WORLD_SELECT(new Page(GEN, 6, "World Selection", new WorldSelect(), WORLD_OVERVIEW)),
    POP_SELECT(new Page(GEN, 6, "Populator Selection", new WorldSelect(), GEN_HOME)),

    ;

    private Page page;

    PageType(Page page) {
        this.page = page;
    }

    public Page getPage() {
        return page;
    }

}
