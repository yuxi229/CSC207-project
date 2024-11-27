package data_access.location_loader_json;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import entity.Elevator;
import entity.ElevatorFactory;
import use_case.ImageMapLocation;

/**
 * A helper class that reads an elevator json string.
 */
public class ElevatorsJasonParser extends AbstractLocationJsonParser<Elevator> implements LocationJsonParser<Elevator> {
    private static final Double DEFAULT_LENGTH = 1.0;

    @JsonCreator
    public ElevatorsJasonParser(
            @JsonProperty("ID") String id,
            @JsonProperty("Floors") int[] floors,
            @JsonProperty("X Coordinates") int[] imgX,
            @JsonProperty("Y Coordinates") int[] imgY,
            @JsonProperty("Connected") String[] connected) {

        for (int floor : floors) {
            final String floorId = String.valueOf(floor);
            addMapLocation(new ImageMapLocation(id, imgX[floor], imgY[floor], floorId));
            addFloorId(floorId);
        }

        setMyObject(new ElevatorFactory().createElevator(id, Arrays.asList(connected), getFloorIds(), DEFAULT_LENGTH));
    }
}
