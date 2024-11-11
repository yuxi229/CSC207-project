package use_case.navigation;

/**
 * The Navigation Interactor
 */
public class NavigationInteractor {
    private final NavigationDataAccessInterface navigationDataAccessObject;
    private final NavigationOutputBoundary navigationPresenter;

    public NavigationInteractor(NavigationDataAccessInterface navigationDataAccessInterface,
                                NavigationOutputBoundary navigationOutputBoundary) {
        this.navigationDataAccessObject = navigationDataAccessInterface;
        this.navigationPresenter = navigationOutputBoundary;

        @Override
        public void execute(NavigationInputData navigationInputData) {
            final String departureRoomCode = navigationInputData. getDepartureRoomCode();
            final String destinationRoomCode = navigationInputData.getgetDestinationRoomCode();
            if (!navigationDataAccessObject.existsByName(roomcode)) {
                navigationPresenter.prepareFailView(roomcode + ": Room does not exist.");
            }
            else {
                    final Room room = roomDataAccessObject.get(navigationInputData.getRoomCode());

                    final NavigationOutputData navigationOutputData = new NavigationOutputData(room.getRoomCode(), false);
                    navigationPresenter.prepareSuccessView(navigationOutputData);
                }
            }
        }

}
