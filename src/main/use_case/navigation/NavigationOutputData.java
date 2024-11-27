package use_case.navigation;

import java.util.ArrayList;
import java.util.List;

/**
 * Output Data for the Navigation Use Case.
 */
public class NavigationOutputData {
    private final List<MapLocation> pathLocations;
    private final boolean useCaseFailed;

    /**
     * Constructor for the NavigationOutputData.
     */
    public NavigationOutputData(List<MapLocation> pathLocations, boolean useCaseFailed) {
        this.pathLocations = pathLocations;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Return a list of the ids of the locations in the path.
     */
    public List<String> getPathIDs() {
        List<String> pathIDs = new ArrayList<>();
        for (MapLocation location : pathLocations) {
            pathIDs.add(location.getLocationID());
        }
        return pathIDs;
    }

    /**
     * Return the path as a list of MapLocation objects.
     */
    public List<MapLocation> getLocation(String id) {
        return List.copyOf(pathLocations);
    }


    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
