package use_case.navigation;

import entity.Corridor;
import entity.Room;

import java.util.List;

/**
 * The Navigation Data Access Use Case.
 */
public interface NavigationDataAccessInterface {

    /**
     * Checks if the given roomCode exists.
     * @param roomCode the roomCode to look for
     * @return true if a room with the given roomCode exists; false otherwise
     */
    public boolean roomExists(String roomCode);

    /**
     * Adds the given room to the data access object
     * @param rooms the list of rooms to add
     */
    public void addRooms(List<Room> rooms);

    /**
     * Adds the given corridor to the data access object
     * @param corridors the list of corridors to add
     */
    public void addCorridors(List<Corridor> corridors);

    /**
     * Returns the room with the given roomCode.
     * @param roomCode the roomCode to look for
     * @return the room with the given roomCode
     */
    public Room getRoom(String roomCode);

    /**
     * Return a list of all rooms.
     */
    public List<Room> getRooms();

    /**
     * Return a list of all corridors.
     */
    public List<Corridor> getCorridors();
}
