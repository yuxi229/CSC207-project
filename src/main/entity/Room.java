package entity;

import java.util.List;

/**
 * A Room implementation of the Location interface.
 */
public class Room implements Location {

    private final String roomCode;

    public Room(String code) {
        this.roomCode = code;
    }

    @Override
    public String getRoomCode() {
        return roomCode;
    }
}

//    private String roomCode;
//    private List<Room> connectedRooms;
//    private List<Corridor> connectedCorridors;
//    private double length;
//
//    public Room(String roomCode, List<Room> connectedRooms, List<Corridor> connectedCorridors, double length) {
//        this.roomCode = roomCode;
//        this.connectedRooms = connectedRooms;
//        this.connectedCorridors = connectedCorridors;
//        this.length = length;
//    }
//    public String getRoomCode() {
//        return roomCode;
//    }
//    public void setRoomCode(String roomCode) {
//        this.roomCode = roomCode;
//    }
//    public List<Room> getConnectedRooms() {
//        return connectedRooms;
//    }
//    public void setConnectedRooms(List<Room> connectedRooms) {
//        this.connectedRooms = connectedRooms;
//    }
//    public List<Corridors> getConnectedCorridors() {
//        return connectedCorridors;
//    }
//    public void setConnectedCorridors(List<Corridors> connectedCorridors) {
//        this.connectedCorridors = connectedCorridors;
//    }
//    public double getLength() {
//        return length;
//    }
//    public void setLength(double length) {
//        this.length = length;
//    }
