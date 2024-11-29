package entity;

import java.util.List;

/**
 * Factory for creating Corridors.
 */
public class CorridorFactory {

    /**
     * Creates a new Corridor.
     * @param id the id of the new corridor
     * @param connected the list of location ids of the locations connected to the new corridor
     * @param size the size of the new corridor
     * @param floor the floor the new corridor is on
     * @param length the length of the new corridor
     * @return the new corridor.
     */
    public Corridor createCorridor(String id, List<String> connected, int size, int floor, double length) {
        return new Corridor(id, connected, size, floor, length);
    }
}
