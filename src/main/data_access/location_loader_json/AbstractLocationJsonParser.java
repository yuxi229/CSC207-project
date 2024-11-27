package data_access.location_loader_json;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import entity.AbstractLocation;
import use_case.MapLocation;

/**
 * An abstract class that reads a location json string and parses it into an object.
 * @param <T> the type of the location object to be parsed.
 */
public abstract class AbstractLocationJsonParser<T extends AbstractLocation> {
    private T location;
    private final List<MapLocation> mapLocations = new ArrayList<>();
    private final List<String> floors = new ArrayList<>();

    /**
     * Adds a map location to the list of map locations.
     * @param mapLocation the map location to add.
     */
    protected void addMapLocation(MapLocation mapLocation) {
        mapLocations.add(mapLocation);
    }

    /**
     * Adds a floor id to the list of floor ids.
     * @param floorId the floor id to add.
     */
    protected void addFloorId(String floorId) {
        floors.add(floorId);
    }

    /**
     * Returns the list of map locations.
     * @return the list of map locations.
     */
    public List<MapLocation> getMapLocations() {
        return mapLocations;
    }

    /**
     * Returns the list of floor ids.
     * @return the list of floor ids.
     */
    public List<String> getFloorIds() {
        return floors;
    }

    /**
     * Sets the location object.
     * @param newLocation the location object to set.
     */
    protected void setMyObject(T newLocation) {
        this.location = newLocation;
    }

    /**
     * Returns the location object.
     * @return the location object.
     */
    protected T getMyObject() {
        return location;
    }

    /**
     * Parses the json string and returns an object.
     * @return the object parsed from the json string.
     */
    public T buildEntity() {
        return getMyObject();
    }
}
