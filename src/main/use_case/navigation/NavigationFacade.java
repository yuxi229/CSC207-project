package use_case.navigation;

import use_case.room_exists.RoomValidationInteractor;

public class NavigationFacade implements NavigationInputBoundary {
    private final RoomValidationInteractor roomValidationInteractor;
    private final NavigationInteractor navigationInteractor;

    public NavigationFacade(RoomValidationInteractor roomValidationInteractor,
                            NavigationInteractor navigationInteractor) {
        this.roomValidationInteractor = roomValidationInteractor;
        this.navigationInteractor = navigationInteractor;
    }

    @Override
    public void execute(NavigationInputData navigationInputData) {
        roomValidationInteractor.execute(navigationInputData);
        navigationInteractor.execute(navigationInputData);
    }
}