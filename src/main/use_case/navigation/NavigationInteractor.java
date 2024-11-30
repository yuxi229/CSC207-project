package use_case.navigation;

import data_access.LocationDataAccess;

/**
 * The Navigation Interactor.
 */
public class NavigationInteractor implements NavigationInputBoundary {
    private final LocationDataAccess locationDao;
    private final PathFinder pathFinder;
    private final NavigationOutputBoundary naviPresenter;

    public NavigationInteractor(LocationDataAccess locationDao, PathFinder pathFinder,
                                NavigationOutputBoundary naviPresenter) {
        this.locationDao = locationDao;
        this.pathFinder = pathFinder;
        this.naviPresenter = naviPresenter;
    }

    @Override
    public void execute(NavigationInputData inputData) {
        final String departureRoomCode = inputData.getDepartureRoomCode();
        final String destinationRoomCode = inputData.getDestinationRoomCode();

        // Check if the rooms exist
        if (!locationDao.roomExists(departureRoomCode)) {
            naviPresenter.prepareFailView(departureRoomCode + ": Departure room does not exist.");
        }
        else if (!locationDao.roomExists(destinationRoomCode)) {
            naviPresenter.prepareFailView(destinationRoomCode + ": Destination room does not exist.");
        }

        // If both rooms exist, find the shortest path between them
        else {
            final NavigationOutputData output = new NavigationOutputData(
                    pathFinder.getPath(departureRoomCode, destinationRoomCode));
            naviPresenter.prepareSuccessView(output);
        }
    }
}

