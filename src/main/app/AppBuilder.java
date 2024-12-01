package app;

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
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 */
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private InputRoomsView inputRoomsView;
    private InputRoomsViewModel inputRoomsViewModel;
    private TextPromptPanel textPromptPanel;

    private BlueprintSelectionView blueprintSelectionView;
    private BlueprintViewModel blueprintViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
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

        cardPanel.add(inputRoomsView, inputRoomsView.getViewName());
        return this;
    }

    public AppBuilder addBlueprintSelectionView() {
        blueprintSelectionView = new BlueprintSelectionView(
                Arrays.asList("map1.jpg", "map2.jpg"),
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

    public JFrame build() {
        final JFrame application = new JFrame("Navigation");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.add(cardPanel);
        return application;
    }


}
