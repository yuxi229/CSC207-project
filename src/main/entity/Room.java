package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A Room implementation that inherits the Location class.
 */
public class Room extends Location {
    private String id;
    private ArrayList<Corridor> corridorsList = new ArrayList<>();
    private ArrayList<Floor> floorsList = new ArrayList<>();

    public Room(String id, ArrayList<Corridor> corridorsList, ArrayList<Floor> floorsList) {
        super(id);
        this.corridorsList = corridorsList;
        this.floorsList = floorsList;
    }

    public String getRoomCode() {
        return id;
    }

    public List<Corridor> getConnectedCorridors() {
        return List.copyOf(corridorsList);
    }

    public List<Floor> getFloors() {
        return List.copyOf(floorsList);
    }

    @Override
    public String getId(String id) {
        return id;
    }

    @Override
    public List<Location> getConnectedLocations() {
        final ArrayList<Location> connected = new ArrayList<>();
        connected.addAll(corridorsList);
        return connected;
    }
}
