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
    public execute(NavigationInputData navigationInputData) {
        return null;
//        final String departureRoomCode = navigationInputData. getDepartureRoomCode();
//        final String destinationRoomCode = navigationInputData.getDestinationRoomCode();
//        if (!navigationDataAccessObject.existsByRoomCode(departureRoomCode) ) {
//                navigationPresenter.prepareFailView(departureRoomCode + ": Departure room does not exist.");
//            }
//        else if (!navigationDataAccessObject.existsByRoomCode(destinationRoomCode) ) {
//            navigationPresenter.prepareFailView(destinationRoomCode + ": Destination room does not exist.");
//        }
//        else {
//            final Room departureRoom = roomDataAccessObject.get(navigationInputData.getDepartureRoomCode());
//            final Room destinationRoom = roomDataAccessObject.get(navigationInputData.getDestinationRoomCode());
//
//            final NavigationOutputData navigationOutputData = new NavigationOutputData(room.getRoomCode(), false);
//            navigationPresenter.prepareSuccessView(navigationOutputData);
//                }
//            }
    }
}
