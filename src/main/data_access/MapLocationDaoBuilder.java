package data_access;

import java.util.Set;

import use_case.navigation.MapLocation.MapLocation;

/**
 * Interface for creating MapLocationDataAccessInterface objects.
 */
public interface MapLocationDaoBuilder {
    /**
     * Add a map location to the data.
     * @param mapLocation the map location to add
     */
    void addMapLocation(MapLocation mapLocation);

    /**
     * Add a set of map locations to the data.
     * @param mapLocations the set of map locations to add
     */
    void addMapLocations(Set<MapLocation> mapLocations);

    /**
     * Create a new MapLocationDataAccessInterface object.
     * @return the created MapLocationDataAccessInterface object
     */
    MapLocationDataAccess createMapLocationDao();
}
