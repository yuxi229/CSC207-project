package app;

import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import data_access.InMemoryRoomDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.navigation.NavigationViewModel;
import use_case.navigation.NagivationDataAccessInterface;
import view.NavigationView;

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

    private final InMemoryRoomDataAccessObject userDataAccessObject = new InMemoryRoomDataAccessObject();

    private NavigationView navigationView;
    private NavigationViewModel navigationViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Navigation View to the application.
     * @return this builder
     */
    public AppBuilder addNavigationView() {
        navigationViewModel = new NavigationViewModel();
        navigationView = new NavigationView(navigationViewModel);
        return this;
    }

    /**
     * Adds the Navigation Use Case to the application.
     * @return this builder
     */
    public AppBuilder addNavigationUseCase() {
       return null;
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
