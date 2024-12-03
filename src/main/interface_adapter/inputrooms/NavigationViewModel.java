package interface_adapter.inputrooms;

import interface_adapter.ViewModel;

/**
 * The View Model for the Navigation View.
 */
public class NavigationViewModel extends ViewModel<NavigationState> {
    public NavigationViewModel() {
        // TODO: Edit to a more descriptive name
        super("Navigation View Model");
        setState(new NavigationState());
    }
}
