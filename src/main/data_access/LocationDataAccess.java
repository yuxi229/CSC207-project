package data_access;

import java.util.List;
import java.util.Set;

import entity.Corridor;
import entity.Location;
import entity.Room;
import entity.Stairs;

/**
 * The Navigation Data Access Use Case.
 */
public interface LocationDataAccess {

    /**
     * Checks if the given roomCode exists.
     *
     * @param roomCode the roomCode to look for
     * @return true if a room with the given roomCode exists; false otherwise
     */
    boolean roomExists(String roomCode);

    /**
     * Checks if the given id exists.
     *
     * @param id the id to look for
     * @return true if a location with the given id exists; false otherwise
     */
    boolean idExists(String id);

    /**
     * Returns the location with the given id.
     *
     * @param id the id to look up
     * @return the location with the given id
     */
    Location getLocation(String id);

    /**
     * Returns the room with the given roomCode.
     *
     * @param roomCode the roomCode to look upw
     * @return the room with the given roomCode
     */
    Room getRoom(String roomCode);

    /**
     * Returns the stairs with the given id.
     * @param id the id of the stairs
     * @return the stairs with the given id
     */
    Stairs getStairs(String id);

    /**
     * Returns the corridor with the given id.
     * @param id  the id of the corridor
     * @return the corridor with the given id
     */
    Corridor getCorridor(String id);

    /**
     * Returns a list of all floor ids in the data access object.
     * @return a list of floor ids
     */
    List<Integer> getFloorIds();

    /**
     * Returns a set of all locations in the data access object.
     * @return a set of locations
     */
    Set<Location> getLocations();

    /**
     * Returns a set of all locations on the given floor.
     * @param floor the id of the floor
     * @return a set of locations on the given floor
     */
    Set<Location> getLocations(int floor);
}
