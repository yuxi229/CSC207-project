package main.interface_adapter.inputrooms;

import main.use_case.navigation.NavigationInputBoundary;
import main.use_case.navigation.NavigationInputData;

/**
 * The controller for the Login Use Case.
 */
public class InputRoomsController {

    private NavigationInputBoundary navigationUseCaseInteractor;

    public InputRoomsController(NavigationInputBoundary navigationUseCaseInteractor) {
        this.navigationUseCaseInteractor = navigationUseCaseInteractor;
    }

    /**
     * Executes the Login Use Case.
     * @param departure_room the roomCode of the departure room
     * @param destination_room the roomCode of the destination room
     */
    public void execute(String departure_room, String destination_room) {
        final NavigationInputData navigationInputData = new NavigationInputData(departure_room, destination_room);

        navigationUseCaseInteractor.execute(navigationInputData);
    }
}
