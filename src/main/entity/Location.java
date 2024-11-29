package entity;

import java.util.List;

/**
 * A representation of a location.
 */
public interface Location {
    /**
     * Gets the id of the location.
     * @return the id of the location.
     */
    String getId();

    /**
     * Gets a list of the ids of the locations connected to this location.
     * @return a list of the ids
     */
    List<String> getConnectedLocations();

    /**
     * Gets the size of the location.
     * @return the size of the location.
     */
    int getSize();

    /**
     * Gets a list of floors the location is on.
     * @return a list of floors
     */
    List<Integer> getFloors();

    /**
     * Returns a unique integer (hash code) representing the location.
     * @return the hash code
     */
    @Override
    int hashCode();

    /**
     * Compares this location to another object.
     * @param obj the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    boolean equals(Object obj);
}
