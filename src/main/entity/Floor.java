package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A floor implementation that inherits Location.
 */

public class Floor {
    private final String floorID;
    private final List<Room> roomsList;
    private final List<Stairs> stairsList;
    private final List<Corridor> corridorsList;
    private final List<AbstractLocation> locationsList;

    Floor(String id, List<Room> roomsList, List<Stairs> stairsList, List<Corridor> corridorsList) {
        this.floorID = id;
        this.roomsList = roomsList;
        this.stairsList = stairsList;
        this.corridorsList = corridorsList;

        locationsList = new ArrayList<>();
        locationsList.addAll(roomsList);
        locationsList.addAll(stairsList);
        locationsList.addAll(corridorsList);
    }

    public String getFloorId() {
        return floorID;
    }

    /**
     * Get a list of all rooms on the floor.
     * @return a list of all rooms on the floor.
     */
    public List<Room> getRoomsList() {
        return List.copyOf(roomsList);
    }

    /**
     * Get a list of all stairs on the floor.
     * @return a list of all stairs on the floor.
     */
    public List<Stairs> getStairsList() {
        return List.copyOf(stairsList);
    }

    /**
     * Get a list of all corridors on the floor.
     * @return a list of all corridors on the floor.
     */
    public List<Corridor> getCorridorsList() {
        return List.copyOf(corridorsList);
    }

    /**
     * Get a list of all locations on the floor.
     * @return a list of all locations on the floor.
     */
    public List<AbstractLocation> getLocationsList() {
        return List.copyOf(locationsList);
    }
}
