package data_access;

import java.util.Set;

import entity.Location;

/** Interface for creating LocationDataAccessInterface objects. */
public interface LocationDaoBuilder {

    /**
     * Add a location to the data.
     * @param location the location to add
     */
    void addLocation(Location location);

    /**
     * Add a set of locations to the data.
     * @param locations the set of locations to add
     */
    void addLocations(Set<Location> locations);

    /**
     * Create a new LocationDataAccessInterface object.
     * @return the created LocationDataAccessInterface object
     */
    LocationDataAccess createDataAccessObject();
}
