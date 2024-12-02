package app;

import java.awt.CardLayout;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.LocationDataAccess;
import data_access.MapLocationDataAccess;
import interface_adapter.BlueprintViewModel;
import interface_adapter.ViewManagerModel;
import view.BlueprintSelectionView;
import view.InputRoomsView;
import view.ViewManager;

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
    }

    /**
     * Adds the BluePrintSelectionView to the card panel.
     * The view provides a user interface for selecting blueprints and navigating back the input rooms view.
     */
    public void addBlueprintSelectionView() {
        blueprintSelectionView = new BlueprintSelectionView(
                Arrays.asList("floor1.jpg", "floor2.jpg"),
                () -> viewManagerModel.setState("inputRoomsView"),
                () -> System.out.println("Switch blueprint logic here")
        );
        cardPanel.add(blueprintSelectionView, "blueprintSelectionView");
    }

    /**
     * Builds and displays the main application window with all views.
     * Initializes the JFrame, sets the properties, and makes it visible to the user.
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
