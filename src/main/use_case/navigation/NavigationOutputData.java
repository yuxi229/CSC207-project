package use_case.navigation;

import java.util.ArrayList;
import java.util.List;

/**
 * Output Data for the Navigation Use Case.
 */
public class NavigationOutputData {
    private final List<String> pathIds;

    /**
     * Constructor for the NavigationOutputData.
     */
    public NavigationOutputData(List<String> pathLocations) {
        this.pathIds = pathLocations;
    }

    /**
     * Return the path as a list of MapLocation objects.
     */
    public List<String> getLocation(String id) {
        return List.copyOf(pathIds);
    }
}
