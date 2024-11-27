package interfaces;

import java.util.Map;

/**
 * Interface for interacting with the building data API.
 */
public interface APIClient {
    Map<String, Object> fetchRooms();
    Map<String, Object> fetchCorridors();
    Map<String, Object> fetchWashrooms();
    Map<String, Object> fetchValves();
    Map<String, Object> fetchStairs();
    Map<String, Object> fetchElevators();
}
