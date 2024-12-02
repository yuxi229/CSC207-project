package use_case.room_exists;

import data_access.LocationDataAccess;
import use_case.navigation.NavigationInputBoundary;
import use_case.navigation.NavigationInputData;

public class RoomValidationInteractor implements NavigationInputBoundary {
    private final LocationDataAccess locationDAO;
    private final RoomValidationOutputBoundary roomValidationPresenter;

    // TODO: Make output boundary and presenter and pass it in as a parameter
    public RoomValidationInteractor(LocationDataAccess locationDao, RoomValidationOutputBoundary roomValidationPresenter) {
        this.locationDAO = locationDao;
        this.roomValidationPresenter = roomValidationPresenter;
    }

    @Override
    public void execute(NavigationInputData navigationInputData) {
        //TODO: Implement this method accoriding to how the output boundary is designed!
        if (!locationDAO.roomExists(navigationInputData.getDepartureRoomCode())) {
            roomValidationPresenter.prepareRoomErrorView("Departure room doesn't exist");

        } else if (!locationDAO.roomExists(navigationInputData.getDestinationRoomCode())) {
            roomValidationPresenter.prepareRoomErrorView("Destination room doesn't exist");
        }
        roomValidationPresenter.prepareRoomSuccessView();
    }
}
