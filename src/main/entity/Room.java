package entity;

import java.util.List;

public class Room {
    private String number;
    private List<Room> connectedRooms;
    private List<Corridor> connectedCorridors;
    private double length;

    public Room(String number, List<Room> connectedRooms, List<Corridor> connectedCorridors, double length) {
        this.number = number;
        this.connectedRooms = connectedRooms;
        this.connectedCorridors = connectedCorridors;
        this.length = length;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public List<Room> getConnectedRooms() {
        return connectedRooms;
    }
    public void setConnectedRooms(List<Room> connectedRooms) {
        this.connectedRooms = connectedRooms;
    }
    public List<Corridor> getConnectedCorridors() {
        return connectedCorridors;
    }
    public void setConnectedCorridors(List<Corridor> connectedCorridors) {
        this.connectedCorridors = connectedCorridors;
    }
    public double getLength() {
        return length;
    }
    public void setLength(float length) {
        this.length = length;
    }
}
