package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A Corridor implementation that inherits the Location class.
 */
public class Corridor extends Location {
    private ArrayList<Room> roomList = new ArrayList<>();
    private ArrayList<Stairs> stairsList = new ArrayList<>();
    private ArrayList<Corridor> connectedCorridors = new ArrayList<>();
    private Floor floor;
    private double length;

    Corridor(String id, ArrayList<Room> roomList, ArrayList<Stairs> stairsList,
             Floor floor, double length) {
        super(id);
        this.roomList = roomList;
        this.stairsList = stairsList;
        this.floor = floor;
        this.length = length;

    }

    @Override
    public String getId(String id) {
        return id;
    }

    @Override
    public List<Floor> getFloors() {
        return List.of(floor);
    }

    public double getLength() {
        return length;
    }

    public List<Room> getConnectedRooms() {
        return List.copyOf(roomList);
    }

    public List<Stairs> getConnectedStairs() {
        return List.copyOf(stairsList);
    }

    public List<Corridor> getConnectedCorridors() {
        return List.copyOf(connectedCorridors);
    }

    @Override
    public List<Location> getConnected() {
        final ArrayList<Location> connected = new ArrayList<>();
        connected.addAll(roomList);
        connected.addAll(stairsList);
        connected.addAll(connectedCorridors);
        return connected;
    }
}




