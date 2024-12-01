package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.LocationDataAccess;
import data_access.MapLocationDataAccess;
import interface_adapter.ViewManagerModel;
import view.InputRoomsView;
import view.ViewManager;

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
     * @return This instance
     */
    public AppBuilder addNavigationView() {
        final NavigationSetupFacade navigationSetupFacade = new NavigationSetupFacade(
                locationDataAccess, mapLocationDataAccess, viewManagerModel);

        // Create the InputRoomsView with all required dependencies
        final InputRoomsView inputRoomsView = navigationSetupFacade.getInputRoomsView();

        // Add InputRoomsView to card panel
        cardPanel.add(inputRoomsView, "InputRoomsView");
        return this;
    }

    /**
     * TODO: Add javadoc.
     * @return The JFrame of the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Navigation");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.add(cardPanel);
        // Adjust frame to fit contents
        application.pack();
        // Centers the frame on the screen
        application.setLocationRelativeTo(null);
        return application;
    }
}
