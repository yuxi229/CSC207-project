package data_access.location_loader_json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import data_access.InMemoryLocationDao;
import entity.AbstractLocation;
import entity.Corridor;
import entity.Floor;
import entity.FloorFactory;
import entity.Room;
import entity.Stairs;
import use_case.MapLocation;

/**
 * A class that loads data from a JsonLocationDataPacket into an InMemoryLocationDAO object.
 */
public class JsonLocationDataLoader {
    private static final String ROOM = "room";
    private static final String CORRIDOR = "corridor";
    private static final String STAIRS = "stairs";

    private final JsonLocationDataPacket data;

    private final ObjectMapper mapper = new ObjectMapper();
    private final List<AbstractLocation> locationList = new ArrayList<>();
    private List<MapLocation> mapLocationList;

    private final FloorBuilder floorBuilder = new FloorBuilder();

    /**
     * Constructor for the JsonLocationDataLoader class.
     * @param dataPacket an ApiLocationDataPacket object that contains the JSON data.
     */
    JsonLocationDataLoader(JsonLocationDataPacket dataPacket) {
        data = dataPacket;
    }

    private void buildLocations() {
        try {
            parseLocation(data.getRoomsJson(), ROOM);
            parseLocation(data.getCorridorsJson(), CORRIDOR);
            parseLocation(data.getStairsJson(), STAIRS);
        }
        catch (JsonProcessingException exp) {
            throw new RuntimeException(exp);
        }
    }

    private void parseLocation(String jsonLocations, String parserType) throws JsonProcessingException {
        final JsonNode locations = mapper.readTree(jsonLocations);
        for (JsonNode location : locations) {
            final LocationJsonParser<? extends AbstractLocation> parser = getParser(location.toString(), parserType);
            locationList.add(parser.buildEntity());
            mapLocationList = parser.getMapLocations();
        }
    }

    private LocationJsonParser<? extends AbstractLocation> getParser(
            String locationJSON, String parserType) throws JsonProcessingException {
        final LocationJsonParser<? extends AbstractLocation> parser;
        if (ROOM.equals(parserType)) {
            parser = mapper.readerFor(RoomJsonParser.class).readValue(locationJSON);
            floorBuilder.addRoom(parser.getFloorIds(), (Room) parser.buildEntity());
        }
        else if (CORRIDOR.equals(parserType)) {
            parser = mapper.readerFor(CorridorsJsonParser.class).readValue(locationJSON);
            floorBuilder.addCorridor(parser.getFloorIds(), (Corridor) parser.buildEntity());
        }
        else if (STAIRS.equals(parserType)) {
            parser = mapper.readerFor(StairsJsonParser.class).readValue(locationJSON);
            floorBuilder.addStairs(parser.getFloorIds(), (Stairs) parser.buildEntity());
        }
        else {
            // TODO: Raise appropriate exception
            parser = null;
        }
        return parser;
    }

    /**
     * Returns the InMemoryLocationDAO object.
     * @return an InMemoryLocationDAO object that contains the location data in the JSON string.
     */
    public InMemoryLocationDao getLocationDao() {
        buildLocations();
        return new InMemoryLocationDao(locationList, mapLocationList, floorBuilder.getFloors());
    }

    /**
     * A helper class that builds floors from the rooms, corridors, and stairs.
     */
    private static final class FloorBuilder {
        private final Map<String, List<Room>> floorIdToRoom = new HashMap<>();
        private final Map<String, List<Corridor>> floorIdToCorridor = new HashMap<>();
        private final Map<String, List<Stairs>> floorIdToStairs = new HashMap<>();
        private final List<Floor> floorList = new ArrayList<>();

        private void addRoom(List<String> floorIds, Room room) {
            addLocationHelper(floorIds, room, floorIdToRoom);
        }

        private void addCorridor(List<String> floorIds, Corridor corridor) {
            addLocationHelper(floorIds, corridor, floorIdToCorridor);
        }

        private <T extends AbstractLocation> void addLocationHelper(
                List<String> floorIds, T location, Map<String, List<T>> idToLocation) {
            for (String floorId : floorIds) {
                if (!idToLocation.containsKey(floorId)) {
                    idToLocation.put(floorId, List.of(location));
                }
                else {
                    idToLocation.get(floorId).add(location);
                }
            }
        }

        private void addStairs(List<String> floorIds, Stairs stairs) {
            addLocationHelper(floorIds, stairs, floorIdToStairs);
        }

        private void buildFloors() {
            for (String floorId : floorIdToRoom.keySet()) {
                final Floor floor = new FloorFactory().createFloor(floorId, floorIdToRoom.get(floorId),
                        floorIdToStairs.get(floorId), floorIdToCorridor.get(floorId));
                floorList.add(floor);
            }
        }

        public List<Floor> getFloors() {
            buildFloors();
            return floorList;
        }
    }
}
