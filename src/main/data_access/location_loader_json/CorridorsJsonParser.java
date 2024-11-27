package data_access.location_loader_json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import entity.Corridor;
import entity.CorridorFactory;
import use_case.ImageMapLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * A helper class that reads a corridor json string.
 */
public class CorridorsJsonParser extends AbstractLocationJsonParser<Corridor> implements LocationJsonParser<Corridor> {
    private static final Double DEFAULT_LENGTH = 1.0;

    @JsonCreator
    public CorridorsJsonParser(
            @JsonProperty("ID") String id,
            @JsonProperty("Floor") int floorNumber,
            @JsonProperty("imgX") int imgX,
            @JsonProperty("imgY") int imgY,
            @JsonProperty("Connected") String[] connected) {

        buildCorridor(id, floorNumber, connected);
        for (String floorId : getMyObject().getFloors()) {
            addMapLocation(new ImageMapLocation(id, imgX, imgY, floorId));
            addFloorId(floorId);
        }
    }

    private void buildCorridor(String id, int floorNumber, String[] connected) {
        final String floorId = String.valueOf(floorNumber);
        final List<String> roomList = new ArrayList<>();
        final List<String> corridorList = new ArrayList<>();
        final List<String> stairsList = new ArrayList<>();

        for (String connectedLocation : connected) {
            if (connectedLocation.startsWith("R")) {
                roomList.add(connectedLocation);
            }
            else if (connectedLocation.startsWith("C")) {
                corridorList.add(connectedLocation);
            }
            else if (connectedLocation.startsWith("S")) {
                stairsList.add(connectedLocation);
            }
        }

        setMyObject(new CorridorFactory().createCorridor(id, roomList, stairsList, corridorList, floorId,
                DEFAULT_LENGTH));
    }
}
