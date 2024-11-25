package entity;

import java.util.List;

/**
 * Implementation of Elevator.
 */
public class Elevator extends Location {

    // List of floor numbers the elevator can access
    private List<Integer> floorsConnected;
    // Represents the number of floors spanned by this elevator
    private int length;

    // Constructor

    public Elevator(String id, List<Integer> floorsConnected, int length) {
        super(id);
        this.floorsConnected = floorsConnected;
        this.length = length;
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
