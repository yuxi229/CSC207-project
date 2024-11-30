package use_case.navigation.pathfinder;

import entity.Location;

import java.util.Set;

/**
 * Interface for location filters.
 * @param <T> the data structure storing the locations to filter
 */
public interface LocationFilter<T> {
    /**
     * Set a location type that is excluded.
     * @param excluded a Set of location classes that are excluded
     */
    void setExcluded(Set<T> excluded);

    /**
     * Check if there are anything filtered.
     * @return true iff there is anything filtered
     */
    boolean hasExcluded();

    /**
     * Check if a location is excluded.
     * @param location the location to check
     * @return true if the location is excluded, false otherwise
     */
    boolean isExcluded(Location location);
}
