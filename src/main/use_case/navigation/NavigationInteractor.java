package use_case.navigation;

import use_case.LocationDataAccessInterface;
import entity.Room;

/**
 * The Navigation Interactor.
 */
public class NavigationInteractor implements NavigationInputBoundary {
    private final LocationDataAccessInterface navigationDataAccessObject;
    private final NavigationOutputBoundary navigationPresenter;

    public NavigationInteractor(LocationDataAccessInterface navigationDataAccessInterface,
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

            final NavigationOutputData navigationOutputData = new NavigationOutputData(departureRoom.getRoomCode(),
                    destinationRoom.getRoomCode(), false);
            navigationPresenter.prepareSuccessView(navigationOutputData);

        }
    }
}

