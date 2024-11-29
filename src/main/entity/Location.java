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
     * Gets the size of the location.
     * @return the size of the location.
     */
    List<String> getConnectedLocations();
}
