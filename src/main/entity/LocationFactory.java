package entity;

import java.util.List;

/**
 * A factory class for creating locations.
 */
public interface LocationFactory {
    /**
     * Creates a new Corridor.
     * @param id the id of the new corridor
     * @param connected the list of location ids of the locations connected to the new corridor
     * @param size the size of the new corridor
     * @param floor the floor the new corridor is on
     * @param length the length of the new corridor
     * @return the new corridor.
     */
    static Corridor create(String id, List<String> connected, int floor, int size, double length) {
        return new Corridor(id, connected, size, floor, length);
    }

    /**
     * Creates a new Elevator.
     * @param id the id of the elevator
     * @param connected the list of connected locations
     * @param floors the list of floors the elevator is on
     * @param size the size of the elevator
     * @return the new elevator
     */
    static Elevator create(String id, List<String> connected, List<Integer> floors, int size) {
        return new Elevator(id, connected, floors, size);
    }

    /**
     * Creates a new Room.
     * @param id the id of the new room
     * @param connected the list of location ids of the locations connected to the new room
     * @param floor the floor the new room is on
     * @param size the size of the new room
     * @param isRestricted whether the room is restricted
     * @return the new room
     */
    static Room create(String id, List<String> connected, int floor, int size, boolean isRestricted) {
        return new Room(id, connected, floor, size, isRestricted);
    }

    /**
     * Creates a new Stairs object.
     * @param id           the unique identifier of the stairs
     * @param lowerFloor   the floor number of the lower end of the stairs
     * @param upperFloor   the floor number of the upper end of the stairs
     * @param lowerCorridor the unique identifier of the corridor at the lower end of the stairs
     * @param upperCorridor the unique identifier of the corridor at the upper end of the stairs
     * @param size         the size of the stairs
     * @return a new Stairs object
     */
    static Stairs create(String id, int lowerFloor, int upperFloor, String lowerCorridor, String upperCorridor,
                         int size) {
        final List<String> connected = List.of(lowerCorridor, upperCorridor);
        final List<Integer> floors = List.of(lowerFloor, upperFloor);
        return new Stairs(id, size, connected, floors);
    }

    /**
     * Creates a new Washroom object.
     * @param id the unique identifier of the washroom
     * @param connected the list of connected locations
     * @param size the size of the washroom
     * @param floor the floor the washroom is on
     * @param gender the gender identifier of the washroom
     * @return a new Washroom object
     */
    static Washroom create(String id, List<String> connected, int floor, int size, String gender) {
        return new Washroom(id, connected, size, floor, gender);
    }
}
