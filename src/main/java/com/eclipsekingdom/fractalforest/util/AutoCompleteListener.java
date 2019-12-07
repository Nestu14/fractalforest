package com.eclipsekingdom.fractalforest.util;

import com.eclipsekingdom.fractalforest.FractalForest;
import com.eclipsekingdom.fractalforest.worldgen.pop.PopCache;
import com.eclipsekingdom.fractalforest.worldgen.pop.TreePopulator;
import com.eclipsekingdom.fractalforest.trees.Species;
import com.google.common.collect.ImmutableList;
import org.bukkit.Bukkit;
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
        String buffer = e.getBuffer().toLowerCase();
        if (e.getSender() instanceof Player) {
            if (buffer.contains("/sapling ")) {
                e.setCompletions(getRefinedCompletions("/sapling", buffer, getSpeciesNames()));
            } else if (buffer.contains("/giftsapling ") && numberOfFullArgs(buffer) > 0) {
                String root = "/giftsapling " + getArg(buffer, 0);
                e.setCompletions(getRefinedCompletions(root, buffer, getSpeciesNames()));
            } else if (buffer.contains("/giftsapling ")) {
                e.setCompletions(getRefinedCompletions("/giftsapling", buffer, getPlayerNames()));
            } else if (buffer.contains("/tpop delete ")) {
                e.setCompletions(getRefinedCompletions("/tpop delete", buffer, getCustomPopNames()));
            } else if (buffer.contains("/tpop createfrom ")) {
                e.setCompletions(getRefinedCompletions("/tpop createfrom", buffer, getAllPopNames()));
            } else if (buffer.contains("/tpop edit ")) {
                e.setCompletions(getRefinedCompletions("/tpop edit", buffer, getCustomPopNames()));
            } else if (buffer.contains("/tpop rename ")) {
                e.setCompletions(getRefinedCompletions("/tpop rename", buffer, getCustomPopNames()));
            } else if (buffer.contains("/tpop ")) {
                e.setCompletions(getRefinedCompletions("/tpop", buffer, popCompletions));
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

    private int numberOfFullArgs(String buffer) {
        int lastNotCompletedPenalty = endsInSpace(buffer) ? 0 : -1;
        return buffer.split(" ").length - 1 + lastNotCompletedPenalty;
    }

    private boolean endsInSpace(String buffer) {
        return ' ' == buffer.charAt(buffer.length() - 1);
    }

    private String getArg(String buffer, int arg) {
        return buffer.split(" ")[arg + 1];
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

    private List<String> getPlayerNames() {
        List<String> playerNames = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            playerNames.add(player.getName());
        }
        return playerNames;
    }

}
