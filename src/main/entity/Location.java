package entity;

import java.util.List;

/**
 * The representation of a location in our program.
 */
public abstract class Location {
    private String id;

    public Location(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract List<Floor> getFloors();

    public abstract List<Location> getConnected();
}
