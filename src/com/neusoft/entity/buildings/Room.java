package com.neusoft.entity.buildings;

import java.util.List;

public class Room {
    private int id;
    private int floor;
    private double square;
    List<Equipment> equipments;
    List<Bed> beds;

    public Room() {
    }

    public Room(int id, int floor, double square, List<Equipment> equipments, List<Bed> beds) {
        this.id = id;
        this.floor = floor;
        this.square = square;
        this.equipments = equipments;
        this.beds = beds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public List<Bed> getBeds() {
        return beds;
    }

    public void setBeds(List<Bed> beds) {
        this.beds = beds;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", floor=" + floor +
                ", square=" + square +
                ", equipments=" + equipments +
                ", beds=" + beds +
                '}';
    }
}
