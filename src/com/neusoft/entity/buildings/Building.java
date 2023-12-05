package com.neusoft.entity.buildings;

import java.util.List;

public class Building {
    private int id;
    private String name;
    private int layer;
    private double square;
    private List<Room> rooms;

    public Building(int id, String name, int layer, double square, List<Room> rooms) {
        this.id = id;
        this.name = name;
        this.layer = layer;
        this.square = square;
        this.rooms = rooms;
    }

    public Building(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", layer=" + layer +
                ", square=" + square +
                ", rooms=" + rooms +
                '}';
    }
}
