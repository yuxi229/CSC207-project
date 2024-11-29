package entity;

import java.util.List;

/**
 * Factory for creating Elevators.
 */
public class ElevatorFactory {
    /**
     * Creates a new Elevator.
     * @param id the id of the elevator
     * @param connected the list of connected locations
     * @param floors the list of floors the elevator is on
     * @param size the size of the elevator
     */
    public Elevator createElevator(String id, List<String> connected, List<Integer> floors, int size) {
        return new Elevator(id, connected, floors, size);
    }
}
