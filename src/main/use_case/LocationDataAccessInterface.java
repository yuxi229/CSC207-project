package use_case;

import entity.*;
import use_case.navigation.MapLocation;

import java.util.List;

/**
 * The Navigation Data Access Use Case Interface.
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
     * Checks if the given id exists in the location data.
     *
     * @param id the id to look for
     * @return true if a location with the given id exists; false otherwise
     */
    boolean idExists(String id);

    /**
     * Returns the single-floor location with the given id.
     *
     * @param id the id to look up
     * @return the single-floor location with the given id
     */
    Location getLocation(String id);

    /**
     * Returns the multi-floor location with the given id.
     *
     * @param id the id to look up
     * @return the multi-floor location with the given id
     */
    MultiFloorLocation getMultiFloorLocation(String id);

    /**
     * Returns the room with the given roomCode.
     *
     * @param roomCode the roomCode to look up
     * @return the room with the given roomCode
     */
    Room getRoom(String roomCode);

    /**
     * Returns the corridor with the given id.
     *
     * @param id the id of the corridor
     * @return the corridor with the given id
     */
    Corridor getCorridor(String id);

    /**
     * Returns the washroom with the given id.
     *
     * @param id the id of the washroom
     * @return the washroom with the given id
     */
    Washroom getWashroom(String id);

    /**
     * Returns the valve with the given id.
     *
     * @param id the id of the valve
     * @return the valve with the given id
     */
    Valve getValve(String id);

    /**
     * Returns the stairs with the given id.
     *
     * @param id the id of the stairs
     * @return the stairs with the given id
     */
    Stair getStair(String id);

    /**
     * Returns the elevator with the given id.
     *
     * @param id the id of the elevator
     * @return the elevator with the given id
     */
    Elevator getElevator(String id);

    /**
     * Returns a list of all single-floor locations in the data access object.
     *
     * @return a list of single-floor locations
     */
    List<Location> getSingleFloorLocations();

    /**
     * Returns a list of all multi-floor locations in the data access object.
     *
     * @return a list of multi-floor locations
     */
    List<MultiFloorLocation> getMultiFloorLocations();

}
