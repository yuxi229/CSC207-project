package use_case;

import entity.Corridor;
import entity.Room;
import entity.Floor;
import entity.Stairs;

import java.util.ArrayList;
import java.util.List;

/**
 * The Navigation Data Access Use Case.
 */
public interface LocationDataAccessInterface {

    /**
     * Checks if the given roomCode exists.
     *
     * @param roomCode the roomCode to look for
     * @return true if a room with the given roomCode exists; false otherwise
     */
    public boolean existsByRoomCode(String roomCode);

    /**
     * Returns the room with the given roomCode.
     *
     * @param roomCode the roomCode to look up
     * @return the room with the given roomCode
     */
    public Room getRoom(String roomCode);

    /**
     * Returns the floor with the given id
     * @param id
     * @return
     */
    public Floor getFloor(String id);

    /**
     * Returns the stairs with the given id
     * @param id
     * @return
     */
    public Stairs getStair(String id);

    /**
     * Returns the corridor with the given id
     * @param id
     * @return
     */
    public Corridor getCorridor(String id);

    /**
     * Stores the list of rooms in the data access object
     * @param rooms: a list of rooms
     * @return
     */
    public void storeRooms(List<Room> rooms);

    /**
     * Stores the list of rooms in the data access object
     * @param rooms: a list of rooms
     * @return
     */
    public void storeStairs(List<Room> rooms);

    /**
     * Stores the list of rooms in the data access object
     * @param rooms: a list of rooms
     * @return
     */
    public void storeCorridors(List<Room> rooms);

    /**
     * Stores the list of rooms in the data access object
     * @param rooms: a list of rooms
     * @return
     */
    public void storeFloors(List<Room> rooms);

}
