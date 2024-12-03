package interface_adapter.inputrooms;

import java.awt.Point;
import java.util.List;

/**
 * The state for the Navigation View Model.
 */
public class NavigationState {
    private String departureRoomCode = "";
    private String destinationRoomCode = "";
    private String departureRoomCodeError;
    private String destinationRoomCodeError;
    private List<Point> path;
    private String roomCodeError;

    public String getDepartureRoomCode() {
        return departureRoomCode;
    }

    public String getDestinationRoomCode() {
        return destinationRoomCode;
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

    public String getDepartureRoomCodeError() {
        return this.departureRoomCodeError;
    }

    public String getDestinationRoomCodeError() {
        return this.destinationRoomCodeError;
    }

    public List<Point> getPath() {
        return path;
    }

    public void setPath(List<Point> path) {
        this.path = path;
    }
}
