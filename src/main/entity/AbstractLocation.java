package entity;

import java.util.List;

/**
 * The representation of a location in our program.
 */
public abstract class AbstractLocation {
    private final String id;

    public AbstractLocation(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    /**
     * Returns which floors this location is on.
     * @return Returns a list of floor ids of floors this location is on.
     */
    public abstract List<String> getFloors();

    /**
     * Returns a list of locations connected to this location.
     * @return a list of locations connected to this location.
     */
    public abstract List<AbstractLocation> getConnectedLocations();
}
