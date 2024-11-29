package use_case.navigation.MapLocation;

/**
 * Factory for creating MapLocation objects.
 */
public interface MapLocationFactory {

    /**
     * Create a new MapLocation object.
     * @param id the id of the location
     * @param mapX the x coordinate of the location
     * @param mapY the y coordinate of the location
     * @param floor the id of the floor
     * @return the created MapLocation object
     */
    MapLocation createMapLocation(String id, double mapX, double mapY, int floor);
}
