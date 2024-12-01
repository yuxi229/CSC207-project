package view;

import interface_adapter.inputrooms.InputRoomsViewModel;
import interface_adapter.inputrooms.InputRoomsState;
import interface_adapter.inputrooms.InputRoomsController;
import interface_adapter.inputrooms.InputRoomsPresenter;
import interface_adapter.beginnavigation.BeginNavigationViewModel;
import interface_adapter.beginnavigation.BeginNavigationState;
import use_case.navigation.NavigationOutputData;
import use_case.navigation.maplocation.MapLocation;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InputRoomsView extends JPanel implements PropertyChangeListener {
    private final InputRoomsViewModel inputRoomsViewModel;
    private final JTextField departureRoomField = new JTextField(15);
    private final JTextField destinationRoomField = new JTextField(15);
    private final MapPanel mapPanel;
    private BeginNavigationView beginNavigationView;
    private final TextPromptPanel textPromptPanel;
    private final InputRoomsPresenter presenter;
    private final InputRoomsController controller;

    public InputRoomsView(InputRoomsViewModel inputRoomsViewModel, TextPromptPanel textPromptPanel, InputRoomsPresenter presenter, InputRoomsController controller) {
        this.inputRoomsViewModel = inputRoomsViewModel;
        this.textPromptPanel = textPromptPanel;
        this.presenter = presenter;
        this.controller = controller;
        inputRoomsViewModel.addPropertyChangeListener(this);

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

    // Method triggered by the "Begin Navigation" button
    private void onBeginNavigation() {
        // Retrieve room inputs from text fields
        String departureRoom = departureRoomField.getText();
        String destinationRoom = destinationRoomField.getText();

        // Pass these inputs to the controller to process navigation
        controller.execute(departureRoom, destinationRoom);
    }


    // Property change listener to react to updates in the InputRoomsViewModel
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            InputRoomsState state = (InputRoomsState) evt.getNewValue();

            // Update text fields
            departureRoomField.setText(state.getDepartureRoomCode());
            destinationRoomField.setText(state.getDestinationRoomCode());

            // Handle errors (if any)
            if (state.getDepartureRoomCodeError() != null) {
                departureRoomField.setForeground(Color.RED);
                departureRoomField.setToolTipText(state.getDepartureRoomCodeError());
            } else {
                departureRoomField.setForeground(Color.BLACK);
                departureRoomField.setToolTipText(null);
            }

            if (state.getDestinationRoomCodeError() != null) {
                destinationRoomField.setForeground(Color.RED);
                destinationRoomField.setToolTipText(state.getDestinationRoomCodeError());
            } else {
                destinationRoomField.setForeground(Color.BLACK);
                destinationRoomField.setToolTipText(null);
            }

            // Update the map if a path is available
            if (state.getPath() != null && !state.getPath().isEmpty()) {
                updateMapWithPath(state.getPath());
            }
        }
    }

    private void updateMapWithPath(List<Point> path) {
        mapPanel.setPath(path); // Directly pass the List<Point>
        mapPanel.repaint(); // Refresh the map panel
    }
    }
