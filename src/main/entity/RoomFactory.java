package entity;

import java.util.List;

/**
 * Factory for creating Room Objects.
 */
public class RoomFactory {
    /**
     * Creates a new Room.
     *
     * @param id the id of the new room
     * @param corridorsList the list of corridors connected to the new room.
     * @param floorIdsList a list of ids of the floors connected to the new room.
     * @return the new room
     */
    public Room createRoom(String id, List<Corridor> corridorsList, List<String> floorIdsList) {
        return new Room(id, corridorsList, floorIdsList);
    }
}
