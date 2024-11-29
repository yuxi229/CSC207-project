package data_access;

import java.util.Set;

import entity.Location;
import use_case.navigation.MapLocation;

/**
 * Interface for creating MapLocationDataAccessInterface objects.
 */
public interface MapLocationDaoBuilder {
    /**
     * Add a map location to the data.
     * @param location the location to add
     * @param mapLocation the map location to add
     * @return true if the map location was added successfully, false otherwise
     */
    boolean addMapLocation(Location location, MapLocation mapLocation);

    /**
     * Add a set of map locations to the data.
     * @param mapLocations the set of map locations to add
     * @return true if the map locations were added successfully, false otherwise
     */
    boolean addMapLocations(Set<MapLocation> mapLocations);

    /**
     * Create a new MapLocationDataAccessInterface object.
     * @return the created MapLocationDataAccessInterface object
     */
    MapLocationDataAccess createDataAccessObject();
}
