package api_infrastructure;

import com.google.gson.Gson;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Implementation of APIClient using HTTP.
 */
public class APIClientImpl implements APIClient {
    private final String baseUrl;
    private final Gson gson;

    public APIClientImpl(String baseUrl) {
        this.baseUrl = baseUrl;
        this.gson = new Gson(); // Using Gson for JSON parsing
    }

    private Map<String, Object> fetchData(String endpoint) {
        try {
            URL url = new URL(baseUrl + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed: HTTP error code " + connection.getResponseCode());
            }

            try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
                return gson.fromJson(reader, Map.class);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching data from API: " + e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> fetchRooms() {
        return fetchData("/?name=r");
    }

    @Override
    public Map<String, Object> fetchCorridors() {
        return fetchData("/?name=c");
    }

    @Override
    public Map<String, Object> fetchWashrooms() {
        return fetchData("/?name=w");
    }

    @Override
    public Map<String, Object> fetchValves() {
        return fetchData("/?name=v");
    }

    @Override
    public Map<String, Object> fetchStairs() {
        return fetchData("/?name=s");
    }

    @Override
    public Map<String, Object> fetchElevators() {
        return fetchData("/?name=e");
    }
}
