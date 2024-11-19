package interface_adapter.inputrooms;


/**
 * The state for the Navigation View Model.
 */
public class InputRoomsState {
    private String roomCode = "";
    private String departureRoomCodeError;
    private String destinationRoomCodeError;
    private String departureRoomCode = "";
    private String destinationRoomCode = "";

    public String getRoomCode() {
        return roomCode;
    }

    public String getDepartureRoomCodeError() {
        return departureRoomCodeError;
    }

    public String getDestinationRoomCodeError() {
        return destinationRoomCodeError;
    }

    public void setDepartureRoomCodeError(String departureRoomCodeError) {
        this.departureRoomCode = departureRoomCodeError;
    }

    public void setDestinationRoomCodeError(String destinationRoomCodeError) {
        this.destinationRoomCodeError = destinationRoomCodeError;
    }

    /**
     * The method to set departure room the user enters.
     * @param departureRoomCode the roomcode the user enters
     */
    public void setDepartureRoom(String departureRoomCode) {
        this.departureRoomCode = roomCode;
    }

    /**
     * The method to set destination room the user enters.
     * @param destinationRoomCode the roomcode the user enters
     */
    public void setDestinationRoom(String destinationRoomCode) {
        this.destinationRoomCode = roomCode;
    }

}
