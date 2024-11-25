package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A floor implementation that inherits Location.
 */

public class Floor {
    private String floorID;
    private ArrayList<Room> roomsList = new ArrayList<>();
    private ArrayList<Location> locationsList = new ArrayList<>();
    private ArrayList<Stairs> stairsList = new ArrayList<>();
    private ArrayList<Corridor> corridorsList = new ArrayList<>();

    Floor(String id, ArrayList<Room> roomsList, ArrayList<Stairs> stairsList,
          ArrayList<Corridor> corridorsList) {
        super(id);
        this.roomsList = roomsList;
        this.stairsList = stairsList;
        this.corridorsList = corridorsList;
    }

    public String getFloorId() {
        return floorID;
    }

    /**
     * Get a list of all rooms on the floor.
     */
    public List<Room> getRoomsList() {
        return roomsList;
    }

    /**
     * Get a list of all locations on the floor.
     */
    public List<Location> getLocationsList() {
        return locationsList;
    }
}