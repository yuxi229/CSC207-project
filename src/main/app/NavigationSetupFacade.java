package app;

import data_access.LocationDataAccess;
import data_access.MapLocationDataAccess;
import interface_adapter.ViewManagerModel;
import interface_adapter.inputrooms.InputRoomsController;
import interface_adapter.inputrooms.InputRoomsPresenter;
import interface_adapter.inputrooms.NavigationViewModel;
import interface_adapter.RoomExists.RoomValidationPresenter;
import use_case.navigation.NavigationFacade;
import use_case.navigation.NavigationInputBoundary;
import use_case.navigation.NavigationInteractor;
import use_case.navigation.NavigationOutputBoundary;
import use_case.navigation.pathfinder.ClassNameFilter;
import use_case.navigation.pathfinder.FilteredJgraphtPathfinder;
import use_case.navigation.pathfinder.PathFinder;
import use_case.room_exists.RoomValidationInteractor;
import view.InputRoomsView;
import view.TextPromptPanel;

/**
 * A facade taking care of the subsystem of setting up the map navigation use case.
 */
public class NavigationSetupFacade {
    private final NavigationViewModel navigationViewModel = new NavigationViewModel();
    private InputRoomsController inputRoomsController;
    private RoomValidationInteractor roomValidationInteractor;
    private NavigationInteractor navigationInteractor;
    private NavigationInputBoundary naviInteractor;
    private NavigationFacade navigationFacade;
    private RoomValidationPresenter roomValidationPresenter;
    private NavigationOutputBoundary inputRoomsPresenter;
    private InputRoomsView inputRoomsView;

    NavigationSetupFacade(LocationDataAccess locationData, MapLocationDataAccess mapLocationData,
                          ViewManagerModel viewManagerModel) {
        setUpPresenters(viewManagerModel);
        setUpInteractors(locationData, mapLocationData);
        setUpControllers();
        setUpView();
    }

    public InputRoomsView getInputRoomsView() {
        return inputRoomsView;
    }

    private void setUpPresenters(ViewManagerModel viewManagerModel) {
        roomValidationPresenter = new RoomValidationPresenter(navigationViewModel);
        inputRoomsPresenter = new InputRoomsPresenter(viewManagerModel, navigationViewModel);
    }

    private void setUpInteractors(LocationDataAccess locationData,
                                  MapLocationDataAccess mapLocationData) {
        // TODO: Retrieve filter from a related interactor.
        final ClassNameFilter filter = new ClassNameFilter();
        final PathFinder pathFinder = new FilteredJgraphtPathfinder(locationData, mapLocationData,
                filter);
        roomValidationInteractor = new RoomValidationInteractor(locationData,
                roomValidationPresenter);
        navigationInteractor = new NavigationInteractor(locationData, pathFinder, inputRoomsPresenter);
        naviInteractor = new NavigationInteractor(locationData, pathFinder, inputRoomsPresenter);
        navigationFacade = new NavigationFacade(roomValidationInteractor, navigationInteractor, locationData);
    }

    private void setUpControllers() {
        inputRoomsController = new InputRoomsController(navigationFacade);
    }

    private void setUpView() {
        final TextPromptPanel textPromptPanel = new TextPromptPanel();
        inputRoomsView = new InputRoomsView(navigationViewModel, textPromptPanel,
                inputRoomsController);
    }
}
