package data_access.location_loader_json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import entity.Stairs;
import entity.StairsFactory;
import use_case.ImageMapLocation;

/**
 * A helper class that parses JSON data for Stairs objects.
 */
public class StairsJsonParser extends AbstractLocationJsonParser<Stairs> implements LocationJsonParser<Stairs> {
    private static final Double DEFAULT_LENGTH = 1.0;

    @JsonCreator
    public StairsJsonParser(
            @JsonProperty("ID") String id,
            @JsonProperty("FloorStart") int lowerFloor,
            @JsonProperty("FloorEnd") int upperFloor,
            @JsonProperty("f1X") int lowerX,
            @JsonProperty("f1Y") int lowerY,
            @JsonProperty("f2X") int upperX,
            @JsonProperty("f2Y") int upperY,
            @JsonProperty("Connected") String[] connected) {
        buildStairs(id, lowerFloor, upperFloor, connected);
        buildMapLocations(id, lowerFloor, upperFloor, lowerX, lowerY, upperX, upperY);
        addFloorIds(lowerFloor, upperFloor);
    }

    private void buildStairs(String id, int lowerFloor, int upperFloor, String[] connected) {
        setMyObject(new StairsFactory().createStairs(id, String.valueOf(lowerFloor), String.valueOf(upperFloor),
                connected[0], connected[1],
                DEFAULT_LENGTH));
    }

    private void buildMapLocations(String id, int lowerFloor, int upperFloor, int lowerX, int lowerY, int upperX,
                                   int upperY) {
        addMapLocation(new ImageMapLocation(id, lowerX, lowerY, String.valueOf(lowerFloor)));
        addMapLocation(new ImageMapLocation(id, upperX, upperY, String.valueOf(upperFloor)));
    }

    private void addFloorIds(int lowerFloor, int upperFloor) {
        addFloorId(String.valueOf(lowerFloor));
        addFloorId(String.valueOf(upperFloor));
    }
}
