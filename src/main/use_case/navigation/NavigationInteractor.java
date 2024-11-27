package use_case.navigation;

import use_case.LocationDataAccessInterface;

/**
 * The Navigation Interactor.
 */

public class NavigationInteractor implements NavigationInputBoundary {
    private final LocationDataAccessInterface locationDAO;
    private final NavigationOutputBoundary naviPresenter;

    public NavigationInteractor(LocationDataAccessInterface locationDAO,
                                NavigationOutputBoundary naviPresenter) {
        this.locationDAO = locationDAO;
        this.naviPresenter = naviPresenter;
    }

    @Override
    public void execute(NavigationInputData inputData) {
        final String departureRoomCode = inputData.getDepartureRoomCode();
        final String destinationRoomCode = inputData.getDestinationRoomCode();

        // Check if the rooms exist
        if (!locationDAO.roomExists(departureRoomCode)){
            naviPresenter.prepareFailView( "Departure room does not exist.");
        }
        else if (!locationDAO.roomExists(destinationRoomCode)) {
            naviPresenter.prepareFailView("Destination room does not exist.");
        }

        // If both rooms exist, find the shortest path between them
        else {
            PathFinder pathFinder = new GraphPathFinder(locationDAO); //TODO: Discuss pre-loading this data somewhere
            NavigationOutputData output = new NavigationOutputData(
                    pathFinder.getPath(departureRoomCode, destinationRoomCode), false);
            naviPresenter.prepareSuccessView(output);
        }
    }
}

