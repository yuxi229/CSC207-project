package data_access;

import entity.Location;
import use_case.navigation.MapLocation;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

/**
 * A class that loads data from a JSON object received from the API call into an InMemoryLocationDAO object.
 */
public class APIJSONDataLoader {
    private List<Location> locationList = new ArrayList<>();
    private List<MapLocation> mapLocationList = new ArrayList<>();
    private JSONObject data;

    APIJSONDataLoader(JSONObject data) {
        this.data = data;

    }

    private void buildRooms() {

    }

    private void buildCorridors() {

    }

    private void buildStairs() {

    }

    private void buildElevators() {

    }


    public InMemoryLocationDAO getInMemoryLocationDAO() {
        return new InMemoryLocationDAO(locationList, mapLocationList);
    }
}