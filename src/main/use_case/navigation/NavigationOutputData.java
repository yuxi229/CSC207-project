package use_case.navigation;

public class NavigationOutputData {
    private final String roomcode;
    private final int estimatedTime; // in minutes or seconds
    private final boolean error;

    public NavigationOutputData(String roomcode, int estimatedTime, boolean error) {
        this.roomcode = roomcode;
        this.estimatedTime = estimatedTime;
        this.error = error;
    }

    public String getRoomcode() {
        return roomcode;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public boolean isError() {
        return error;
    }
}
