package entity;

import java.util.List;

public class Corridor implements Location {
    private String id;
    private List<Room> connectedRooms;
    private List<Stairs> connectedStairs;
    private double length;

    public Corridor(String id, List<Room> connectedRooms, List<Stairs> connectedStairs, double length) {
        this.id = id;
        this.connectedRooms = connectedRooms;
        this.connectedStairs = connectedStairs;
        this.length = length;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Room> getConnectedRooms() {
        return connectedRooms;
    }

    public void setConnectedRooms(List<Room> connectedRooms) {
        this.connectedRooms = connectedRooms;
    }

    public List<Stairs> getConnectedStairs() {
        return connectedStairs;
    }

    public void setConnectedStairs(List<Stairs> connectedStairs) {
        this.connectedStairs = connectedStairs;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
