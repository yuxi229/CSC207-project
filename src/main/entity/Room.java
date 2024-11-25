package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A Room implementation that inherits the Location class
 */

public class Room extends Location {
    private String roomCode;
    private ArrayList<Corridor> connectedCorridors = new ArrayList<>();
    private ArrayList<Floor> floors = new ArrayList<>();

    public Room(String id, String roomCode) {
        super(id);
        this.roomCode = roomCode;
        // TODO: Implement constructor
    }

    public String getRoomCode() {
        return roomCode;
    }

    public List<Corridor> getConnectedCorridors() {
        return List.copyOf(connectedCorridors);
    }

    public List<Floor> getFloors() {
        return List.copyOf(floors);
    }

    @Override
    public List<Location> getConnected() {
        return List.copyOf(connectedCorridors);
    }
}