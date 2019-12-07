package com.eclipsekingdom.fractalforest.phylo.encyclopedia;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RecordSpecimenEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final String species;
    private final Specimen specimen;

    public RecordSpecimenEvent(String species, Specimen specimen) {
        this.species = species;
        this.specimen = specimen;
    }

    public final static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public final HandlerList getHandlers() {
        return handlers;
    }

    public String getSpecies() {
        return species;
    }

    public final Specimen getSpecimen() {
        return specimen;
    }

}
