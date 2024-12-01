package use_case.navigation.pathfinder;

import entity.Location;

/**
 * Pathfinder class that allow for filtering which locations to visit by class name by adjusting the edge weights of
 * the filtered locations to be positive infinity.
 */
public class FilteredJgraphtPathfinder extends JgraphtPathFinder implements ClassNameFilterStrategy {
    public static final double DEFAULT_WEIGHT = 1.0;
    private ClassNameFilter filter;

    public FilteredJgraphtPathfinder(ClassNameFilter filter) {
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
