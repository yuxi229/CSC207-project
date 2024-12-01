package use_case.navigation.pathfinder;

import java.util.Set;

import entity.Location;

/**
 * Implementation of filter that filters locations based on their class name.
 */
public class ClassNameFilter implements LocationFilter<Class<?>> {
    private Set<Class<?>> excluded;

    ClassNameFilter() {
        this.excluded = null;
    }

    @Override
    public void setExcluded(Set<Class<?>> excluded) {
        this.excluded = excluded;
    }

    /**
     * Set a type of location to be excluded from the pathfinding by proving an instance of the location.
     * @param location An instance of the type of location to be excluded.
     */
    public void excludeByExample(Location location) {
        this.excluded.add(location.getClass());
    }

    @Override
    public boolean hasExcluded() {
        return excluded != null;
    }

    @Override
    public boolean isExcluded(Location location) {
        return excluded.contains(location.getClass());
    }
}
