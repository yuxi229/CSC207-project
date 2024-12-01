package app;

import data_access.LocationDataAccess;
import data_access.MapLocationDataAccess;
import interface_adapter.ViewManagerModel;
import interface_adapter.beginnavigation.BeginNavigationViewModel;
import interface_adapter.inputrooms.InputRoomsController;
import interface_adapter.inputrooms.InputRoomsPresenter;
import interface_adapter.inputrooms.InputRoomsViewModel;
import use_case.navigation.NavigationInputBoundary;
import use_case.navigation.NavigationInteractor;
import use_case.navigation.NavigationOutputBoundary;
import use_case.navigation.pathfinder.ClassNameFilter;
import use_case.navigation.pathfinder.FilteredJgraphtPathfinder;
import use_case.navigation.pathfinder.PathFinder;

/**
 * A facade taking care of the subsystem of setting up the map navigation use case.
 */
public class NavigationFacade {
    /**
     * Sets up the map navigation use case.
     * @param locationData the location data access object
     * @param mapLocationData the map location data access object
     * @param viewManagerModel the view manager model
     */
    public static void setup(LocationDataAccess locationData, MapLocationDataAccess mapLocationData,
                      ViewManagerModel viewManagerModel) {
        final NavigationOutputBoundary naviPresenter = setUpPresenter(viewManagerModel);
        final NavigationInputBoundary navigationUseCaseInteractor = setUpInteractor(locationData, mapLocationData,
                naviPresenter);
        final InputRoomsController inputRoomsController = new InputRoomsController(navigationUseCaseInteractor);
        // TODO: Implement the rest of the setup as more components are completed
    }

    private static NavigationInputBoundary setUpInteractor(LocationDataAccess locationData,
                                                           MapLocationDataAccess mapLocationData,
                                                           NavigationOutputBoundary naviPresenter) {
        // TODO: Retreive filter from a related interactor.
        final ClassNameFilter filter = new ClassNameFilter();
        final PathFinder pathFinder = new FilteredJgraphtPathfinder(locationData, mapLocationData, filter);
        return new NavigationInteractor(locationData, pathFinder, naviPresenter);
    }

    private static NavigationOutputBoundary setUpPresenter(ViewManagerModel viewManagerModel) {
        final InputRoomsViewModel inputRoomsViewModel = new InputRoomsViewModel();
        final BeginNavigationViewModel beginNavigationViewModel = new BeginNavigationViewModel();
        return new InputRoomsPresenter(viewManagerModel, beginNavigationViewModel, inputRoomsViewModel);
    }
}
