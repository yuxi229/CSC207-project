package use_case.navigation.pathfinder;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import data_access.LocationDataAccess;
import data_access.MapLocationDataAccess;
import entity.Location;
import use_case.navigation.maplocation.MapLocation;

/**
 * Pathfinder class that allow for filtering which locations to visit by class name by adjusting the edge weights of
 * the filtered locations to be positive infinity.
 */
public class FilteredJgraphtPathfinder extends JgraphtPathFinder implements ClassNameFilterStrategy {
    public static final double DEFAULT_WEIGHT = 1.0;
    private ClassNameFilter filter;
    private final LocationDataAccess locationDataAccess;

    public FilteredJgraphtPathfinder(LocationDataAccess locationDataAccess, MapLocationDataAccess mapLocationDataAccess,
                                     ClassNameFilter filter) {
        this.locationDataAccess = locationDataAccess;
        this.filter = filter;
        loadData(locationDataAccess, mapLocationDataAccess);
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
        updateWeights();
    }

    private void updateWeights() {
        final SimpleWeightedGraph<MapLocation, DefaultWeightedEdge> map = getMap();
        for (DefaultWeightedEdge edge : map.edgeSet()) {
            final MapLocation source = map.getEdgeSource(edge);
            final MapLocation target = map.getEdgeTarget(edge);
            final Location sourceLocation = locationDataAccess.getLocation(source.getLocationID());
            final Location targetLocation = locationDataAccess.getLocation(target.getLocationID());
            map.setEdgeWeight(edge, calculateWeight(sourceLocation, targetLocation));
        }
    }
}
