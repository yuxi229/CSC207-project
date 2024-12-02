package use_case.navigation;

import data_access.LocationDataAccess;
import use_case.navigation.pathfinder.PathFinder;

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

            final NavigationOutputData output = new NavigationOutputData(
                    pathFinder.getPath(departureRoomCode, destinationRoomCode));
            naviPresenter.prepareSuccessView(output);

    }
}

