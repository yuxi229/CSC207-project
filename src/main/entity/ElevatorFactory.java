package entity;

import java.util.List;

/**
 * Factory for creating Elevators.
 */
public class ElevatorFactory {

    /**
     * Creates a new Elevator.
     * @param id the id of the Elevator
     * @param corridorList a list of ids of the Corridors connected to the Elevator
     * @param floorsList a list of ids of the floors reached by this Elevator
     * @param length the length of the Elevator
     * @return the new Elevator
     */
    public Elevator createElevator(String id, List<String> corridorList, List<String> floorsList,
                                           double length) {
        return new Elevator(id, corridorList, floorsList, length);
    }
}
