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
public class NavigationSetupFacade {
    private InputRoomsController inputRoomsController;
    private NavigationInputBoundary naviInteractor;
    private NavigationOutputBoundary inputRoomsPresenter;

    NavigationSetupFacade(LocationDataAccess locationData, MapLocationDataAccess mapLocationData,
                          ViewManagerModel viewManagerModel) {
        setUpPresenters(viewManagerModel);
        setUpInteractors(locationData, mapLocationData);
        setUpControllers();
    }

    public InputRoomsController getInputRoomsController() {
        return inputRoomsController;
    }

    public NavigationInputBoundary getNavigationInteractor() {
        return naviInteractor;
    }

    public NavigationOutputBoundary getInputRoomsPresenter() {
        return inputRoomsPresenter;
    }

    private void setUpPresenters(ViewManagerModel viewManagerModel) {
        final InputRoomsViewModel inputRoomsViewModel = new InputRoomsViewModel();
        final BeginNavigationViewModel beginNavigationViewModel = new BeginNavigationViewModel();
        inputRoomsPresenter = new InputRoomsPresenter(viewManagerModel, beginNavigationViewModel, inputRoomsViewModel);
    }

    private void setUpInteractors(LocationDataAccess locationData,
                                  MapLocationDataAccess mapLocationData) {
        // TODO: Retrieve filter from a related interactor.
        final ClassNameFilter filter = new ClassNameFilter();
        final PathFinder pathFinder = new FilteredJgraphtPathfinder(locationData, mapLocationData, filter);
        naviInteractor = new NavigationInteractor(locationData, pathFinder, inputRoomsPresenter);
    }

    private void setUpControllers() {
        inputRoomsController = new InputRoomsController(naviInteractor);
    }
}
