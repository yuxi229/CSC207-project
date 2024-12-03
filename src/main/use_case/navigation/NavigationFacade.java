package use_case.navigation;

import data_access.LocationDataAccess;
import use_case.room_exists.RoomValidationInteractor;

public class NavigationFacade implements NavigationInputBoundary {
    private final RoomValidationInteractor roomValidationInteractor;
    private final NavigationInteractor navigationInteractor;
    private final LocationDataAccess locationDataAccess;

    public NavigationFacade(RoomValidationInteractor roomValidationInteractor,
                            NavigationInteractor navigationInteractor, LocationDataAccess locationDataAccess) {
        this.roomValidationInteractor = roomValidationInteractor;
        this.navigationInteractor = navigationInteractor;
        this.locationDataAccess = locationDataAccess;
    }

    @Override
    public void execute(NavigationInputData navigationInputData) {
        roomValidationInteractor.execute(navigationInputData);
        if (locationDataAccess.roomExists(navigationInputData.getDepartureRoomCode()) &&
                locationDataAccess.roomExists(navigationInputData.getDestinationRoomCode())) {
            navigationInteractor.execute(navigationInputData);
        }
    }
}