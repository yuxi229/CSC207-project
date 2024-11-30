package view;

import interface_adapter.inputrooms.InputRoomsViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.inputrooms.InputRoomsController;
import interface_adapter.inputrooms.InputRoomsState;
import interface_adapter.inputrooms.InputRoomsViewModel;


public class InputRoomsView extends JPanel implements PropertyChangeListener {
    private final InputRoomsViewModel inputRoomsViewModel;
    private final JTextField departureRoomField = new JTextField(15);
    private final JTextField destinationRoomField = new JTextField(15);
    private final MapPanel mapPanel;
    private BeginNavigationView beginNavigationView;
    private final HashMap<String, List<Point>> fixedRoute = new HashMap<>();
    private final TextPromptPanel textPromptPanel;

    public InputRoomsView(InputRoomsViewModel inputRoomsViewModel, TextPromptPanel textPromptPanel) {
        this.inputRoomsViewModel = inputRoomsViewModel;
        this.textPromptPanel = textPromptPanel;
        // TODO: 1. Replace this with API returned data
        //       2. Verify all API points data/models can be used in PathFinder
        //          2.a If it doesn't work, we find another way
        //       3. Possibly replace this path (list of points) with PathFinder class's function
        //          3.a This algorithm: Input: two room IDs  Output: list of points
        // In conclusion, the UI gets all location data from the API. Feed to PathFinder.

        // TODO Ideally: use PathFinder.loadData()

        // Mock routes for navigation
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

        // Layout and design improvements
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(245, 245, 245)); // Soft light grey

        // Header Panel
        JPanel headerPanel = createHeaderPanel();
        this.add(headerPanel, BorderLayout.NORTH);

        // Left panel for user input
        JPanel leftPanel = createLeftPanel();
        this.add(leftPanel, BorderLayout.WEST);

        // Map panel for rendering routes
        mapPanel = new MapPanel("map.jpg");
        mapPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        mapPanel.setLayout(new BorderLayout());

        // Add "Displaying Bahen Centre" title to MapPanel
        JPanel mapTitlePanel = createMapTitlePanel();
        mapPanel.add(mapTitlePanel, BorderLayout.NORTH);

        this.add(mapPanel, BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(48, 63, 159)); // Soft blue
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel headerLabel = new JLabel("UofT Indoor Navigation");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        return headerPanel;
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(300, 0)); // Restrict width
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel title = new JLabel("Where To?");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(48, 63, 159));

        JLabel departureLabel = new JLabel("Departure Room");
        styleTextField(departureRoomField); // Apply styling to the input field

        JLabel destinationLabel = new JLabel("Destination Room");
        styleTextField(destinationRoomField); // Apply styling to the input field

        beginNavigationView = new BeginNavigationView(this::onBeginNavigation);

        // Set a preferred size for the TextPromptPanel
        textPromptPanel.setPreferredSize(new Dimension(500, 100)); // Adjust height as needed
        textPromptPanel.setMaximumSize(new Dimension(500, 100)); // Prevent expansion

        leftPanel.add(title);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(departureLabel);
        leftPanel.add(departureRoomField);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(destinationLabel);
        leftPanel.add(destinationRoomField);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(beginNavigationView.getButton()); // Add the button
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(new JLabel("Text Prompt")); // Label for the TextPromptPanel
        leftPanel.add(Box.createVerticalStrut(5));
        leftPanel.add(textPromptPanel); // Add the TextPromptPanel

        return leftPanel;
    }


    private JPanel createMapTitlePanel() {
        JPanel mapTitlePanel = new JPanel();
        mapTitlePanel.setBackground(new Color(245, 245, 245)); // Light grey background
        mapTitlePanel.setLayout(new BorderLayout()); // Use BorderLayout to align content
        mapTitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding

        // Label for "Displaying"
        JLabel displayingLabel = new JLabel("Displaying ");
        displayingLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        displayingLabel.setForeground(new Color(150, 150, 150));

        // Label for "Bahen Centre"
        JLabel bahenLabel = new JLabel("Bahen Centre");
        bahenLabel.setFont(new Font("Arial", Font.BOLD, 14));
        bahenLabel.setForeground(new Color(48, 63, 159));

        // Use a FlowLayout to group "Displaying" and "Bahen Centre"
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0)); // Align fully left, no gaps
        textPanel.setBackground(new Color(245, 245, 245)); // Match background color
        textPanel.add(displayingLabel);
        textPanel.add(bahenLabel);

        // Add the textPanel to the left side of mapTitlePanel
        mapTitlePanel.add(textPanel, BorderLayout.WEST);

        return mapTitlePanel;
    }

    private void styleTextField(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setForeground(Color.BLACK);
        textField.setBackground(new Color(240, 240, 240)); // Light grey background
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)), // Light grey border
                BorderFactory.createEmptyBorder(5, 10, 5, 10))); // Padding inside the field
        textField.setMaximumSize(new Dimension(400, 30)); // Fixed size
    }

    private void onBeginNavigation() {
        String fromRoom = getDepartureRoom();
        String toRoom = getDestinationRoom();

        // Mock findPath logic using fixedRoute
        List<Point> path = fixedRoute.get(fromRoom + "-" + toRoom);
        if (path == null) {
            JOptionPane.showMessageDialog(this,
                    "No route found from " + fromRoom + " to " + toRoom,
                    "Route Not Found",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        mapPanel.setPath(path); // Update the map with the mock path
    }

    public String getDepartureRoom() {
        return departureRoomField.getText();
    }

    public String getDestinationRoom() {
        return destinationRoomField.getText();
    }

    public void updatePath(List<Point> path) {
        mapPanel.setPath(path); // Update the map with the given path
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("path".equals(evt.getPropertyName())) {
            // Handle property change event from ViewModel
            List<Point> path = (List<Point>) evt.getNewValue();
            updatePath(path);
        }
    }

    public String getViewName() {
        return "inputRoomsView";
    }
}
