package entity;

import java.util.ArrayList;

/**
 * Factory for creating Elevators.
 */
public class ElevatorFactory {

    /**
     * Creates a new User.
     * @param id the id of the new corridor
     * @param roomList the list of rooms linked to the new corridor
     * @param stairsList the list of stairs linked to the new corridor
     * @param floors the list of floors linked to the new corridor
     * @param length the length of the new corridor
     * @return the new corridor.
     */
    public Elevator createElevator(String id, ArrayList<Room> roomList, ArrayList<Stairs> stairsList,
                                   ArrayList<Floor> floors, double length) {
        return new Elevator(id, roomList, stairsList, floors, length);
    }
}
