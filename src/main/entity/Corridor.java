package entity;

import java.util.List;

public class Corridor {
    private String id;
    private List<String> connectedRooms;
    private List<String> connectedStairs;
    private double length;

    public Corridor(String id, List<String> connectedRooms, List<String> connectedStairs, double length) {
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

    public List<String> getConnectedRooms() {
        return connectedRooms;
    }

    public void setConnectedRooms(List<String> connectedRooms) {
        this.connectedRooms = connectedRooms;
    }

    public List<String> getConnectedStairs() {
        return connectedStairs;
    }

    public void setConnectedStairs(List<String> connectedStairs) {
        this.connectedStairs = connectedStairs;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

}
