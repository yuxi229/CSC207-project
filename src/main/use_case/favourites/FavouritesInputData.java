package use_case.favourites;

/**
 * The Input Data for the Navigation Use Case.
 */
public class FavouritesInputData {
    private final String departureRoomCode;
    private final String destinationRoomCode;

    public FavouritesInputData(String departureRoom, String destinationRoom) {
        this.departureRoomCode = departureRoom;
        this.destinationRoomCode = destinationRoom;
    }

    public String getDepartureRoomCode() {
        return departureRoomCode;
    }

    public String getDestinationRoomCode() {
        return destinationRoomCode;
    }
}
