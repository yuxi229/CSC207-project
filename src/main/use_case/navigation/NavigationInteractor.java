package use_case.navigation;

import entity.Room;

/**
 * The Navigation Interactor.
 */
public class NavigationInteractor implements NavigationInputBoundary {
    private final NavigationDataAccessInterface naviDAO;
    private final NavigationOutputBoundary naviPresenter;

    public NavigationInteractor(NavigationDataAccessInterface naviDAO,
                                NavigationOutputBoundary naviPresenter) {
        this.naviDAO = naviDAO;
        this.naviPresenter = naviPresenter;
    }

    @Override
    public void execute(NavigationInputData inputData) {
        final String departureRoomCode = inputData.getDepartureRoomCode();
        final String destinationRoomCode = inputData.getDestinationRoomCode();
        if (!naviDAO.roomExists(departureRoomCode)) {
            naviPresenter.prepareFailView(departureRoomCode + ": Departure room does not exist.");
        }
        else if (!naviDAO.roomExists(destinationRoomCode)) {
            naviPresenter.prepareFailView(destinationRoomCode + ": Destination room does not exist.");
        }
        else {
            NavigationOutputData output = new NavigationOutputData();
            PathFinder pathFinder = new GraphPathFinder(naviDAO); //TODO: Discuss loading the data somewhere else
            output.setPath(pathFinder.getPath(departureRoomCode, destinationRoomCode));
            naviPresenter.prepareSuccessView(output);
        }
    }
}

