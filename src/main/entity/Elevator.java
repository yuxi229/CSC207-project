package entity;

import java.util.List;

/**
 * Implementation of Elevator.
 */
public class Elevator extends AbstractLocation {
    private final List<Corridor> connectedCorridors;
    private final List<String> connectedFloors;
    private final double length;

    public Elevator(String id, List<Corridor> connectedCorridors,
                    List<String> connectedFloors, double length) {
        super(id);
        this.connectedCorridors = connectedCorridors;
        this.connectedFloors = connectedFloors;
        this.length = length;
    }

    @Override
    public List<String> getFloors() {
        return List.copyOf(connectedFloors);
    }

    @Override
    public List<AbstractLocation> getConnectedLocations() {
        return List.copyOf(connectedCorridors);
    }

    public double getLength() {
        return length;
    }
}
