package com.eclipsekingdom.fractalforest.util;

import com.eclipsekingdom.fractalforest.FractalForest;
import com.eclipsekingdom.fractalforest.gen.pop.PopCache;
import com.eclipsekingdom.fractalforest.gen.pop.TreePopulator;
import com.eclipsekingdom.fractalforest.phylo.Species;
import com.google.common.collect.ImmutableList;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteListener implements Listener {

    public AutoCompleteListener() {
        FractalForest plugin = FractalForest.plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onComplete(TabCompleteEvent e) {
        if (e.getSender() instanceof Player) {
            if (e.getBuffer().contains("/sapling ")) {
                e.setCompletions(getRefinedCompletions("/sapling", e.getBuffer(), getSpeciesNames()));
            } else if (e.getBuffer().contains("/tpop delete ")) {
                e.setCompletions(getRefinedCompletions("/tpop delete", e.getBuffer(), getCustomPopNames()));
            } else if (e.getBuffer().contains("/tpop createfrom ")) {
                e.setCompletions(getRefinedCompletions("/tpop createfrom", e.getBuffer(), getAllPopNames()));
            } else if (e.getBuffer().contains("/tpop edit ")) {
                e.setCompletions(getRefinedCompletions("/tpop edit", e.getBuffer(), getCustomPopNames()));
            } else if (e.getBuffer().contains("/tpop rename ")) {
                e.setCompletions(getRefinedCompletions("/tpop rename", e.getBuffer(), getCustomPopNames()));
            } else if (e.getBuffer().contains("/tpop ")) {
                e.setCompletions(getRefinedCompletions("/tpop", e.getBuffer(), popCompletions));
            }
        }
    }

    private List<String> getRefinedCompletions(String root, String buffer, List<String> completions) {
        if (buffer.equalsIgnoreCase(root + " ")) {
            return completions;
        } else {
            List<String> refinedCompletions = new ArrayList<>();
            String bufferFromRoot = buffer.split(root + " ")[1];
            for (String completion : completions) {
                if (bufferFromRoot.length() < completion.length()) {
                    if (completion.substring(0, bufferFromRoot.length()).equalsIgnoreCase(bufferFromRoot)) {
                        refinedCompletions.add(completion);
                    }
                }
            }
            return refinedCompletions;
        }
    }

    private static List<String> popCompletions = new ImmutableList.Builder<String>()
            .add("create")
            .add("createfrom")
            .add("edit")
            .add("list")
            .add("delete")
            .add("rename")
            .add("help")
            .build();

    private List<String> getCustomPopNames() {
        List<String> popNames = new ArrayList<>();
        for (TreePopulator pop : PopCache.getPopulators()) {
            if (!PopCache.isPreset(pop.getName())) {
                popNames.add(pop.getName());
            }
        }
        return popNames;
    }


    private List<String> getAllPopNames() {
        List<String> popNames = new ArrayList<>();
        for (TreePopulator pop : PopCache.getPopulators()) {
            popNames.add(pop.getName());
        }
        return popNames;
    }

    private List<String> getSpeciesNames() {
        List<String> speciesNames = new ArrayList<>();
        for (Species species : Species.values()) {
            speciesNames.add(species.toString());
        }
        speciesNames.add("list");
        return speciesNames;
    }

}
