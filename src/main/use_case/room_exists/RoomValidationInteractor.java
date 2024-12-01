package use_case.room_exists;

import data_access.LocationDataAccess;
import use_case.navigation.NavigationInputBoundary;
import use_case.navigation.NavigationInputData;
import use_case.navigation.NavigationOutputBoundary;
import use_case.navigation.PathFinder;

public class RoomValidationInteractor implements NavigationInputBoundary {
    private final LocationDataAccess locationDAO;
    private final PathFinder pathFinder;
    private final NavigationOutputBoundary naviPresenter;

    public RoomValidationInteractor(LocationDataAccess locationDao, PathFinder pathFinder,
                                    NavigationOutputBoundary naviPresenter) {
        this.locationDAO = locationDao;
        this.pathFinder = pathFinder;
        this.naviPresenter = naviPresenter;
    }


    @Override
    public void execute(NavigationInputData navigationInputData) {
        if (!locationDAO.roomExists(roomCode)){
            naviPresenter.prepareFailView( "Departure room does not exist.");
        }
        else if (!locationDAO.roomExists(destinationRoomCode)) {
            naviPresenter.prepareFailView("Destination room does not exist.");
        }

    }
}
