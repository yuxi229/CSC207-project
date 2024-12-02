package api_infrastructure;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.google.gson.Gson;

/**
 * Implementation of APIClient using HTTP.
 */
public class ApiClientImpl implements ApiCilent {
    private static final int HTTP_OK = 200;
    private final String baseUrl;
    private final Gson gson;

    public ApiClientImpl(String baseUrl) {
        this.baseUrl = baseUrl;
        // Using Gson for JSON parsing
        this.gson = new Gson();
    }

    private Map<String, Object> fetchData(String endpoint) {
        try {
            final URL url = new URL(baseUrl + endpoint);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() != HTTP_OK) {
                throw new RuntimeException("Failed: HTTP error code " + connection.getResponseCode());
            }

            try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
                return gson.fromJson(reader, Map.class);
            }
        }
        catch (Exception exception) {
            throw new RuntimeException("Error fetching data from API: " + exception.getMessage(), exception);
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
