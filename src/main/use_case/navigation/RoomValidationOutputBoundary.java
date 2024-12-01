package use_case.navigation;

/**
 * The output boundary for the room validation use case.
 */
public interface RoomValidationOutputBoundary {
    /**
     * Prepare the success view.
     */
    void prepareSuccessView();

    /**
     * Prepare the fail view.
     * @param errorMessage The error message to display.
     */
    void prepareFailView(String errorMessage);

}
