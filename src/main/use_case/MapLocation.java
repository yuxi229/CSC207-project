package use_case;

/**
 * Represents a location on a map.
 */
public interface MapLocation {

    /**
     * Returns the ID of the location.
     * @return the ID of the location
     */
    String getLocationID();

    /**
     * Returns the x-coordinate of the location.
     * @return the x-coordinate of the location
     */
    double getX();

    /**
     * Returns the y-coordinate of the location.
     * @return the y-coordinate of the location
     */
    double getY();

    /**
     * Returns the floor ID of the location.
     * @return the floor ID of the location
     */
    String getFloorID();

    /**
     * Returns true iff the object is a MapLocation and represents the same location as this MapLocation.
     * @param o the object to compare
     * @return true iff the object is a MapLocation and represents the same location as this MapLocation
     */
    @Override
    boolean equals(Object o);
}