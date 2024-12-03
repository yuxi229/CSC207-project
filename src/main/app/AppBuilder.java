package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import interface_adapter.BlueprintController;
import interface_adapter.BlueprintViewModel;
import interface_adapter.ViewManagerModel;
import use_case.BlueprintSelectionInteractor;
import use_case.favourites.FavouritesOutputBoundary;
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
    // TODO: Figure out what ViewManager does.
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private BlueprintViewModel blueprintViewModel;
    private BlueprintController blueprintController;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Load all data into memory.
     */
    public void loadData() {
        loadApiLocationData();
        // TODO: Load user data
    }

    private void loadApiLocationData() {
        LoadApiDataFacade.loadDataIntoMemory();
    }

    /**
     * Set up navigation use case and add the related view to the card panel.
     */
    public void addNavigationView() {
        final NavigationSetupFacade navigationSetupFacade = new NavigationSetupFacade(
                LoadApiDataFacade.getLocationDao(),
                LoadApiDataFacade.getMapLocationDao(),
                viewManagerModel
        );

        final InputRoomsView inputRoomsView = navigationSetupFacade.getInputRoomsView();
        cardPanel.add(inputRoomsView, "InputRoomsView");
    }

    /**
     * Adds the BluePrintSelectionView to the card panel.
     * The view provides a user interface for selecting blueprints and navigating back the input rooms view.
     */
    public void addBlueprintSelectionView() {
        // Set up interactor, view model, and controller
        final BlueprintSelectionInteractor interactor = new BlueprintSelectionInteractor();
        blueprintViewModel = new BlueprintViewModel();
        blueprintController = new BlueprintController(interactor, blueprintViewModel);

        // Initialize the view model
        blueprintController.initializeBlueprints();

        // Set up the view using the controller and view model
        final BlueprintSelectionView blueprintSelectionView = new BlueprintSelectionView(
                blueprintViewModel,
                () -> viewManagerModel.setState("inputRoomsView"),
                blueprintController::onBlueprintSelected
        );

        // Add the view to the card panel
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

        application.setLocationRelativeTo(null);
        application.pack();
        application.setVisible(true);
    }
}
