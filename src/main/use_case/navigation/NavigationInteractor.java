package use_case.navigation;

import entity.Room;

/**
 * The Navigation Interactor.
 */
public class NavigationInteractor implements NavigationInputBoundary {
    private final NavigationDataAccessInterface navigationDataAccessObject;
    private final NavigationOutputBoundary navigationPresenter;

    public NavigationInteractor(NavigationDataAccessInterface navigationDataAccessInterface,
                                NavigationOutputBoundary navigationOutputBoundary) {
        this.navigationDataAccessObject = navigationDataAccessInterface;
        this.navigationPresenter = navigationOutputBoundary;
    }

    @Override
    public void execute(NavigationInputData navigationInputData) {
        final String departureRoomCode = navigationInputData.getDepartureRoomCode();
        final String destinationRoomCode = navigationInputData.getDestinationRoomCode();
        if (!navigationDataAccessObject.existsByRoomCode(departureRoomCode)) {
            navigationPresenter.prepareFailView(departureRoomCode + ": Departure room does not exist.");
        }
        else if (!navigationDataAccessObject.existsByRoomCode(destinationRoomCode)) {
            navigationPresenter.prepareFailView(destinationRoomCode + ": Destination room does not exist.");
        }
        else {
            final Room departureRoom = navigationDataAccessObject.getRoomCode(navigationInputData
                    .getDepartureRoomCode());
            final Room destinationRoom = navigationDataAccessObject.getRoomCode(navigationInputData
                    .getDestinationRoomCode());

            final NavigationOutputData navigationOutputData = new NavigationOutputData("1",
                    "2", false);
            navigationPresenter.prepareSuccessView(navigationOutputData);

        }
    }
}

