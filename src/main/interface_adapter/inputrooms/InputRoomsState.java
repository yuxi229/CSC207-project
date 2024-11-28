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

    // Getters
    public String getRoomCode() {
        return roomCode;
    }

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

    // Setters
    public void setRoomCode(String roomCode) {
        if (roomCode == null || roomCode.isEmpty()) {
            throw new IllegalArgumentException("Room code cannot be null or empty.");
        }
        this.roomCode = roomCode;
    }

    public void setDepartureRoomCode(String departureRoomCode) {
        if (departureRoomCode == null || departureRoomCode.isEmpty()) {
            throw new IllegalArgumentException("Departure room code cannot be null or empty.");
        }
        this.departureRoomCode = departureRoomCode;
    }

    public void setDestinationRoomCode(String destinationRoomCode) {
        if (destinationRoomCode == null || destinationRoomCode.isEmpty()) {
            throw new IllegalArgumentException("Destination room code cannot be null or empty.");
        }
        this.destinationRoomCode = destinationRoomCode;
    }

    public void setDepartureRoomCodeError(String departureRoomCodeError) {
        this.departureRoomCodeError = departureRoomCodeError;
    }

    public void setDestinationRoomCodeError(String destinationRoomCodeError) {
        this.destinationRoomCodeError = destinationRoomCodeError;
    }

    /**
     * The method to set the departure room the user enters.
     * @param departureRoomCode the room code the user enters
     */
    public void setDepartureRoom(String departureRoomCode) {
        if (departureRoomCode == null || departureRoomCode.isEmpty()) {
            throw new IllegalArgumentException("Departure room code cannot be null or empty.");
        }
        this.departureRoomCode = departureRoomCode;
    }

    /**
     * The method to set the destination room the user enters.
     * @param destinationRoomCode the room code the user enters
     */
    public void setDestinationRoom(String destinationRoomCode) {
        if (destinationRoomCode == null || destinationRoomCode.isEmpty()) {
            throw new IllegalArgumentException("Destination room code cannot be null or empty.");
        }
        this.destinationRoomCode = destinationRoomCode;
    }
}
