package data_access;

import use_case.navigation.maplocation.MapLocation;

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
     * Create a new MapLocationDataAccessInterface object.
     * @return the created MapLocationDataAccessInterface object
     */
    MapLocationDataAccess createMapLocationDao();
}
