package use_case.navigation;

/**
 * The Input Data for the Navigation Use Case.
 */
public final class NavigationInputData {
    private final String departureRoomCode;
    private final String destinationRoomCode;

    public NavigationInputData(String departureRoomCode, String destinationRoomCode) {
        this.departureRoomCode = departureRoomCode;
        this.destinationRoomCode = destinationRoomCode;
    }

    public String getDepartureRoomCode() {
        return departureRoomCode;
    }

    public String getDestinationRoomCode() {
        return destinationRoomCode;
    }
}
