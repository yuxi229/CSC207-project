package use_case.navigation;

/**
 * Output Data for the Navigation Use Case.
 */
public final class NavigationOutputData {
    private final String departureRoomCode;
    private final String destinationRoomCode;
    // private final int estimatedTime;
    private final boolean useCaseFailed;

    public NavigationOutputData(String departureRoomCode, String destinationRoomCode, boolean useCaseFailed) {
        this.departureRoomCode = departureRoomCode;
        this.destinationRoomCode = destinationRoomCode;
        // this.estimatedTime = estimatedTime;
        this.useCaseFailed = useCaseFailed;
    }

    public String getDepartureRoomCode() {
        return departureRoomCode;
    }

    public String getDestinationRoomCode() {
        return destinationRoomCode;
    }

    //    public int getEstimatedTime() {
    //        return estimatedTime;
    //    }

    //    public boolean isError() {
    //        return useCaseFailed;
    //    }
}
