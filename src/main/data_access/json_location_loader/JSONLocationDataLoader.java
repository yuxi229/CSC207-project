package data_access.json_location_loader;

import java.util.ArrayList;
import java.util.List;

import data_access.InMemoryLocationDao;
import entity.AbstractLocation;
import use_case.navigation.MapLocation;

/**
 * A class that loads data from a JSON object received from the API call into an InMemoryLocationDAO object.
 */
public class JSONLocationDataLoader {
    private String jsonData;
    private List<AbstractLocation> locationList = new ArrayList<>();
    private List<MapLocation> mapLocationList = new ArrayList<>();

    JSONLocationDataLoader(String jsonData) {
        this.jsonData = jsonData;
    }

    private void buildRooms() {

    }

    private void buildCorridors() {

    }

    private void buildStairs() {

    }

    /**
     * Returns the InMemoryLocationDAO object.
     * @return an InMemoryLocationDAO object that contains the location data in the JSON string.
     */
    public InMemoryLocationDao getLocationDao() {
        return new InMemoryLocationDao(locationList, mapLocationList);
    }
}