package interface_adapter.navigation;

import interface_adapter.ViewModel;

/**
 * The View Model for the Navigation View.
 */
public class NavigationViewModel extends ViewModel<NavigationState> {
    public NavigationViewModel() {
        super("input rooms");
        setState(new NavigationState());
    }
}
