package app;

import data_access.InMemoryLocationDao;
import data_access.LocationDataAccess;
import data_access.MapLocationDataAccess;
import interface_adapter.inputrooms.InputRoomsController;
import interface_adapter.inputrooms.InputRoomsViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.beginnavigation.BeginNavigationViewModel;
import interface_adapter.inputrooms.InputRoomsPresenter;
import use_case.navigation.*;
import view.TextPromptPanel;
import use_case.navigation.NavigationOutputBoundary;
import view.InputRoomsView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 */
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
    private LocationDataAccess locationDataAccess;
    private MapLocationDataAccess mapLocationDataAccess;

    private InputRoomsView inputRoomsView;
    private InputRoomsViewModel inputRoomsViewModel;
    private TextPromptPanel textPromptPanel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Load all data into memory.
     */
    public void loadData() {
        loadApiLocationData();
        // TODO: Load user data.
    }

    private void loadApiLocationData() {
        // Load location data from the API
        LoadApiDataFacade.loadDataIntoMemory();
        locationDataAccess = LoadApiDataFacade.getLocationDao();
        mapLocationDataAccess = LoadApiDataFacade.getMapLocationDao();
    }

    public AppBuilder addNavigationView() {
        // Initialize the necessary components
        inputRoomsViewModel = new InputRoomsViewModel();
        textPromptPanel = new TextPromptPanel();
        BeginNavigationViewModel beginNavigationViewModel = new BeginNavigationViewModel();

        // Initialize the presenter
        InputRoomsPresenter inputRoomsPresenter = new InputRoomsPresenter(
                viewManagerModel, inputRoomsViewModel);

        // Initialize the use case interactor
        InMemoryLocationDao inMemoryDao = new InMemoryLocationDao();
        LocationDataAccess locationDataAccess = inMemoryDao;
        MapLocationDataAccess mapLocationDataAccess = inMemoryDao;

        // Initialize the PathFinder
        PathFinder pathFinder = new JgraphtPathFinder(locationDataAccess, mapLocationDataAccess);
        pathFinder.loadData(locationDataAccess); // Load data into the path finder

        // Initialize the interactor with the presenter as the output boundary
        NavigationInputBoundary navigationInteractor = new NavigationInteractor(
                locationDataAccess, pathFinder, inputRoomsPresenter);

        // Initialize the controller with the interactor
        InputRoomsController inputRoomsController = new InputRoomsController(navigationInteractor);

        // Create the InputRoomsView with all required dependencies
        inputRoomsView = new InputRoomsView(
                inputRoomsViewModel, textPromptPanel, inputRoomsPresenter, inputRoomsController);

        // Add InputRoomsView to card panel
        cardPanel.add(inputRoomsView, "InputRoomsView");
        return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame("Navigation");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.add(cardPanel);
        application.pack(); // Adjust frame to fit contents
        application.setLocationRelativeTo(null); // Centers the frame on the screen
        return application;
    }}