package entity;

import java.util.List;

/**
 * A representation of a location that spans multiple floors.
 */
public interface MultiFloorLocation extends Location {
    /**
     * Gets the floor number of the location.
     * @return a list of the floor numbers
     */
    List<Integer> getFloors();
}
