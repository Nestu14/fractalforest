package com.eclipsekingdom.fractalforest.gui.page;

import com.eclipsekingdom.fractalforest.gui.page.gen.GeneratorHome;
import com.eclipsekingdom.fractalforest.gui.page.gen.PopSelection;
import com.eclipsekingdom.fractalforest.gui.page.pop.*;
import com.eclipsekingdom.fractalforest.gui.page.sapling.SaplingOverview;

import static com.eclipsekingdom.fractalforest.gui.page.MenuType.*;
import static com.eclipsekingdom.fractalforest.sys.language.Message.*;

public enum PageType {

    POP_HOME(new Page(POP, 6, LABEL_OVERVIEW.toString(), new PopHome(), null)),
    BIOME_OVERVIEW(new Page(POP, 4, LABEL_BIOMES.toString(), new BiomeOverview(), POP_HOME)),
    BIOME_SELECT(new Page(POP, 6, LABEL_BIOME_SELECTION.toString(), new BiomeSelect(), BIOME_OVERVIEW)),
    TREE_OVERVIEW(new Page(POP, 4, LABEL_TREES.toString(), new TreeOverview(), POP_HOME)),
    TREE_SELECT(new Page(POP, 6, LABEL_TREE_SELECTION.toString(), new TreeSelect(), TREE_OVERVIEW)),
    SPAWNER(new Page(POP, 3, LABEL_SPAWNER.toString(), new Spawner(), POP_HOME)),
    CHANCE(new Page(POP, 3, LABEL_SPAWN_CHANCE.toString(), new Chance(), SPAWNER)),
    AMOUNT_MIN(new Page(POP, 3, LABEL_SPAWN_MIN.toString(), new AmountMin(), SPAWNER)),
    AMOUNT_MAX(new Page(POP, 3, LABEL_SPAWN_MAX.toString(), new AmountMax(), SPAWNER)),
    OVERFLOW(new Page(POP, 3, LABEL_OVERFLOW_RAD.toString(), new Overflow(), SPAWNER)),

    SAPLING_OVERVIEW(new Page(SAPLING, 6, LABEL_SELECTION.toString(), new SaplingOverview(), null)),

    GEN_HOME(new Page(GEN, 6, LABEL_OVERVIEW.toString(), new GeneratorHome(), null)),
    POP_SELECT(new Page(GEN, 6, LABEL_POP_SELECTION.toString(), new PopSelection(), GEN_HOME)),
    ;

    private Page page;

    PageType(Page page) {
        this.page = page;
    }

    public Page getPage() {
        return page;
    }

}
