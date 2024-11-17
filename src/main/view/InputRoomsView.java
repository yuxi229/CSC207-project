package view;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.navigation.NavigationController;
import interface_adapter.navigation.NavigationViewModel;

/**
 * The View for when the user is inputting departure and destination rooms  into the program.
 */
public class InputRoomsView extends JPanel implements PropertyChangeListener {
    private final String viewName = "inputting rooms";
    private final NavigationViewModel navigationViewModel;
    private final JLabel noroomErrorField = new JLabel();
    private NavigationController navigationController;

    private final JLabel departureroomcode;
    private final JLabel destinationroomcode;

    private final JButton beginNavigation;

    private final JTextField departureInputField = new JTextField(15);
    private final JButton enterDeparture;

    private final JTextField destinationInputField = new JTextField(15);
    private final JButton enterDestination;

    public InputRoomsView(NavigationViewModel navigationViewModel) {
        this.navigationViewModel = navigationViewModel;
        this.navigationViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Enter Room Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        final JPanel buttons = new JPanel();
        logOut = new JButton("Log Out");
        buttons.add(logOut);

        changePassword = new JButton("Change Password");
        buttons.add(changePassword);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoggedInState currentState = loggedInViewModel.getState();
                currentState.setPassword(passwordInputField.getText());
                loggedInViewModel.setState(currentState);
            }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
