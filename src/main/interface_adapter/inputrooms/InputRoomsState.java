package interface_adapter.inputrooms;

import java.awt.Point;
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
        // Correct assignment
        this.departureRoomCode = departureRoomCode;
    }

    public void setDestinationRoomCode(String destinationRoomCode) {
        // Correct assignment
        this.destinationRoomCode = destinationRoomCode;
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
