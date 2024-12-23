package interface_adapter.inputrooms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import interface_adapter.ViewManagerModel;
import use_case.navigation.NavigationOutputBoundary;
import use_case.navigation.NavigationOutputData;
import use_case.navigation.maplocation.MapLocation;

/**
 * The Presenter for the Navigation Use Case.
 */
public class InputRoomsPresenter implements NavigationOutputBoundary {

    private final NavigationViewModel navigationViewModel;
    private final ViewManagerModel viewManagerModel;

    public InputRoomsPresenter(ViewManagerModel viewManagerModel,
                               NavigationViewModel navigationViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.navigationViewModel = navigationViewModel;
    }

    @Override
    public void prepareSuccessView(NavigationOutputData outputData) {
        // Create a new state for the InputRoomsViewModel
        final NavigationState newState = new NavigationState();

        // Ensure the outputData is valid
        final List<MapLocation> pathLocations = outputData.getLocations();
        if (pathLocations == null || pathLocations.isEmpty()) {
            prepareFailView("Path not found.");
        }
        else {
            // Convert MapLocations to Points
            final List<Point> pathPoints = new ArrayList<>();
            for (MapLocation location : pathLocations) {
                final int x = location.getX();
                final int y = location.getY();
                pathPoints.add(new Point(x, y));
            }
            // Set the path in the state as List<Point>
            newState.setPath(pathPoints);

            // Convert MapLocations to floors
            final List<Integer> floors = new ArrayList<>();
            for (MapLocation location : pathLocations) {
                final int floor = location.getFloor();
                floors.add(floor);
            }
            // Set the floors in the state as List<Integer>
            newState.setFloors(floors);


            // Assume the first and last MapLocations represent the departure and destination
            final MapLocation departureLocation = pathLocations.get(0);
            final MapLocation destinationLocation = pathLocations.get(pathLocations.size() - 1);

            // Set departure and destination room codes using IDs or other appropriate properties
            newState.setDepartureRoomCode(departureLocation.getLocationID());
            newState.setDestinationRoomCode(destinationLocation.getLocationID());

            // Update the InputRoomsViewModel and fire property change
            navigationViewModel.setState(newState);
        }
    }

    @Override
    public void prepareFailView(String error) {
        final NavigationState newState = new NavigationState();

        // Clear previous errors
        newState.setDepartureRoomCodeError(null);
        newState.setDestinationRoomCodeError(null);

        // Handle errors
        if (error.toLowerCase().contains("departure")) {
            newState.setDepartureRoomCodeError("Invalid departure room.");
        }
        else if (error.toLowerCase().contains("destination")) {
            newState.setDestinationRoomCodeError("Invalid destination room.");
        }
        else {
            newState.setDepartureRoomCodeError(error);
            newState.setDestinationRoomCodeError(error);
        }

        // Update the InputRoomsViewModel and fire property change
        navigationViewModel.setState(newState);
    }
}
