package entity;

import java.util.List;

/**
 * Factory for creating Floors.
 */
public class FloorFactory {
    /**
     * Create a new Floor.
     * @param id The ID of the floor.
     * @param roomsList The list of rooms on the floor.
     * @param stairsList The list of stairs on the floor.
     * @param corridorsList The list of corridors on the floor.
     * @return The new Floor.
     */
    public Floor createFloor(String id, List<Room> roomsList, List<Stairs> stairsList,
                             List<Corridor> corridorsList) {
        return new Floor(id, roomsList, stairsList, corridorsList);
    }
}
