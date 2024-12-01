package interface_adapter.inputrooms;

import interface_adapter.ViewManagerModel;
import use_case.navigation.NavigationOutputBoundary;
import use_case.navigation.NavigationOutputData;
import use_case.navigation.maplocation.MapLocation;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * The Presenter for the Navigation Use Case.
 */
public class InputRoomsPresenter implements NavigationOutputBoundary {

    private final InputRoomsViewModel inputRoomsViewModel;
    private final ViewManagerModel viewManagerModel;

    public InputRoomsPresenter(ViewManagerModel viewManagerModel,
                               InputRoomsViewModel inputRoomsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.inputRoomsViewModel = inputRoomsViewModel;
    }

    @Override
    public void prepareSuccessView(NavigationOutputData outputData) {
        // Create a new state for the InputRoomsViewModel
        final InputRoomsState newState = new InputRoomsState();

        // Ensure the outputData is valid
        final List<MapLocation> pathLocations = outputData.getLocations();
        if (pathLocations == null || pathLocations.isEmpty()) {
            prepareFailView("Path not found.");
        }

        // Convert MapLocations to Points
        final List<Point> pathPoints = new ArrayList<>();
        for (MapLocation location : pathLocations) {
            // Cast double to int
            final int x = (int) location.getX();
            final int y = (int) location.getY();
            pathPoints.add(new Point(x, y));
        }

        // Set the path in the state as List<Point>
        newState.setPath(pathPoints);

        // Assume the first and last MapLocations represent the departure and destination
        final MapLocation departureLocation = pathLocations.get(0);
        final MapLocation destinationLocation = pathLocations.get(pathLocations.size() - 1);

        // Set departure and destination room codes using IDs or other appropriate properties
        newState.setDepartureRoomCode(departureLocation.getLocationID());
        newState.setDestinationRoomCode(destinationLocation.getLocationID());

        // Update the InputRoomsViewModel and fire property change
        inputRoomsViewModel.setState(newState);
    }

    @Override
    public void prepareFailView(String error) {
        final InputRoomsState newState = new InputRoomsState();

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
        inputRoomsViewModel.setState(newState);
    }
}