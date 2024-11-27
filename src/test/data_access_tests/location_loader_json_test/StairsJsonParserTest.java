package data_access_tests.location_loader_json_test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import data_access.location_loader_json.StairsJsonParser;
import entity.Stairs;
import entity.StairsFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import use_case.ImageMapLocation;
import use_case.MapLocation;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StairsJsonParserTest {
    private static final String TEST_JSON_FILE_PATH
            = "src/test/data_access_tests/location_loader_json_test/sample_data/TestData.json";
    private static final Double DEFAULT_STAIRS_WEIGHT = 1.0;
    private static JsonNode stairs;
    private static final Map<String, StairsJsonParser> idToParser = new HashMap<>();
    private static final Map<String, Stairs> idToStairs = new HashMap<>();
    private static final Map<String, MapLocation> idToLocation = new HashMap<>();
    private static final Map<String, List<String>> idToFloorIds = new HashMap<>();
    private static final StairsFactory factory = new StairsFactory();

    @BeforeAll
    static void setUpParsers() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        stairs = mapper.readTree(new File(TEST_JSON_FILE_PATH)).get("stairs");
        for (Map.Entry<String, JsonNode> idLocationPair : stairs.properties()) {
            String id = idLocationPair.getKey();
            JsonNode location = idLocationPair.getValue();

            String locationString = location.toString();
            StairsJsonParser parser = mapper.readerFor(StairsJsonParser.class).readValue(locationString);
            idToParser.put(id, parser);
        }
    }

    @BeforeAll
    static void setUpStairs() {
        idToStairs.put("S1", factory.createStairs("S1", "1", "2", "C01",
                "C5", DEFAULT_STAIRS_WEIGHT));
        idToStairs.put("S2", factory.createStairs("S2", "2", "3", "C5",
                "C0001", DEFAULT_STAIRS_WEIGHT));
    }

    @BeforeAll
    static void setUpLocations() {
        idToLocation.put("S1-1", new ImageMapLocation("S1", 0, 0, "1"));
        idToLocation.put("S1-2", new ImageMapLocation("S1", 12, 11, "2"));
        idToLocation.put("S2-2", new ImageMapLocation("S2", 9, 11, "2"));
        idToLocation.put("S2-3", new ImageMapLocation("S2", 0, 0, "3"));
    }

    @BeforeAll
    static void setUpFloors() {
        idToFloorIds.put("S1", List.of("1", "2"));
        idToFloorIds.put("S2", List.of("2", "3"));
    }

    @Test
    void testParseStairsSampleData() {
        for (String id : idToParser.keySet()) {
            StairsJsonParser parser = idToParser.get(id);
            Stairs expected = idToStairs.get(id);
            Stairs actual = parser.buildEntity();
            assertEquals(expected.getId(), actual.getId());
        }
    }

    @Test
    void testParseStairsLocationSampleData() {
        for (String id : idToParser.keySet()) {
            StairsJsonParser parser = idToParser.get(id);
            for (MapLocation actual : parser.getMapLocations()) {
                MapLocation expected = idToLocation.get(actual.getLocationID() + "-" + actual.getFloorID());
                assertEquals(expected.getLocationID(), actual.getLocationID());
            }
        }
    }

    @Test
    void testParseStairsFloorIdsSampleData() {
        for (String id : idToParser.keySet()) {
            StairsJsonParser parser = idToParser.get(id);
            List<String> expected = idToFloorIds.get(id);
            assertEquals(expected, parser.getFloorIds());
        }
    }
}
