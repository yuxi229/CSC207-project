package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.LocationDataAccess;
import data_access.MapLocationDataAccess;
import interface_adapter.BlueprintViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.beginnavigation.BeginNavigationViewModel;
import interface_adapter.inputrooms.InputRoomsPresenter;
import interface_adapter.inputrooms.InputRoomsViewModel;
import view.BlueprintSelectionView;
import view.TextPromptPanel;
import use_case.navigation.NavigationOutputBoundary;
import view.InputRoomsView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * The AppBuilder class is responsible for putting together the pieces of our CA architecture.
 */
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    // TODO: Figure out what viewManager does.
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
    private LocationDataAccess locationDataAccess;
    private MapLocationDataAccess mapLocationDataAccess;

    private BlueprintSelectionView blueprintSelectionView;
    private BlueprintViewModel blueprintViewModel;

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
        inputRoomsViewModel = new InputRoomsViewModel();
        inputRoomsView = new InputRoomsView(inputRoomsViewModel, new TextPromptPanel());

        // Listen for blueprint selector event
        inputRoomsViewModel.addPropertyChangeListener(evt -> {
            if ("openBlueprintSelector".equals(evt.getPropertyName())) {
                viewManagerModel.setState("blueprintSelectionView");
            }
        });
    /**
     * Set up navigation use case and add the related view to the card panel.
     */
    public void addNavigationView() {
        final NavigationSetupFacade navigationSetupFacade = new NavigationSetupFacade(
                locationDataAccess, mapLocationDataAccess, viewManagerModel);

        // Create the InputRoomsView with all required dependencies
        final InputRoomsView inputRoomsView = navigationSetupFacade.getInputRoomsView();

        // Add InputRoomsView to card panel
        cardPanel.add(inputRoomsView, "InputRoomsView");
        cardPanel.add(inputRoomsView, inputRoomsView.getViewName());
        return this;
    }

    public AppBuilder addBlueprintSelectionView() {
        blueprintSelectionView = new BlueprintSelectionView(
                Arrays.asList("floor1.jpg", "floor2.jpg"),
                () -> viewManagerModel.setState("inputRoomsView"),
                () -> System.out.println("Switch blueprint logic here")
        );
        cardPanel.add(blueprintSelectionView, "blueprintSelectionView");
        return this;
    }


    public AppBuilder addNavigationUseCase() {
        final NavigationOutputBoundary navigationOutputBoundary = new InputRoomsPresenter(
                viewManagerModel, new BeginNavigationViewModel(), inputRoomsViewModel);
        return this;
    }

    /**
     * TODO: Add javadoc.
     */
    public void build() {
        final JFrame application = new JFrame("Navigation");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.add(cardPanel);

        // Centers the frame on the screen
        application.setLocationRelativeTo(null);

        // Display the application
        application.pack();
        application.setVisible(true);
    }


}
