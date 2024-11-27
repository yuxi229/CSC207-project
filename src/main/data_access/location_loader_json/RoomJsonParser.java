package data_access.location_loader_json;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import entity.Room;
import entity.RoomFactory;
import use_case.ImageMapLocation;

/**
 * A helper class that reads a room json string.
 */
public class RoomJsonParser extends AbstractLocationJsonParser<Room> implements LocationJsonParser<Room> {
    @JsonCreator
    public RoomJsonParser(
            @JsonProperty("ID") String id,
            @JsonProperty("Floor") int floorNumber,
            @JsonProperty("imgX") int imgX,
            @JsonProperty("imgY") int imgY,
            @JsonProperty("Connected") String[] connected) {
        buildRoom(id, floorNumber, connected);
        for (String floorId : getMyObject().getFloors()) {
            addMapLocation(new ImageMapLocation(id, imgX, imgY, floorId));
            addFloorId(floorId);
        }
    }

    private void buildRoom(String id, int floorNumber, String[] connected) {
        final List<String> connectedRooms = Arrays.asList(connected);
        final List<String> floorIds = List.of(String.valueOf(floorNumber));
        setMyObject(new RoomFactory().createRoom(id, connectedRooms, floorIds));
    }
}
