package interface_adapter.RoomExists;

import interface_adapter.inputrooms.NavigationState;
import interface_adapter.inputrooms.NavigationViewModel;
import use_case.room_exists.RoomValidationOutputBoundary;

/**
 * The Presenter for the Room Validation Use Case.
 */
public class RoomValidationPresenter implements RoomValidationOutputBoundary {

    private final NavigationViewModel naviViewModel;
    public RoomValidationPresenter(NavigationViewModel naviViewModel) {
        this.naviViewModel = naviViewModel;
    }

    @Override
    public void prepareRoomErrorView(String errorMessage) {
        NavigationState newState = new NavigationState();
        // Update the NavigationState with the error message
        if (errorMessage.contains("Departure")) {
            newState.setDepartureRoomCodeError(errorMessage);
        } else if (errorMessage.contains("Destination")) {
            newState.setDestinationRoomCodeError(errorMessage);
        }
        naviViewModel.setState(newState);
//        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);

    }

    @Override
    public void prepareRoomSuccessView() {
        NavigationState newState = new NavigationState();
        // Clear any previous errors in the NavigationState
        newState.setDepartureRoomCodeError(null);
        newState.setDestinationRoomCodeError(null);
        naviViewModel.setState(newState);
    }
}
