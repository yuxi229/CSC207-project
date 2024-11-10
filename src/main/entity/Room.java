package entity;

import java.util.List;

public class Room {
    private String number;
    private List<String> connectedRooms;
    private List<String> connectedCorridors;
    private float length;

    public Room(String number, List<String> connectedRooms, List<String> connectedCorridors, float length) {
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
    public List<String> getConnectedRooms() {
        return connectedRooms;
    }
    public void setConnectedRooms(List<String> connectedRooms) {
        this.connectedRooms = connectedRooms;
    }
    public List<String> getConnectedCorridors() {
        return connectedCorridors;
    }
    public void setConnectedCorridors(List<String> connectedCorridors) {
        this.connectedCorridors = connectedCorridors;
    }
    public float getLength() {
        return length;
    }
    public void setLength(float length) {
        this.length = length;
    }
}
