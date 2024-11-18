package interface_adapter.beginnavigation;

import interface_adapter.ViewModel;

/**
 * The View Model for the Begin Navigation View.
 */
public class BeginNavigationViewModel extends ViewModel<BeginNavigationState> {
    public BeginNavigationViewModel() {
        super("begin navigation");
        setState(new BeginNavigationState());
    }
}
