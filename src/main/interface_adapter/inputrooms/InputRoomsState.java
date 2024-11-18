package interface_adapter.inputrooms;


/**
 * The state for the Navigation View Model.
 */
public class InputRoomsState {
    private String roomCode = "";
    private String roomCodeError;
    private String departureRoomCode = "";
    private String destinationRoomCode = "";

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCodeErrorError(String roomCodeError) {
        this.roomCodeError = roomCodeError;
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
