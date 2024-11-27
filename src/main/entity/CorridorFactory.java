package entity;

import java.util.List;

/**
 * Factory for creating Corridors.
 */
public class CorridorFactory {

    /**
     * Creates a new Corridor.
     * @param id the id of the new corridor
     * @param roomList a list of ids of the rooms linked to the new corridor
     * @param stairsList a list of ids of the stairs linked to the new corridor
     * @param corridorsList a list ids of the corridors linked to the new corridor
     * @param floorId the id of the floor the new corridor is on
     * @param length the length of the new corridor
     * @return the new corridor.
     */
    public Corridor createCorridor(String id, List<String> roomList, List<String> stairsList,
                                   List<String> corridorsList, String floorId, double length) {
        return new Corridor(id, roomList, stairsList, corridorsList, floorId, length);
    }
}
