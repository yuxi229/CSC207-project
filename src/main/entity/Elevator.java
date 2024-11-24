package entity;

import java.util.List;

/**
 * Implementation of Elevator.
 */
public class Elevator extends Location {
    private List<Floor> floors;
    private List<Corridor> connectedCorridors;
    private double length;

    public Elevator(String id) {
        super(id);
        // TODO: Implement constructor
    }

    @Override
    public List<Floor> getFloors() {
        return List.copyOf(floors);
    }

    @Override
    public List<Location> getConnected() {
        return List.copyOf(connectedCorridors);
    }

    public double getLength() {
        return length;
    }
}
