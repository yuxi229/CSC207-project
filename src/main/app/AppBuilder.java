package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.InMemoryRoomDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.inputrooms.InputRoomsPresenter;
import interface_adapter.inputrooms.InputRoomsViewModel;
import interface_adapter.beginnavigation.BeginNavigationViewModel;
import use_case.navigation.NavigationInputBoundary;
import use_case.navigation.NavigationInteractor;
import use_case.navigation.NavigationOutputBoundary;
import view.InputRoomsView;
import view.BeginNavigationView;
import view.ViewManager;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel
    );
    
    private final InMemoryRoomDataAccessObject userDataAccessObject = new InMemoryRoomDataAccessObject();

    private InputRoomsView inputRoomsView;
    private InputRoomsViewModel inputRoomsViewModel;
    private BeginNavigationView beginNavigationView;
    private BeginNavigationViewModel beginNavigationViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Navigation View to the application.
     * @return this builder
     */
    public AppBuilder addNavigationView() {
        inputRoomsViewModel = new InputRoomsViewModel();
        inputRoomsView = new InputRoomsView(inputRoomsViewModel);
        cardPanel.add(inputRoomsView, inputRoomsView.getViewName());
        return this;
    }

    /**
     * Adds the Navigation Use Case to the application.
     * @return this builder
     */
    public AppBuilder addNavigationUseCase() {
        final NavigationOutputBoundary navigationOutputBoundary = new InputRoomsPresenter(viewManagerModel,
                beginNavigationViewModel, inputRoomsViewModel);
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Navigation");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        return application;
    }
}
