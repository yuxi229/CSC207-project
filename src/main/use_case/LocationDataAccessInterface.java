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
    AbstractLocation getLocation(String id);

    /**
     * Returns the room with the given roomCode.
     *
     * @param roomCode the roomCode to look upw
     * @return the room with the given roomCode
     */
    Room getRoom(String roomCode);

    /**
     * Returns the floor with the given id
     * @param id the id of the floor
     * @return the floor with the given id
     */
    Floor getFloor(String id);

    /**
     * Returns the stairs with the given id
     * @param id the id of the stairs
     * @return the stairs with the given id
     */
    Stairs getStair(String id);

    /**
     * Returns the corridor with the given id
     * @param id  the id of the corridor
     * @return the corridor with the given id
     */
    Corridor getCorridor(String id);

//    /**
//     * Store a list of Locations into the data access object
//     * @param locations: a list of locations to store
//     */
//    void loadLocations(List<Location> locations);


    /**
     * Returns the list of floors in the data access object
     * @return a list of floors
     */
    List<Floor> getFloors();

    /**
     * Returns the list of locations in the data access object
     * @return a list of locations
     */
    List<AbstractLocation> getLocations();

    /**
     * Returns the map location of the location with the given id and on the given floor
     * @return a MapLocation object representing the location
     */
    MapLocation getMapLocation(String id, String floorID);
}
