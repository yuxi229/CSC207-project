package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A Corridor implementation that inherits the Location class.
 */
public class Corridor extends AbstractLocation {
    private final List<String> connectedRooms;
    private final List<String> connectedStairs;
    private final List<String> connectedCorridors;
    private final String floorId;
    private final double length;

    Corridor(String id, List<String> roomList, List<String> stairsList, List<String> corridorsList,
             String floorId, double length) {
        super(id);
        this.connectedRooms = roomList;
        this.connectedStairs = stairsList;
        this.connectedCorridors = corridorsList;
        this.floorId = floorId;
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    public List<String> getConnectedRooms() {
        return List.copyOf(connectedRooms);
    }

    public List<String> getConnectedStairs() {
        return List.copyOf(connectedStairs);
    }

    @Override
    public List<String> getFloors() {
        return List.of(floorId);
    }

    @Override
    public List<String> getConnectedLocations() {
        final ArrayList<String> connected = new ArrayList<>();
        connected.addAll(connectedRooms);
        connected.addAll(connectedStairs);
        connected.addAll(connectedCorridors);
        return connected;
    }
}
