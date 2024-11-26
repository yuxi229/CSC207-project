package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import entity.Room;
import interface_adapter.beginnavigation.BeginNavigationState;
import interface_adapter.inputrooms.InputRoomsController;
import interface_adapter.inputrooms.InputRoomsState;
import interface_adapter.inputrooms.InputRoomsViewModel;

/**
 * The View for when the user is inputting departure and destination rooms into the program.
 */
public class InputRoomsView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "inputting rooms";
    private final InputRoomsViewModel inputRoomsViewModel;

    private final JTextField departureRoomField = new JTextField(15);
    private final JLabel noDepartureRoomErrorField = new JLabel();

    private final JTextField destinationRoomField = new JTextField(15);
    private final JLabel noDestinationRoomErrorField = new JLabel();

    private final JButton startFromEntrance;
    private final JButton beginNavigation;
    private InputRoomsController inputRoomsController;
    private final MapPanel mapPanel;

    private final HashMap<String, List<Point>> fixedRoute = new HashMap<>();

    public InputRoomsView(InputRoomsViewModel inputRoomsViewModel) {

        // TODO: 1. Replace this with API returned data
        //       2. Verify all API points data/models can be used in PathFinder
        //          2.a If it doesn't work, we find another way
        //       3. Possibly replace this path (list of points) with PathFinder class's function
        //          3.a This algorithm: Input: two room IDs  Output: list of points
        // In conclusion, the UI gets all location data from the API. Feed to PathFinder.

        // TODO Ideally: use PathFinder.loadData()
        fixedRoute.put(
                "1160-1170", List.of(
                        new Point(2029, 1479),
                        new Point(1644, 1476))
        );
        fixedRoute.put(
                "1160-1200", List.of(
                        new Point(2029, 1479),
                        new Point(1600, 1700),
                        new Point(1324, 2070))
        );

        this.inputRoomsViewModel = inputRoomsViewModel;
        this.inputRoomsViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Enter Rooms");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel departureRoomCode = new LabelTextPanel(new JLabel("Departure Room"), departureRoomField);
        final LabelTextPanel destinationRoomCode = new LabelTextPanel(new JLabel("Destination Room"), destinationRoomField);

        final JPanel buttons = new JPanel();
        startFromEntrance = new JButton("Start From Entrance");
        buttons.add(startFromEntrance);
        beginNavigation = new JButton("Begin Navigation");
        buttons.add(beginNavigation);

        startFromEntrance.addActionListener(this);

        mapPanel = new MapPanel("map.jpg");

        // Button click event
        beginNavigation.addActionListener(e -> {
            final String departRoomCode = departureRoomField.getText();
            final String arrivalRoomCode = destinationRoomField.getText();

            // TODO: Change here if you want to modify what path is displayed
            // TODO: Ideally, call PathFinder here to get all points instead of using the fixed point list
            // mapPanel.setPath(PathFinder.getPath(departRoomCode, arrivalRoomCode))
            mapPanel.setPath(fixedRoute.get(departRoomCode + "-" + arrivalRoomCode)); // Update map
        });

        departureRoomField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final InputRoomsState currentState = inputRoomsViewModel.getState();
                currentState.setDepartureRoom(departureRoomField.getText());
                inputRoomsViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        destinationRoomField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final InputRoomsState currentState = inputRoomsViewModel.getState();
                currentState.setDepartureRoom(destinationRoomField.getText());
                inputRoomsViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.add(title);
        this.add(departureRoomCode);
        this.add(noDepartureRoomErrorField);
        this.add(destinationRoomCode);
        this.add(noDestinationRoomErrorField);
        this.add(beginNavigation);
        this.add(mapPanel);
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final InputRoomsState state = (InputRoomsState) evt.getNewValue();
        setFields(state);
        noDepartureRoomErrorField.setText(state.getDepartureRoomCodeError());
        noDestinationRoomErrorField.setText(state.getDestinationRoomCodeError());
        // TODO Check this
    }

    private void setFields(InputRoomsState state) {
        destinationRoomField.setText(state.getRoomCode());
        departureRoomField.setText(state.getRoomCode());
    }

    public String getViewName() {
        return viewName;
    }

    public void setInputRoomsController(InputRoomsController inputRoomsController) {
        this.inputRoomsController = inputRoomsController;
    }
}
