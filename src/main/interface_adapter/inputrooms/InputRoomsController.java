package interface_adapter.inputrooms;

import use_case.navigation.NavigationInputBoundary;

/**
 * The controller for the Login Use Case.
 */
public class InputRoomsController {

    private NavigationInputBoundary navigationFacade;

    public InputRoomsController(NavigationInputBoundary navigationFacade) {
        this.navigationFacade = navigationFacade;
    }
}
