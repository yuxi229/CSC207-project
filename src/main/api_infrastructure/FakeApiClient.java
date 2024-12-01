package api_infrastructure;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import com.google.gson.Gson;

/**
 * Fake implementation of the APIClient interface for testing purposes.
 */
public class FakeApiClient implements APIClient {
    private Map<String, Object> data;

    FakeApiClient() {
        final Gson gson = new Gson();
        try {
            data = gson.fromJson(new FileReader("src/main/api_infrastructure/fake_data.json"), Map.class);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    @Override
    public Map<String, Object> fetchRooms() {
        return (Map<String, Object>) data.get("rooms");
    }

    @Override
    public Map<String, Object> fetchCorridors() {
        return (Map<String, Object>) data.get("corridors");
    }

    @Override
    public Map<String, Object> fetchWashrooms() {
        return (Map<String, Object>) data.get("washrooms");
    }

    @Override
    public Map<String, Object> fetchValves() {
        return (Map<String, Object>) data.get("valves");
    }

    @Override
    public Map<String, Object> fetchStairs() {
        return (Map<String, Object>) data.get("stairs");
    }

    @Override
    public Map<String, Object> fetchElevators() {
        return (Map<String, Object>) data.get("elevators");
    }
}
