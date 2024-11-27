package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A Corridor implementation that inherits the Location class.
 */
public class Corridor extends AbstractLocation {
    private ArrayList<Room> roomList = new ArrayList<>();
    private ArrayList<Stairs> stairsList = new ArrayList<>();
    private ArrayList<Corridor> connectedCorridors = new ArrayList<>();
    private final String floorId;
    private final double length;

    Corridor(String id, ArrayList<Room> roomList, ArrayList<Stairs> stairsList,
             String floorId, double length) {
        super(id);
        this.roomList = roomList;
        this.stairsList = stairsList;
        this.floorId = floorId;
        this.length = length;
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

    @Override
    public List<String> getFloors() {
        return List.of(floorId);
    }

    @Override
    public List<AbstractLocation> getConnectedLocations() {
        final ArrayList<AbstractLocation> connected = new ArrayList<>();
        connected.addAll(roomList);
        connected.addAll(stairsList);
        connected.addAll(connectedCorridors);
        return connected;
    }
}
