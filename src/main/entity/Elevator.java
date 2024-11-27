package entity;

import java.util.List;

/**
 * Implementation of Elevator.
 */
public class Elevator extends AbstractLocation {
    private final List<Corridor> connectedCorridors;
    private final double length;

    // Constructor

    public Elevator(String id, List<Corridor> connectedCorridors,
                    double length) {
        super(id);
        this.connectedCorridors = connectedCorridors;
        this.length = length;
    }

    @Override
    public List<String> getFloors() {
        return List.of();
    }

    @Override
    public List<AbstractLocation> getConnectedLocations() {
        return List.copyOf(connectedCorridors);
    }

    public double getLength() {
        return length;
    }
}
