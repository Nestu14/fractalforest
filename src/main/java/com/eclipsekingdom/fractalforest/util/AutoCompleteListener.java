package com.eclipsekingdom.fractalforest.util;

import com.eclipsekingdom.fractalforest.FractalForest;
import com.eclipsekingdom.fractalforest.Permissions;
import com.eclipsekingdom.fractalforest.phylo.Species;
import com.eclipsekingdom.fractalforest.populator.PopCache;
import com.eclipsekingdom.fractalforest.populator.TreePopulator;
import com.google.common.collect.ImmutableList;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteListener implements Listener {

    public AutoCompleteListener(){
        FractalForest plugin = FractalForest.plugin;
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public void onComplete(TabCompleteEvent e){
        if(e.getSender() instanceof Player){
            Player player = (Player) e.getSender();
            if(e.getBuffer().contains("/sapling ") && Permissions.canSummonSapling(player)){
                e.setCompletions(getRefinedCompletions("/sapling", e.getBuffer(), saplingList));
            } else if (e.getBuffer().contains("/tpop delete ")) {
                e.setCompletions(getRefinedCompletions("/tpop delete", e.getBuffer(), getPopNames()));
            } else if (e.getBuffer().contains("/tpop edit ")) {
                e.setCompletions(getRefinedCompletions("/tpop edit", e.getBuffer(), getPopNames()));
            } else if (e.getBuffer().contains("/tpop rename ")) {
                e.setCompletions(getRefinedCompletions("/tpop rename", e.getBuffer(), getPopNames()));
            } else if (e.getBuffer().contains("/tpop ")) {
                e.setCompletions(getRefinedCompletions("/tpop", e.getBuffer(), popCompletions));
            }
        }
    }

    private List<String> getRefinedCompletions(String root, String buffer, List<String> completions){
        if(buffer.equalsIgnoreCase(root + " ")){
            return completions;
        }else{
            List<String> refinedCompletions = new ArrayList<>();
            String bufferFromRoot = buffer.split(root + " ")[1];
            for(String completion : completions){
                if(bufferFromRoot.length() < completion.length()){
                    if(completion.substring(0,bufferFromRoot.length()).equalsIgnoreCase(bufferFromRoot)){
                        refinedCompletions.add(completion);
                    }
                }
            }
            return refinedCompletions;
        }
    }

    private static List<String> saplingList = buildSaplingList();
    private static List<String> buildSaplingList(){
        List<String> completions = new ArrayList<>();
        completions.add("list");
        for(Species species: Species.values()){
            completions.add(species.toString());
        }
        return completions;
    }


    private static List<String> popCompletions = new ImmutableList.Builder<String>()
            .add("create")
            .add("edit")
            .add("list")
            .add("delete")
            .add("rename")
            .add("help")
            .build();

    private List<String> getPopNames() {
        List<String> popNames = new ArrayList<>();
        for (TreePopulator pop : PopCache.getPopulators()) {
            popNames.add(pop.getName());
        }
        return popNames;
    }

}
