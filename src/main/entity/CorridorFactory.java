package entity;

import java.util.ArrayList;

/**
 * Factory for creating Corridors.
 */
public class CorridorFactory {

    /**
     * Creates a new User.
     * @param id the id of the new corridor
     * @param roomList the list of rooms linked to the new corridor
     * @param stairsList the list of stairs linked to the new corridor
     * @param floor the list of floors linked to the new corridor
     * @param length the length of the new corridor
     * @return the new corridor.
     */
    public Corridor createCorridor(String id, ArrayList<Room> roomList, ArrayList<Stairs> stairsList,
                                   Floor floor, double length) {
        return new Corridor(id, roomList, stairsList, floor, length);
    }
}
