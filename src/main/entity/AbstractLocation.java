package entity;

import java.util.List;

/**
 * The representation of a location in our program.
 */
public abstract class AbstractLocation implements Location {
    private final String id;
    private final List<String> connectedLocations;
    private final int size;

    public AbstractLocation(String id, List<String> connectedLocations, int size) {
        this.id = id;
        this.connectedLocations = connectedLocations;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    /**
     * Gets the locations connected to this location.
     * @return a list of ids of the locations connected to this location.
     */
    public List<String> getConnectedLocations() {
        return List.copyOf(connectedLocations);
    }

    public int getSize() {
        return size;
    }
}
