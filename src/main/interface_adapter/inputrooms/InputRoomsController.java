package interface_adapter.inputrooms;

import use_case.navigation.NavigationInputBoundary;
import use_case.navigation.NavigationInputData;

/**
 * The controller for the Navigation Use Case.
 */
public class InputRoomsController {

    private final NavigationInputBoundary navigationFacade;

    public InputRoomsController(NavigationInputBoundary navigationFacade) {
        this.navigationFacade = navigationFacade;
    }

    /**
     * Executes the navigation use case.
     * @param departureRoom the room code of the departure room
     * @param destinationRoom the room code of the destination room
     */
    public void execute(String departureRoom, String destinationRoom) {
        // Create the input data for navigation use case
        final NavigationInputData navigationInputData = new NavigationInputData(departureRoom, destinationRoom);

        // Pass the input data to the interactor for execution
            navigationFacade.execute(navigationInputData);
    }
}
