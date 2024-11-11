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
            final String username = navigationInputData.getUsername();
            final String password = navigationInputData.getPassword();
            if (!navigationDataAccessObject.existsByName(username)) {
                navigationPresenter.prepareFailView(username + ": Room does not exist.");
            }
            else {
                    final Room room = roomDataAccessObject.get(navigationInputData.getRoomcode());

                    final NavigationOutputData navigationOutputData = new NavigationOutputData(room.getRoom(), false);
                    navigationPresenter.prepareSuccessView(navigationOutputData);
                }
            }
        }

}
