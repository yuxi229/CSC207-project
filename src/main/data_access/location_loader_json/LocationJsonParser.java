package data_access.location_loader_json;

import java.util.List;

import entity.AbstractLocation;
import use_case.MapLocation;

/**
 * An interface for helper classes parsing location json strings.
 * @param <T> the type of object to be parsed.
 */
public interface LocationJsonParser<T extends AbstractLocation> {

    /**
     * Returns the object parsed from the json string.
     * @return the object parsed from the json string.
     */
    T buildEntity();

    /**
     * Returns a list of map locations.
     * @return a list of map locations.
     */
    List<MapLocation> getMapLocations();

    /**
     * Returns a list of floor ids.
     * @return a list of floor ids.
     */
    List<String> getFloorIds();
}
