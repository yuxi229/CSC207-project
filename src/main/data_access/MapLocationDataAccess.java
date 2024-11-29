package data_access;

import use_case.navigation.MapLocation.MapLocation;

/**
 * Data access object for map locations.
 */
public interface MapLocationDataAccess {
    /**
     * Returns the map location of the location with the given id and on the given floor.
     * @param id the id of the location
     * @param floor the id of the floor
     * @return a MapLocation object representing the location.
     */
    MapLocation getMapLocation(String id, int floor);
}
