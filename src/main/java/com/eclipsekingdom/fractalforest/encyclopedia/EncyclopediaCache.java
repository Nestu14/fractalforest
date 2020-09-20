package com.eclipsekingdom.fractalforest.encyclopedia;

import com.eclipsekingdom.fractalforest.FractalForest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class EncyclopediaCache implements Listener {

    private static Map<String, Entry> speciesToEntry = new HashMap<>();
    private static EncyclopediaFlatFile encyclopediaFlatFile = new EncyclopediaFlatFile();

    public EncyclopediaCache() {
        Plugin plugin = FractalForest.getPlugin();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        load();
    }

    private void load() {
        speciesToEntry.putAll(encyclopediaFlatFile.fetch());
    }

    public static void save() {
        encyclopediaFlatFile.store(speciesToEntry);
    }

    public static boolean hasEntry(String species) {
        return speciesToEntry.containsKey(species);
    }

    public static Entry getEntry(String species) {
        return speciesToEntry.get(species);
    }

    public static void registerSpecies(String species, Specimen specimen) {
        Entry entry = new Entry(1, specimen.getHeight(), specimen.getSpread(), specimen.getVolume());
        speciesToEntry.put(species, entry);
    }

    public static Map<String, Entry> getSpeciesToEntry() {
        return speciesToEntry;
    }

    public static boolean hasRecords() {
        return speciesToEntry.size() > 0;
    }

    @EventHandler
    public void onRecord(RecordSpecimenEvent e) {
        Specimen specimen = e.getSpecimen();
        String species = e.getSpecies();
        if (hasEntry(species)) {
            Entry entry = getEntry(species);
            entry.record(specimen);
        } else {
            registerSpecies(species, specimen);
        }
    }

}
