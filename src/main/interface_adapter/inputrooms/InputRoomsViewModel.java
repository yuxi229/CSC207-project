package interface_adapter.inputrooms;

import interface_adapter.ViewModel;

/**
 * The View Model for the Navigation View.
 */
public class InputRoomsViewModel extends ViewModel<InputRoomsState> {
    public InputRoomsViewModel() {
        super("Room Input View Model");  // a more descriptive name
        setState(new InputRoomsState());
    }
}
