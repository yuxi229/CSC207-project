package api_infrastructure;

import java.util.List;
import java.util.Map;

import data_access.LocationDaoBuilder;
import data_access.LocationDataAccess;
import data_access.MapLocationDaoBuilder;
import data_access.MapLocationDataAccess;
import entity.LocationFactory;
import use_case.navigation.maplocation.MapLocationFactory;

/**
 * Utility to parse raw API data into a LocationDataAccess object.
 */
public class EntityParser {
    public static final String CONNECTED = "connected";
    public static final String FLOOR = "floor";
    public static final String SIZE = "size";
    public static final String RESTRICTED = "restricted";
    public static final String IS_RESTRICTED = "Y";
    public static final String Y_POS_KEY = "imgYpos";
    public static final String X_POS_KEY = "imgXpos";
    public static final Double DEFAULT_LENGTH = 1.0;
    public static final String F1_XPOS = "f1x";
    public static final String F1_YPOS = "f1y";
    public static final String F2_XPOS = "f2x";
    public static final String F2_YPOS = "f2y";
    public static final String FLOORSTART = "floorstart";
    public static final String FLOOREND = "floorend";
    public static final String OUT = "out";

    private final ApiCilent apiClient;
    private final LocationDaoBuilder locationsBuilder;
    private final MapLocationDaoBuilder mapLocationsBuilder;
    private final MapLocationFactory mapLocationFactory;

    public EntityParser(ApiCilent apiClient, LocationDaoBuilder locationsBuilder,
                        MapLocationDaoBuilder mapLocationsBuilder, MapLocationFactory mapLocationFactory) {
        this.apiClient = apiClient;
        this.locationsBuilder = locationsBuilder;
        this.mapLocationsBuilder = mapLocationsBuilder;
        this.mapLocationFactory = mapLocationFactory;

        parseRooms();
        parseCorridors();
        parseWashrooms();
        parseValves();
        parseStairs();
        parseElevators();
    }

    /**
     * Load the raw API data from the given APIClient into a LocationDataAccess object and return it.
     * @return the LocationDataAccess object containing the data
     */
    public LocationDataAccess getLocationData() {
        return locationsBuilder.createDataAccessObject();
    }

    /**
     * Load the raw API data from the given APIClient into a MapLocationDataAccess object and return it.
     * @return the MapLocationDataAccess object containing the data
     */
    public MapLocationDataAccess getMapLocationData() {
        return mapLocationsBuilder.createMapLocationDao();
    }

    private void parseRooms() {
        final Map<String, Object> rawRooms = apiClient.fetchRooms();
        for (Map.Entry<String, Object> entry : rawRooms.entrySet()) {
            final String id = entry.getKey();
            final Map<String, Object> data = (Map<String, Object>) entry.getValue();
            final List<String> connected = (List<String>) data.get(CONNECTED);
            // Remove OUT from connected
            connected.remove(OUT);
            locationsBuilder.addLocation(LocationFactory.create(
                    id,
                    connected,
                    (int) (double) data.get(FLOOR),
                    (int) (double) data.get(SIZE),
                    IS_RESTRICTED.equals(data.get(RESTRICTED))
            ));
            mapLocationsBuilder.addMapLocation(mapLocationFactory.createMapLocation(
                    id,
                    (int) (double) data.get(X_POS_KEY),
                    (int) (double) data.get(Y_POS_KEY),
                    (int) (double) data.get(FLOOR)
            ));
        }
    }

    private void parseCorridors() {
        final Map<String, Object> rawCorridors = apiClient.fetchCorridors();
        for (Map.Entry<String, Object> entry : rawCorridors.entrySet()) {
            final String id = entry.getKey();
            final Map<String, Object> data = (Map<String, Object>) entry.getValue();
            final List<String> connected = (List<String>) data.get(CONNECTED);
            // Remove OUT from connected
            connected.remove(OUT);
            // For valves, create corridors
            locationsBuilder.addLocation(LocationFactory.create(
                    id,
                    connected,
                    (int) (double) data.get(FLOOR),
                    (int) (double) data.get(SIZE),
                    DEFAULT_LENGTH
            ));
            mapLocationsBuilder.addMapLocation(mapLocationFactory.createMapLocation(
                    id,
                    (int) (double) data.get(X_POS_KEY),
                    (int) (double) data.get(Y_POS_KEY),
                    (int) (double) data.get(FLOOR)
            ));
        }
    }

    private void parseWashrooms() {
        final Map<String, Object> rawWashrooms = apiClient.fetchWashrooms();
        for (Map.Entry<String, Object> entry : rawWashrooms.entrySet()) {
            final String id = entry.getKey();
            final Map<String, Object> data = (Map<String, Object>) entry.getValue();
            final List<String> connected = (List<String>) data.get(CONNECTED);
            // Remove OUT from connected
            connected.remove(OUT);
            locationsBuilder.addLocation(LocationFactory.create(
                    id,
                    connected,
                    (int) (double) data.get(FLOOR),
                    (int) (double) data.get(SIZE),
                    (String) data.get("gender")
            ));
            mapLocationsBuilder.addMapLocation(mapLocationFactory.createMapLocation(
                    id,
                    (int) (double) data.get(X_POS_KEY),
                    (int) (double) data.get(Y_POS_KEY),
                    (int) (double) data.get(FLOOR)
            ));
        }
    }

    private void parseValves() {
        // TODO: Discuss what to do with Valves
        final Map<String, Object> rawValves = apiClient.fetchValves();
        for (Map.Entry<String, Object> entry : rawValves.entrySet()) {
            final String id = entry.getKey();
            final Map<String, Object> data = (Map<String, Object>) entry.getValue();
            final List<String> connected = (List<String>) data.get(CONNECTED);
            // Remove OUT from connected
            connected.remove(OUT);
            // For valves, create corridors
            locationsBuilder.addLocation(LocationFactory.create(
                    id,
                    connected,
                    (int) (double) data.get(FLOOR),
                    (int) (double) data.get(SIZE),
                    DEFAULT_LENGTH
            ));
            mapLocationsBuilder.addMapLocation(mapLocationFactory.createMapLocation(
                    id,
                    (int) (double) data.get(X_POS_KEY),
                    (int) (double) data.get(Y_POS_KEY),
                    (int) (double) data.get(FLOOR)
            ));
        }
    }

    private void parseStairs() {
        final Map<String, Object> rawStairs = apiClient.fetchStairs();
        for (Map.Entry<String, Object> entry : rawStairs.entrySet()) {
            final String id = entry.getKey();
            final Map<String, Object> data = (Map<String, Object>) entry.getValue();
            final List<String> connected = (List<String>) data.get(CONNECTED);
            // Remove OUT from connected
            connected.remove(OUT);
            locationsBuilder.addLocation(LocationFactory.create(
                    id,
                    (int) (double) data.get(FLOORSTART),
                    (int) (double) data.get(FLOOREND),
                    connected.get(0),
                    connected.get(connected.size() - 1),
                    (int) (double) data.get(SIZE)
            ));
            mapLocationsBuilder.addMapLocation(mapLocationFactory.createMapLocation(
                    id,
                    (int) (double) data.get(F1_XPOS),
                    (int) (double) data.get(F1_YPOS),
                    (int) (double) data.get(FLOORSTART)
            ));
            mapLocationsBuilder.addMapLocation(mapLocationFactory.createMapLocation(
                    id,
                    (int) (double) data.get(F2_XPOS),
                    (int) (double) data.get(F2_YPOS),
                    (int) (double) data.get(FLOOREND)
            ));
        }
    }

    private void parseElevators() {
        final Map<String, Object> rawElevators = apiClient.fetchElevators();
        for (Map.Entry<String, Object> entry : rawElevators.entrySet()) {
            final String id = entry.getKey();
            final Map<String, Object> data = (Map<String, Object>) entry.getValue();
            locationsBuilder.addLocation(LocationFactory.create(
                    id,
                    (List<String>) data.get(CONNECTED),
                    List.of((int) (double) data.get(FLOORSTART), (int) (double) data.get(FLOOREND)),
                    (int) (double) data.get(SIZE)
            ));

            // TODO: Discuss extendability issue of storing different floors under different keys for elevators
            mapLocationsBuilder.addMapLocation(mapLocationFactory.createMapLocation(
                    id,
                    (int) (double) data.get(F1_XPOS),
                    (int) (double) data.get(F1_YPOS),
                    (int) (double) data.get(FLOORSTART)
            ));
            mapLocationsBuilder.addMapLocation(mapLocationFactory.createMapLocation(
                    id,
                    (int) (double) data.get(F2_XPOS),
                    (int) (double) data.get(F2_YPOS),
                    (int) (double) data.get(FLOOREND)
            ));
        }
    }
}
