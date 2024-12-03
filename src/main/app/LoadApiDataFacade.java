package app;

import api_infrastructure.ApiCilent;
import api_infrastructure.ApiClientImpl;
import api_infrastructure.EntityParser;
import api_infrastructure.FakeApiClient;
import data_access.InMemoryLocationDao;
import data_access.LocationDaoBuilder;
import data_access.LocationDataAccess;
import data_access.MapLocationDaoBuilder;
import data_access.MapLocationDataAccess;
import use_case.navigation.maplocation.ImageMapLocationFactory;

/**
 * Facade object representing the subsystem of loading location data from the API.
 */
public class LoadApiDataFacade {
    private static EntityParser entityParser;

    /**
     * Loads data from the API into memory and handles any exceptions that occur.
     */
    public static void loadDataIntoMemory() {
        ApiCilent apiClient = null;
        try {
            apiClient = fetchApiData();
        }
        catch (Exception exception) {
            // TODO: Throw/catch appropriate exception and handle it.
            printFailureMessageToConsole();
        }
        buildEntityParser(apiClient);
        printSuccessMessageToConsole();
    }

    /**
     * Fetches data from the API and returns an APIClient object.
     * @return APIClient object with data fetched from the API.
     */
    public static ApiCilent fetchApiData() {
        final String apiBaseUrl = "https://ea40-138-51-79-29.ngrok-free.app/get-dictionary";
        printSuccessMessageToConsole();
        return new ApiClientImpl(apiBaseUrl);
    }

    /**
     * Fetches fake data for testing purposes.
     * @return APIClient object with fake data.
     */
    public static ApiCilent fetchFakeData() {
        return new FakeApiClient();
    }

    private static void buildEntityParser(ApiCilent apiClient) {
        final LocationDaoBuilder locationDaoBuilder = new InMemoryLocationDao();
        final MapLocationDaoBuilder mapLocationDaoBuilder = new InMemoryLocationDao();
        entityParser = new EntityParser(apiClient, locationDaoBuilder, mapLocationDaoBuilder,
                new ImageMapLocationFactory());
    }

    /**
     * Returns the loaded LocationDataAccess object.
     * @return the LocationDataAccess object containing the data
     */
    public static LocationDataAccess getLocationDao() {
        return entityParser.getLocationData();
    }

    /**
     * Returns the loaded MapLocationDataAccess object.
     * @return the MapLocationDataAccess object containing the data
     */
    public static MapLocationDataAccess getMapLocationDao() {
        return entityParser.getMapLocationData();
    }

    private static void printSuccessMessageToConsole() {
        System.out.println("Data loaded successfully.");
    }

    private static void printFailureMessageToConsole() {
        System.out.println("Failed to load data from the API.");
    }
}
