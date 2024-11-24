package use_case;

import entity.*;
import use_case.navigation.MapLocation;

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
    public boolean roomExists(String roomCode);

    /**
     * Checks if the given id exists.
     *
     * @param id the id to look for
     * @return true if a location with the given id exists; false otherwise
     */
    public boolean idExists(String id);

    /**
     * Returns the location with the given id.
     *
     * @param id the id to look up
     * @return the location with the given id
     */
    public Location getLocation(String id);

    /**
     * Returns the room with the given roomCode.
     *
     * @param roomCode the roomCode to look upw
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
     * Store a list of Locations into the data access object
     * @param locations: a list of rooms
     * @return
     */
    public void loadLocations(List<Location> locations);


    /**
     * Returns the list of floors in the data access object
     * @return
     */
    public List<Floor> getFloors();

    /**
     * Returns the list of locations in the data access object
     * @return
     */
    public List<Location> getLocations();

    /**
     * Returns the map location of the location with the given id and on the given floor
     * @return
     */
    public MapLocation getMapLocation(String id, String floorID);
}
