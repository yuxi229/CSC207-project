package interface_adapter.inputrooms;

import use_case.navigation.NavigationInputBoundary;
import use_case.navigation.NavigationInputData;
import use_case.favourites.FavouritesInputData;
import use_case.favourites.FavouritesInputBoundary;

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
        final FavouritesInputData favouritesInputData = new FavouritesInputData(departureRoom, destinationRoom);

        // Pass the input data to the interactor for execution

        navigationInteractor.execute(navigationInputData);
        favouritesInteractor.addRouteToFavourites(favouritesInputData);
            navigationFacade.execute(navigationInputData);

    }
}
