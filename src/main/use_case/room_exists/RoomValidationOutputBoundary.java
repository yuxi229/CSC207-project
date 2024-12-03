package use_case.room_exists;

/**
 * Output Boundary for the Room Exists Use Case.
 */
public interface RoomValidationOutputBoundary {

    /**
     * Prepares the failure view for the Room Exists Use Case.
     * @param errorMessage the output data
     */
    void prepareRoomErrorView(String errorMessage);

    /**
     * Prepares the success view for the Room Exists Use Case.
     */
    void prepareRoomSuccessView();
}
