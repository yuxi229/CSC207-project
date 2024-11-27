package view;

import interface_adapter.inputrooms.InputRoomsViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;

public class InputRoomsView extends JPanel implements PropertyChangeListener {
    private final InputRoomsViewModel inputRoomsViewModel;
    private final JTextField departureRoomField = new JTextField(15);
    private final JTextField destinationRoomField = new JTextField(15);
    private final MapPanel mapPanel;
    private final BeginNavigationView beginNavigationView;
    private final HashMap<String, List<Point>> fixedRoute = new HashMap<>();
    private final TextPromptPanel textPromptPanel;

    public InputRoomsView(InputRoomsViewModel inputRoomsViewModel, TextPromptPanel textPromptPanel) {
        this.inputRoomsViewModel = inputRoomsViewModel;
        this.textPromptPanel = textPromptPanel;

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
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(48, 63, 159)); // Soft blue
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel headerLabel = new JLabel("UofT Indoor Navigation");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        this.add(headerPanel, BorderLayout.NORTH);

        // Left panel for user input
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Adjusted spacing for aesthetics

        JLabel title = new JLabel("Where To?");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(48, 63, 159));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel departureLabel = new JLabel("Departure Room");
        departureLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        departureRoomField.setMaximumSize(new Dimension(400, 30)); // Adjusted size
        departureRoomField.setBackground(new Color(240, 240, 240));
        departureRoomField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel destinationLabel = new JLabel("Destination Room");
        destinationLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        destinationRoomField.setMaximumSize(new Dimension(400, 30)); // Adjusted size
        destinationRoomField.setBackground(new Color(240, 240, 240));
        destinationRoomField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Initialize BeginNavigationView and pass fixedRoute
        beginNavigationView = new BeginNavigationView(this, fixedRoute);

        // Text Prompt Label
        JLabel textPromptLabel = new JLabel("Text Prompt");
        textPromptLabel.setFont(new Font("Arial", Font.BOLD, 16));
        textPromptLabel.setForeground(new Color(48, 63, 159));
        textPromptLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Assemble left panel
        leftPanel.add(title);
        leftPanel.add(Box.createVerticalStrut(10)); // Reduced gap
        leftPanel.add(departureLabel);
        leftPanel.add(departureRoomField);
        leftPanel.add(Box.createVerticalStrut(10)); // Reduced gap
        leftPanel.add(destinationLabel);
        leftPanel.add(destinationRoomField);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(beginNavigationView.getButton()); // Add the button from BeginNavigationView
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(textPromptLabel); // Add the label for text prompt
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(textPromptPanel); // Add the TextPromptPanel to the left panel

        // Map panel for the right side
        mapPanel = new MapPanel("map.jpg");
        mapPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        mapPanel.setLayout(new BorderLayout());

        // Map title bar
        JPanel mapTitlePanel = new JPanel();
        mapTitlePanel.setBackground(new Color(245, 245, 245)); // Light grey
        mapTitlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        mapTitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel displayingLabel = new JLabel("Displaying ");
        displayingLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        displayingLabel.setForeground(new Color(150, 150, 150));

        JLabel bahenLabel = new JLabel("Bahen Centre");
        bahenLabel.setFont(new Font("Arial", Font.BOLD, 14));
        bahenLabel.setForeground(new Color(48, 63, 159));

        mapTitlePanel.add(displayingLabel);
        mapTitlePanel.add(bahenLabel);

        mapPanel.add(mapTitlePanel, BorderLayout.NORTH);

        // Assemble the main layout
        this.add(leftPanel, BorderLayout.WEST);
        this.add(mapPanel, BorderLayout.CENTER);
    }

    public String getDepartureRoom() {
        return departureRoomField.getText();
    }

    public String getDestinationRoom() {
        return destinationRoomField.getText();
    }

    public void updatePath(List<Point> path) {
        mapPanel.setPath(path);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle property change events from InputRoomsViewModel
    }

    public String getViewName() {
        return "inputRoomsView";
    }
}