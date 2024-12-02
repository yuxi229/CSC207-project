package interface_adapter.inputrooms;

import use_case.navigation.RoomValidationOutputBoundary;

/**
 * The presenter for the room validation use case.
 */
public class RoomValidationPresenter implements RoomValidationOutputBoundary {

    @Override
    public void prepareSuccessView() {

    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
