package com.eclipsekingdom.fractalforest.sapling;

import com.eclipsekingdom.fractalforest.FractalForest;
import com.eclipsekingdom.fractalforest.Permissions;
import com.eclipsekingdom.fractalforest.phylo.Species;
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
}
