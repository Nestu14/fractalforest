package com.eclipsekingdom.fractalforest.phylo.encyclopedia;

public class Entry {

    private int specimensObserved;
    private double averageHeight;
    private double averageSpread;
    private double averageVolume;

    public Entry(int specimensObserved, double averageHeight, double averageSpread, double averageVolume) {
        this.specimensObserved = specimensObserved;
        this.averageHeight = averageHeight;
        this.averageSpread = averageSpread;
        this.averageVolume = averageVolume;
    }

    public void record(Specimen specimen) {
        specimensObserved++;
        int oldRecordsWeight = specimensObserved - 1;
        averageHeight = ((averageHeight * oldRecordsWeight) + specimen.getHeight()) / specimensObserved;
        averageSpread = ((averageSpread * oldRecordsWeight) + specimen.getSpread()) / specimensObserved;
        averageVolume = ((averageVolume * oldRecordsWeight) + specimen.getVolume()) / specimensObserved;
    }

    public int getSpecimensObserved() {
        return specimensObserved;
    }

    public double getAverageHeight() {
        return averageHeight;
    }

    public double getAverageSpread() {
        return averageSpread;
    }

    public double getAverageVolume() {
        return averageVolume;
    }
}
