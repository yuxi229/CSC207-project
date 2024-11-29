package entity;

import java.util.List;

/**
 * A factory class for creating Washroom objects.
 */
public class WashroomFactory {
    /**
     * Creates a new Washroom object.
     * @param id the unique identifier of the washroom
     * @param connected the list of connected locations
     * @param size the size of the washroom
     * @param floor the floor the washroom is on
     * @param gender the gender identifier of the washroom
     * @return a new Washroom object
     */
    public Washroom createWashroom(String id, List<String> connected, int size, int floor, String gender) {
        return new Washroom(id, connected, size, floor, gender);
    }
}
