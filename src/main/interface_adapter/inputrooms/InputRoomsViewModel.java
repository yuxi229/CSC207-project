package main.interface_adapter.inputrooms;

import interface_adapter.ViewModel;

/**
 * The View Model for the Navigation View.
 */
public class InputRoomsViewModel extends ViewModel<InputRoomsState> {
    public InputRoomsViewModel() {
        super("input rooms");
        setState(new InputRoomsState());
    }
}
