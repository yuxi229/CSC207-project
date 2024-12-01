package use_case.navigation.pathfinder;

import data_access.LocationDataAccess;
import data_access.MapLocationDataAccess;
import entity.Location;

/**
 * Pathfinder class that allow for filtering which locations to visit by class name by adjusting the edge weights of
 * the filtered locations to be positive infinity.
 */
public class FilteredJgraphtPathfinder extends AbstractJgraphtPathFinder implements ClassNameFilterStrategy {
    public static final double DEFAULT_WEIGHT = 1.0;
    private ClassNameFilter filter;

    public FilteredJgraphtPathfinder(ClassNameFilter filter) {
        setFilter(filter);
    }

    public FilteredJgraphtPathfinder(LocationDataAccess locationDataAccess, MapLocationDataAccess mapLocationDataAccess,
                                     ClassNameFilter filter) {
        super(locationDataAccess, mapLocationDataAccess);
        setFilter(filter);
    }

    @Override
    protected Double calculateWeight(Location location1, Location location2) {
        double weight = DEFAULT_WEIGHT;
        if (filter.hasExcluded()) {
            if (filter.isExcluded(location1) || filter.isExcluded(location2)) {
                weight = Double.POSITIVE_INFINITY;
            }
        }
        return weight;
    }

    @Override
    public void setFilter(ClassNameFilter filter) {
        this.filter = filter;
    }
}
