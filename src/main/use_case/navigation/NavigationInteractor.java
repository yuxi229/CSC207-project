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

    /**
     * Takes param containing departure and destination room codes.
     * @param navigationInputData the input data
     */
    @Override
    public void execute(NavigationInputData navigationInputData) {

        // Obtain a string of the departureRoomCode from NavigationInputData
        final String departureRoomCode = navigationInputData.getDepartureRoomCode();

        // Obtain a string of the destinationRoomCode from NavigationInputData
        final String destinationRoomCode = navigationInputData.getDestinationRoomCode();

        // If the departure code doesn't exist, then prepare corresponding fail view
        if (!navigationDataAccessObject.existsByRoomCode(departureRoomCode)) {
            navigationPresenter.prepareFailView(departureRoomCode + ": Departure room does not exist.");
        }

        // If the destination code doesn't exist, then prepare corresponding fail view
        else if (!navigationDataAccessObject.existsByRoomCode(destinationRoomCode)) {
            navigationPresenter.prepareFailView(destinationRoomCode + ": Destination room does not exist.");
        }

        // Both rooms exist
        else {

            // Create a Room obj. called departureRoom.
            final Room departureRoom = navigationDataAccessObject.getRoomCode(navigationInputData
                    .getDepartureRoomCode());

            // Create a Room obj. called destinationRoom
            final Room destinationRoom = navigationDataAccessObject.getRoomCode(navigationInputData
                    .getDestinationRoomCode());

            // Create navigationOutputData with departure and destination rooms
            final NavigationOutputData navigationOutputData = new NavigationOutputData(departureRoom.getRoomCode(),
                    destinationRoom.getRoomCode(), false);

            // Prepare the successful view, with the generated nav data
            navigationPresenter.prepareSuccessView(navigationOutputData);

        }
    }
}

