package use_case.navigation;

/**
 * Output Data for the Navigation Use Case.
 */
public class NavigationOutputBoundary {

    private final String roomcode;
    private final boolean useCaseFailed;

    public NavigationOutputData(String roomcode, boolean useCaseFailed) {
        this.roomcode = roomcode;
        this.useCaseFailed = useCaseFailed;
    }

    public String getRoomcode() {
        return roomcode;
    }
}
