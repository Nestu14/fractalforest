package com.eclipsekingdom.fractalforest.gui.page;

import com.eclipsekingdom.fractalforest.gui.page.gen.GeneratorHome;
import com.eclipsekingdom.fractalforest.gui.page.gen.PopSelection;
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
    AMOUNT_MIN(new Page(POP, 3, "Spawn Amount Min", new AmountMin(), SPAWNER)),
    AMOUNT_MAX(new Page(POP, 3, "Spawn Amount Max", new AmountMax(), SPAWNER)),
    OVERFLOW(new Page(POP, 3, "Overflow Radius", new Overflow(), SPAWNER)),

    SAPLING_OVERVIEW(new Page(SAPLING, 6, "Selection", new SaplingOverview(), null)),

    GEN_HOME(new Page(GEN, 6, "Overview", new GeneratorHome(), null)),
    POP_SELECT(new Page(GEN, 6, "Populator Selection", new PopSelection(), GEN_HOME)),
    ;

    private Page page;

    PageType(Page page) {
        this.page = page;
    }

    public Page getPage() {
        return page;
    }

}
