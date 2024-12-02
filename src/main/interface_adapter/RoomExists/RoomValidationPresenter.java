package interface_adapter.RoomExists;

import interface_adapter.inputrooms.NavigationState;
import use_case.room_exists.RoomValidationOutputBoundary;

/**
 * The Presenter for the Room Validation Use Case.
 */
public class RoomValidationPresenter implements RoomValidationOutputBoundary {

    private final NavigationState navigationState;

    public RoomValidationPresenter(NavigationState navigationState) {
        this.navigationState = navigationState;
    }

    @Override
    public void prepareRoomErrorView(String errorMessage) {
        // Update the NavigationState with the error message
        if (errorMessage.contains("Departure")) {
            navigationState.setDepartureRoomCodeError(errorMessage);
        } else if (errorMessage.contains("Destination")) {
            navigationState.setDestinationRoomCodeError(errorMessage);
        }
    }

    @Override
    public void prepareRoomSuccessView() {
        // Clear any previous errors in the NavigationState
        navigationState.setDepartureRoomCodeError(null);
        navigationState.setDestinationRoomCodeError(null);
    }
}
