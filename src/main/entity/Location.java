package entity;

import java.util.List;

/**
 * The representation of a location in our program.
 */
public abstract class Location {
    private final String id;

    public Location(String id) {
        this.id = id;
        // TODO: Implement constructor
    }

    public String getId() {
        return id;
    }

    public abstract List<Floor> getFloors();

    public abstract String getId(String id);

    public abstract List<Location> getConnected();
}
