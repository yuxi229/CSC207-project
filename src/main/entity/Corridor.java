package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A Corridor implementation that inherits the Location class.
 */
public class Corridor extends Location {
    private ArrayList<Room> connectedRooms = new ArrayList<>();
    private ArrayList<Stairs> connectedStairs = new ArrayList<>();
    private ArrayList<Corridor> connectedCorridors = new ArrayList<>();
    private Floor floor;
    private double length;

    public Corridor(String id) {
        super(id);
        // TODO: Implement constructor
    }

    public double getLength() {
        return length;
    }

    public List<Room> getConnectedRooms() {
        return List.copyOf(connectedRooms);
    }

    public List<Stairs> getConnectedStairs() {
        return List.copyOf(connectedStairs);
    }

    public List<Corridor> getConnectedCorridors() {
        return List.copyOf(connectedCorridors);
    }

    @Override
    public List<Floor> getFloors() {
        return List.of(floor);
    }

    @Override
    public List<Location> getConnected() {
        ArrayList<Location> connected = new ArrayList<>();
        connected.addAll(connectedRooms);
        connected.addAll(connectedStairs);
        connected.addAll(connectedCorridors);
        return connected;
    }

}