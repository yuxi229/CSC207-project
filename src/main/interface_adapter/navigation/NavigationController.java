package interface_adapter.navigation;

import use_case.navigation.NavigationInputBoundary;
import use_case.navigation.NavigationInputData;

/**
 * The controller for the Navigation Use Case.
 */
public class NavigationController {

    private final NavigationInputBoundary navigationInteractor;

    public NavigationController(NavigationInputBoundary navigationInteractor) {
        this.navigationInteractor = navigationInteractor;
    }

    /**
     * Executes the navigation use case.
     * @param departureRoom the room code of the departure room
     * @param destinationRoom the room code of the destination room
     */
    public void execute(String departureRoom, String destinationRoom) {
        // Create the input data for navigation use case
        NavigationInputData navigationInputData = new NavigationInputData(departureRoom, destinationRoom);

        // Pass the input data to the interactor for execution
        navigationInteractor.execute(navigationInputData);
    }
}
