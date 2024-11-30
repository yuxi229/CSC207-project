package use_case.navigation.pathfinder;

import entity.Location;

/**
 * Pathfinder class that allow for filtering which locations to visit.
 */
public class EdgeWeightFilterPathfinder extends JgraphtPathFinder implements EdgeWeightFilterStrategy {
    public static final double DEFAULT_WEIGHT = 1.0;
    private ClassNameFilter filter;

    public EdgeWeightFilterPathfinder(ClassNameFilter filter) {
        setFilter(filter);
    }

    @Override
    public Double calculateWeight(Location location1, Location location2) {
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
