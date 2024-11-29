package entity;

import java.util.List;

/**
 * Abstract class for locations that span multiple floors (e.g., Stairs, Elevators).
 */
public abstract class AbstractMultiFloorLocation extends AbstractLocation implements MultiFloorLocation {
    private final List<Integer> floors;

    AbstractMultiFloorLocation(String id, List<String> connectedLocations, int size, List<Integer> floors) {
        super(id, connectedLocations, size);
        this.floors = floors;
    }

    public List<Integer> getFloors() {
        return List.copyOf(floors);
    }
}
