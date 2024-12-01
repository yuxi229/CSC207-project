package interface_adapter.inputrooms;

import interface_adapter.ViewModel;

/**
 * The View Model for the Navigation View.
 */
public class InputRoomsViewModel extends ViewModel<InputRoomsState> {
    public InputRoomsViewModel() {
        // TODO: Edit to a more descriptive name
        super("Room Input View Model");
        setState(new InputRoomsState());
    }
}
