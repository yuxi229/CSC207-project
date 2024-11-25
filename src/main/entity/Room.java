package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A Room implementation that inherits the Location class
 */

class Room extends Location {
    private String roomCode;
    private ArrayList<Corridor> connectedCorridors = new ArrayList<>();
    private ArrayList<Floor> floors = new ArrayList<>();

    public Room(String id, String roomCode) {
        super(id);
        this.roomCode = roomCode;
    }

    public String getRoomCode() {
        return roomCode;
    }

    void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public ArrayList<Corridor> getConnectedCorridors() {
        return connectedCorridors;
    }

    private void addConnectedCorridor(Corridor corridor) {
        connectedCorridors.add(corridor);
    }

    public ArrayList<Floor> getFloors() {
        return floors;
    }

    private void addFloor(Floor floor) {
        floors.add(floor);
    }
}
