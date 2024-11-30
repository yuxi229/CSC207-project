package use_case.navigation.pathfinder;

/**
 * Interface for pathfinders that can be filtered by a set of class names.
 */
public interface ClassNameFilterStrategy extends PathFinder {
    /**
     * Set the filter to be used by the pathfinder.
     * @param filter The filter to be used.
     */
    void setFilter(ClassNameFilter filter);
}
