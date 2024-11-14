package use_case.navigation;

/**
 * Output Data for the Navigation Use Case.
 */
public class NavigationOutputData {
    private final String roomCode;
    private final int estimatedTime;
    // in minutes or seconds
    private final boolean error;

    public NavigationOutputData(String roomCode, int estimatedTime, boolean error) {
        this.roomCode = roomCode;
        this.estimatedTime = estimatedTime;
        this.error = error;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public boolean isError() {
        return error;
    }
}
