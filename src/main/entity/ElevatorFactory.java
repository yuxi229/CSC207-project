package entity;

import java.util.List;

/**
 * Factory for creating Elevators.
 */
public class ElevatorFactory {

    /**
     * Creates a new Elevator.
     * @param id the id of the Elevator
     * @param corridorList the list of Corridors connected to the Elevator
     * @param length the length of the Elevator
     * @return the new Elevator
     */
    public AbstractLocation createElevator(String id, List<Corridor> corridorList, double length) {
        return new Elevator(id, corridorList, length);
    }
}
