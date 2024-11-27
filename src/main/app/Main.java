package main.app;

import main.infrastructure.EntityParser;
import main.data_access.InMemoryLocationDao;
import main.infrastructure.APIClientImpl;
import main.interfaces.APIClient;

import main.use_case.LocationDataAccessInterface;
import main.use_case.navigation.MapLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import main.entity.*;

import javax.swing.JFrame;



/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {

        String apiBaseUrl = "https://be2e-138-51-70-251.ngrok-free.app/get-dictionary";
        APIClient apiClient = new APIClientImpl(apiBaseUrl);
        EntityParser entityParser = new EntityParser();

        try {
            // Fetch data from API
            Map<String, Object> roomData = apiClient.fetchRooms();
            Map<String, Object> corridorData = apiClient.fetchCorridors();
            Map<String, Object> washroomData = apiClient.fetchWashrooms();
            Map<String, Object> valveData = apiClient.fetchValves();
            Map<String, Object> stairData = apiClient.fetchStairs();
            Map<String, Object> elevatorData = apiClient.fetchElevators();

            // Parse data into entities
            List<Room> rooms = entityParser.parseRooms(roomData);
            List<Corridor> corridors = entityParser.parseCorridors(corridorData);
            List<Washroom> washrooms = entityParser.parseWashrooms(washroomData);
            List<Valve> valves = entityParser.parseValves(valveData);
            List<Stair> stairs = entityParser.parseStairs(stairData);
            List<Elevator> elevators = entityParser.parseElevators(elevatorData);

            List<MultiFloorLocation> multiFloorLocations = new ArrayList<>();
            List<Location> singleFloorLocations = new ArrayList<>();
            singleFloorLocations.addAll(rooms);
            singleFloorLocations.addAll(corridors);
            singleFloorLocations.addAll(washrooms);
            singleFloorLocations.addAll(valves);
            multiFloorLocations.addAll(stairs);
            multiFloorLocations.addAll(elevators);


            // Combine all entities into InMemoryLocationDao
            LocationDataAccessInterface locationDao = new InMemoryLocationDao(
                    singleFloorLocations,
                    multiFloorLocations
            );

            System.out.println("Data successfully loaded into InMemoryLocationDao.");
        } catch (Exception e) {
            System.err.println("Error during initialization: " + e.getMessage());
        }

        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addNavigationView()
                .addNavigationUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
