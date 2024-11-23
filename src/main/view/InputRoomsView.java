package view;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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

import service.PathFinder;
import entity.Room;
import interface_adapter.inputrooms.InputRoomsController;
import interface_adapter.inputrooms.InputRoomsState;
import interface_adapter.inputrooms.InputRoomsViewModel;

/**
 * The View for when the user is inputting the destination room and viewing the map.
 */
public class InputRoomsView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "inputting room";
    private final InputRoomsViewModel inputRoomsViewModel;

    private final JTextField destinationRoomField = new JTextField(15);
    private final JLabel noDestinationRoomErrorField = new JLabel();

    private final JButton beginNavigation;
    private final MapPanel mapPanel;
    private InputRoomsController inputRoomsController;

    private final Map<String, Room> roomMap; // Room data mapping

    public InputRoomsView(InputRoomsViewModel inputRoomsViewModel) {
        this.inputRoomsViewModel = inputRoomsViewModel;
        this.inputRoomsViewModel.addPropertyChangeListener(this);

        // Initialize the map
        this.roomMap = initializeRooms();

        final JLabel title = new JLabel("Enter Destination Room");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel destinationRoomCode = new LabelTextPanel(new JLabel("Destination Room"), destinationRoomField);

        final JPanel buttons = new JPanel();
        beginNavigation = new JButton("Begin Navigation");
        buttons.add(beginNavigation);

        mapPanel = new MapPanel("map.jpg");

        beginNavigation.addActionListener(e -> {
            String destinationRoomId = destinationRoomField.getText();
            Room startRoom = roomMap.get("Entrance"); // Default starting point is the entrance
            Room destinationRoom = roomMap.get(destinationRoomId);

            if (startRoom != null && destinationRoom != null) {
                // Use the pathfinding service to get the path
                List<Point> path = PathFinder.findPath(startRoom, destinationRoom);
                mapPanel.setPath(path); // Update the map
            } else {
                System.err.println("Invalid room selection: " + destinationRoomId);
                noDestinationRoomErrorField.setText("Invalid room ID: " + destinationRoomId);
            }
        });

        // Text field listener
        destinationRoomField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final InputRoomsState currentState = inputRoomsViewModel.getState();
                currentState.setDestinationRoom(destinationRoomField.getText());
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

        // Layout settings
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add components
        this.add(title);
        this.add(destinationRoomCode);
        this.add(noDestinationRoomErrorField);
        this.add(buttons);
        this.add(mapPanel); // Add map panel
    }

    /**
     * Initialize room data
     */
    private Map<String, Room> initializeRooms() {
        Map<String, Room> rooms = new HashMap<>();
        // Example room data (adjust as needed)
        rooms.put("Entrance", new Room("Entrance", new Point(50, 50)));
        rooms.put("Room 101", new Room("Room 101", new Point(100, 150)));
        rooms.put("Room 102", new Room("Room 102", new Point(200, 150)));
        rooms.put("Room 201", new Room("Room 201", new Point(100, 300)));
        rooms.put("Room 202", new Room("Room 202", new Point(200, 300)));

        // Establish room connections (adjacency relationships)
        rooms.get("Entrance").addNeighbor(rooms.get("Room 101"));
        rooms.get("Room 101").addNeighbor(rooms.get("Room 102"));
        rooms.get("Room 101").addNeighbor(rooms.get("Room 201"));
        rooms.get("Room 102").addNeighbor(rooms.get("Room 202"));

        return rooms;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final InputRoomsState state = (InputRoomsState) evt.getNewValue();
        setFields(state);
        noDestinationRoomErrorField.setText(state.getDestinationRoomCodeError());
    }

    private void setFields(InputRoomsState state) {
        destinationRoomField.setText("state.getDestinationRoom()");
    }

    public String getViewName() {
        return viewName;
    }

    public void setInputRoomsController(InputRoomsController inputRoomsController) {
        this.inputRoomsController = inputRoomsController;
    }
}
