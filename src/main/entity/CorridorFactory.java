package entity;

import java.util.ArrayList;

/**
 * Factory for creating Corridors.
 */
public class CorridorFactory {

    /**
     * Creates a new Corridor.
     * @param id the id of the new corridor
     * @param roomList the list of rooms linked to the new corridor
     * @param stairsList the list of stairs linked to the new corridor
     * @param floorId the id of the floor the new corridor is on
     * @param length the length of the new corridor
     * @return the new corridor.
     */
    public Corridor createCorridor(String id, ArrayList<Room> roomList, ArrayList<Stairs> stairsList,
                                   String floorId, double length) {
        return new Corridor(id, roomList, stairsList, floorId, length);
    }
}
