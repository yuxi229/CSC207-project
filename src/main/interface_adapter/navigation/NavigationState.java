package interface_adapter.navigation;


/**
 * The state for the Navigation View Model.
 */
public class NavigationState {
    private String roomCode = "";
    private String roomCodeError;
    private String departureRoomCode;
    private String destinationRoomCode;

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCodeErrorError(String roomCodeError) {
        this.roomCodeError = roomCodeError;
    }

    /**
     * The method to set
     */
    public void setDepartureRoom(String roomCode) {
        this.departureRoomCode = roomCode;
    }

    public void setDestinationRoom(String roomCode) {
        this.destinationRoomCode = roomCode;
    }

}
