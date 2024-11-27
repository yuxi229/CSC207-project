package data_access_tests.location_loader_json_test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import data_access.location_loader_json.RoomJsonParser;
import entity.Room;
import entity.RoomFactory;
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

class RoomJsonParserTest {
    private static final String TEST_JSON_FILE_PATH
            = "src/test/data_access_tests/location_loader_json_test/sample_data/TestData.json";
    private static JsonNode rooms;
    private static final Map<String, RoomJsonParser> idToParser = new HashMap<>();
    private static final Map<String, Room> idToRoom = new HashMap<>();
    private static final Map<String, MapLocation> idToRoomLocation = new HashMap<>();
    private static final Map<String, List<String>> idToFloorIds = new HashMap<>();
    private static final RoomFactory factory = new RoomFactory();

    @BeforeAll
    static void setUpParsers() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        rooms = mapper.readTree(new File(TEST_JSON_FILE_PATH)).get("rooms");
        for (Map.Entry<String, JsonNode> idRoomPair : rooms.properties()) {
            String id = idRoomPair.getKey();
            JsonNode room = idRoomPair.getValue();

            String roomAsString = room.toString();
            RoomJsonParser parser = mapper.readerFor(RoomJsonParser.class).readValue(roomAsString);
            idToParser.put(id, parser);
        }
    }

    @BeforeAll
    static void setUpRooms() {
        idToRoom.put("R1", factory.createRoom("R1", List.of("C1"), List.of("2")));
        idToRoom.put("R2", factory.createRoom("R2", List.of("C1", "C4"), List.of("2")));
        idToRoom.put("R3", factory.createRoom("R3", List.of("C2", "C3"), List.of("2")));
        idToRoom.put("R4", factory.createRoom("R4", List.of("C4"), List.of("2")));
        idToRoom.put("R5", factory.createRoom("R5", List.of("C6"), List.of("2")));
    }

    @BeforeAll
    static void setUpRoomLocations() {
        idToRoomLocation.put("R1", new ImageMapLocation("R1", 0, 3, "2"));
        idToRoomLocation.put("R2", new ImageMapLocation("R2", 11, 3, "2"));
        idToRoomLocation.put("R3", new ImageMapLocation("R3", 3, 9, "2"));
        idToRoomLocation.put("R4", new ImageMapLocation("R4", 12, 6, "2"));
        idToRoomLocation.put("R5", new ImageMapLocation("R5", 1, 14, "2"));
    }

    @BeforeAll
    static void setUpFloors() {
        idToFloorIds.put("R1", List.of("2"));
        idToFloorIds.put("R2", List.of("2"));
        idToFloorIds.put("R3", List.of("2"));
        idToFloorIds.put("R4", List.of("2"));
        idToFloorIds.put("R5", List.of("2"));
    }

    @Test
    void parseTestSampleData() {
        for (String id : idToParser.keySet()) {
            Room roomBuilt = idToParser.get(id).buildEntity();
            Room roomExpected = idToRoom.get(id);
            assertEquals(roomExpected.getId(), roomBuilt.getId());
            assertEquals(roomExpected.getRoomCode(), roomBuilt.getRoomCode());
            assertEquals(roomExpected.getConnectedLocations(), roomBuilt.getConnectedLocations());
            assertEquals(roomExpected.getFloors(), roomBuilt.getFloors());
            assertEquals(roomExpected.getConnectedCorridors(), roomBuilt.getConnectedCorridors());
        }
    }

    @Test
    void getMapLocationsTestSampleData() {
        for (String id : idToParser.keySet()) {
            MapLocation roomLocationBuilt = idToParser.get(id).getMapLocations().get(0);
            MapLocation roomLocationExpected = idToRoomLocation.get(id);
            assertEquals(roomLocationExpected, roomLocationBuilt);
            assertEquals(roomLocationExpected.getLocationID(), roomLocationBuilt.getLocationID());
        }
    }

    @Test
    void getFloorIdsTestSampleData() {
        for (String id : idToParser.keySet()) {
            List<String> floorIdsBuilt = idToParser.get(id).getFloorIds();
            List<String> floorIdsExpected = idToFloorIds.get(id);
            assertEquals(floorIdsExpected, floorIdsBuilt);
        }
    }
}
