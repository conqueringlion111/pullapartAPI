package com.pullapart.api.model;

import java.util.List;

public class Vehicle {

    private List<Integer> Locations;
    private int MakeID;
    private List<Integer> Models;
    private List<Integer> Years;

    public Vehicle() {

    }

    public Vehicle(List<Integer> Locations, int MakeID, List<Integer> Models, List<Integer> Years) {
        this.Locations = Locations;
        this.MakeID = MakeID;
        this.Models = Models;
        this.Years = Years;
    }

    public List<Integer> getLocations() {
        return Locations;
    }

    public void setLocations(List<Integer> locations) {
        Locations = locations;
    }

    public int getMakeID() {
        return MakeID;
    }

    public void setMakeID(int makeID) {
        MakeID = makeID;
    }

    public List<Integer> getModels() {
        return Models;
    }

    public void setModels(List<Integer> models) {
        Models = models;
    }

    public List<Integer> getYears() {
        return Years;
    }

    public void setYears(List<Integer> years) {
        Years = years;
    }

}
