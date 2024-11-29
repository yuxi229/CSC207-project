package use_case.navigation;

import java.util.ArrayList;
import java.util.List;

import use_case.navigation.maplocation.MapLocation;

/**
 * Output Data for the Navigation Use Case.
 */
public class NavigationOutputData {
    private final List<MapLocation> pathLocations;

    /**
     * Constructor for the NavigationOutputData.
     * @param pathLocations A list of locations in the path
     */
    public NavigationOutputData(List<MapLocation> pathLocations) {
        this.pathLocations = pathLocations;
    }

    /**
     * Return a list of the ids of the locations in the path.
     * @return a list ids
     */
    public List<String> getPathIds() {
        final List<String> pathIds = new ArrayList<>();
        for (MapLocation location : pathLocations) {
            pathIds.add(location.getLocationID());
        }
        return pathIds;
    }

    /**
     * Return the path as a list of MapLocation objects.
     * @return a list of MapLocations
     */
    public List<MapLocation> getLocations() {
        return List.copyOf(pathLocations);
    }
}
