package interface_adapter.inputrooms;

/**
 * The state for the Navigation View Model.
 */
public class InputRoomsState {
    private String departureRoomCode = "";
    private String departureRoomCodeError;
    private String destinationRoomCode = "";
    private String destinationRoomCodeError;


    public String getDepartureRoomCodeRoomCode() {
        return departureRoomCode;
    }

    public String getDestinationRoomCodeRoomCode() { return destinationRoomCode; }

    public String getDepartureRoomCodeError() {
        return departureRoomCodeError;
    }

    public String getDestinationRoomCodeError() {
        return destinationRoomCodeError;
    }

    public void setDepartureRoomCode(String departureRoomCode) {
        this.departureRoomCode = departureRoomCode;
    }

    public void setDestinationRoomCode(String destinationRoomCode) {
        this.destinationRoomCode = destinationRoomCode;
    }

    public void setDepartureRoomCodeError(String departureRoomCodeError) {
        this.departureRoomCode = departureRoomCodeError;
    }

    public void setDestinationRoomCodeError(String destinationRoomCodeError) {
        this.destinationRoomCodeError = destinationRoomCodeError;
    }


}
