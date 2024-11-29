package entity;

import java.util.List;

/**
 * Factory for creating Room Objects.
 */
public class RoomFactory {
    /**
     * Creates a new Room.
     * @param id the id of the new room
     * @param connected the list of location ids of the locations connected to the new room
     * @param floor the floor the new room is on
     * @param size the size of the new room
     * @param isRestricted whether the room is restricted
     * @return the new room
     */
    public Room createRoom(String id, List<String> connected, int floor, int size, boolean isRestricted) {
        return new Room(id, connected, floor, size, isRestricted);
    }
}
