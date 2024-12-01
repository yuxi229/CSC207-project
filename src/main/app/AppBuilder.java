package app;

import api_infrastructure.LoadApiDataFacade;
import data_access.LocationDataAccess;
import data_access.MapLocationDataAccess;
import interface_adapter.inputrooms.InputRoomsViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.beginnavigation.BeginNavigationViewModel;
import interface_adapter.inputrooms.InputRoomsPresenter;
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
        // Initialize dependencies
        inputRoomsViewModel = new InputRoomsViewModel();
        textPromptPanel = new TextPromptPanel();

        // Pass both InputRoomsViewModel and TextPromptPanel to InputRoomsView
        inputRoomsView = new InputRoomsView(inputRoomsViewModel, textPromptPanel);

        // Add InputRoomsView to card panel
        cardPanel.add(inputRoomsView, inputRoomsView.getViewName());
        return this;
    }

    public AppBuilder addNavigationUseCase() {
        final NavigationOutputBoundary navigationOutputBoundary = new InputRoomsPresenter(
                viewManagerModel, new BeginNavigationViewModel(), inputRoomsViewModel);
        return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame("Navigation");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.add(cardPanel);
        return application;
    }
}
