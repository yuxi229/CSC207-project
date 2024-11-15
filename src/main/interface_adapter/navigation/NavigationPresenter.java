package interface_adapter.navigation;

import use_case.navigation.NavigationOutputBoundary;
import use_case.navigation.NavigationOutputData;
import view.NavigationView;

/**
 * The Presenter for the Navigation Use Case.
 */
public class NavigationPresenter implements NavigationOutputBoundary {

    private final NavigationViewModel navigationViewModel;

    public NavigationPresenter(NavigationViewModel navigationViewModel) {
        this.navigationViewModel = navigationViewModel;
    }

    @Override
    public void prepareSuccessView(NavigationOutputData outputData) {
    }

    @Override
    public void prepareFailView(String errorMessage) {
    }
}
