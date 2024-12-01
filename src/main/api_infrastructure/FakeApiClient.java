package api_infrastructure;

import java.util.Map;

/**
 * Fake implementation of the APIClient interface for testing purposes.
 */
public class FakeApiClient implements APIClient {
    FakeApiClient() {

    }

    @Override
    public Map<String, Object> fetchRooms() {
        return null;
    }

    @Override
    public Map<String, Object> fetchCorridors() {
        return null;
    }

    @Override
    public Map<String, Object> fetchWashrooms() {
        return null;
    }

    @Override
    public Map<String, Object> fetchValves() {
        return null;
    }

    @Override
    public Map<String, Object> fetchStairs() {
        return null;
    }

    @Override
    public Map<String, Object> fetchElevators() {
        return null;
    }
}
