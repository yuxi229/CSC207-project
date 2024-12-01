package data_access;

import entity.Location;

/** Interface for creating LocationDataAccessInterface objects. */
public interface LocationDaoBuilder {

    /**
     * Add a location to the data.
     * @param location the location to add
     */
    void addLocation(Location location);

    /**
     * Create a new LocationDataAccessInterface object.
     * @return the created LocationDataAccessInterface object
     */
    LocationDataAccess createDataAccessObject();
}
