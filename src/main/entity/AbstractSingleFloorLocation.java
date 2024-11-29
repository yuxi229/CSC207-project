package entity;

import java.util.List;

/**
 * Abstract class for locations that is on a single floor (e.g., Rooms, Corridors).
 */
public abstract class AbstractSingleFloorLocation extends AbstractLocation {
    private final int floor;

    public AbstractSingleFloorLocation(String id, List<String> connectedLocations, int size, int floor) {
        super(id, connectedLocations, size);
        this.floor = floor;
    }

    public int getFloor() {
        return floor;
    }
}
