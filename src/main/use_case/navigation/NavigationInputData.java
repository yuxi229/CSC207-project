package use_case.navigation;

public class NavigationInputData {
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
