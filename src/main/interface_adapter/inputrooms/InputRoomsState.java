package interface_adapter.inputrooms;

import org.jgrapht.alg.drawing.model.Points;
import use_case.navigation.maplocation.MapLocation;

import java.awt.*;
import java.util.List;

/**
 * The state for the Navigation View Model.
 */
public class InputRoomsState {
    private String departureRoomCode = "";
    private String destinationRoomCode = "";
    private String departureRoomCodeError;
    private String destinationRoomCodeError;
    private List<Point> path;

    public String getDepartureRoomCode() {
        return departureRoomCode;
    }

    public String getDestinationRoomCode() {
        return destinationRoomCode;
    }

    public String getDepartureRoomCodeError() {
        return departureRoomCodeError;
    }

    public String getDestinationRoomCodeError() {
        return destinationRoomCodeError;
    }

    public void setDepartureRoomCode(String departureRoomCode) {
        this.departureRoomCode = departureRoomCode;  // Correct assignment
    }

    public void setDestinationRoomCode(String destinationRoomCode) {
        this.destinationRoomCode = destinationRoomCode;  // Correct assignment
    }

    public void setDepartureRoomCodeError(String departureRoomCodeError) {
        this.departureRoomCodeError = departureRoomCodeError;
    }

    public void setDestinationRoomCodeError(String destinationRoomCodeError) {
        this.destinationRoomCodeError = destinationRoomCodeError;
    }
    public List<Point> getPath() {
        return path;
    }

    public void setPath(List<Point> path) {
        this.path = path;
    }
}
