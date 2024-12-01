package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.*;

import interface_adapter.inputrooms.InputRoomsController;
import interface_adapter.inputrooms.InputRoomsState;
import interface_adapter.inputrooms.InputRoomsViewModel;

/**
 * TODO: Add javadoc.
 * TODO: Fix all the magic numbers!
 */
public class InputRoomsView extends JPanel implements PropertyChangeListener {
    // Constants
    public static final Color SOFT_LIGHT_GREY = new Color(245, 245, 245);
    // TODO: Rename BORDER_COLOR to the color
    public static final Color BORDER_COLOR = new Color(200, 200, 200);
    public static final String IMAGE_PATH = "floor1.jpg";
    public static final Color SOFT_BLUE = new Color(48, 63, 159);
    public static final Font HEADER_STYLE = new Font("Arial", Font.BOLD, 20);
    public static final String HEADER_STRING = "UofT Indoor Navigation";
    public static final Color LIGHT_GREY = new Color(240, 240, 240);

    // Instance variables
    private final InputRoomsViewModel inputRoomsViewModel;
    private final JTextField departureRoomField = new JTextField(15);
    private final JTextField destinationRoomField = new JTextField(15);
    private final MapPanel mapPanel = new MapPanel(IMAGE_PATH);
    private final BeginNavigationView beginNavigationView = new BeginNavigationView(this::onBeginNavigation);
    private final TextPromptPanel textPromptPanel;
    private final InputRoomsController controller;

    public InputRoomsView(InputRoomsViewModel inputRoomsViewModel, TextPromptPanel textPromptPanel,
                          InputRoomsController controller) {
        this.inputRoomsViewModel = inputRoomsViewModel;
        this.textPromptPanel = textPromptPanel;
        this.controller = controller;
        inputRoomsViewModel.addPropertyChangeListener(this);
        makeView();
    }

    private void makeView() {
        // Layout and design improvements
        this.setLayout(new BorderLayout());

        // Soft light grey
        this.setBackground(SOFT_LIGHT_GREY);

        // Header Panel
        final JPanel headerPanel = createHeaderPanel();
        this.add(headerPanel, BorderLayout.NORTH);

        // Left panel for user input
        final JPanel leftPanel = createLeftPanel();
        this.add(leftPanel, BorderLayout.WEST);

        // Map panel for rendering routes
        mapPanel.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
        mapPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        mapPanel.setLayout(new BorderLayout());

        // Add "Displaying Bahen Centre" title to MapPanel
        final JPanel mapTitlePanel = createMapTitlePanel();
        mapPanel.add(mapTitlePanel, BorderLayout.NORTH);

        this.add(mapPanel, BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel() {
        final JPanel headerPanel = new JPanel();
        // Soft blue
        headerPanel.setBackground(SOFT_BLUE);
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        final JLabel headerLabel = new JLabel(HEADER_STRING);
        headerLabel.setFont(HEADER_STYLE);
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        return headerPanel;
    }

    private JPanel createLeftPanel() {
        final JPanel leftPanel = new JPanel();
        // Restrict width
        leftPanel.setPreferredSize(new Dimension(300, 0));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding

        final JLabel title = new JLabel("Where To?");
        title.setFont(HEADER_STYLE);
        title.setForeground(SOFT_BLUE);

        final JLabel departureLabel = new JLabel("Departure Room");
        // Apply styling to the input field
        styleTextField(departureRoomField);
        styleTextField(departureRoomField);

        final JLabel destinationLabel = new JLabel("Destination Room");
        // Apply styling to the input field
        styleTextField(destinationRoomField);
        styleTextField(destinationRoomField);

        // Set a preferred size for the TextPromptPanel
        // Adjust height as needed
        textPromptPanel.setPreferredSize(new Dimension(500, 100));
        // Prevent expansion
        textPromptPanel.setMaximumSize(new Dimension(500, 100));
        textPromptPanel.setPreferredSize(new Dimension(500, 100));
        textPromptPanel.setMaximumSize(new Dimension(500, 100));

        // Add "View Freely" button with right alignment
        JButton viewFreelyButton = new JButton("View Freely");
        viewFreelyButton.setFont(new Font("Arial", Font.BOLD, 14));
        viewFreelyButton.setBackground(new Color(48, 63, 159));
        viewFreelyButton.setForeground(new Color(48, 63, 159));
        viewFreelyButton.addActionListener(e -> openFreeViewScreen());

        JPanel viewFreelyPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        viewFreelyPanel.setBackground(Color.WHITE);
        viewFreelyPanel.add(viewFreelyButton);


        // Add components to the left panel
        leftPanel.add(title);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(departureLabel);
        leftPanel.add(departureRoomField);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(destinationLabel);
        leftPanel.add(destinationRoomField);
        leftPanel.add(Box.createVerticalStrut(20));
        // Add the button
        leftPanel.add(beginNavigationView.getButton());
        leftPanel.add(beginNavigationView.getButton());
        leftPanel.add(Box.createVerticalStrut(20));
        // Label for the TextPromptPanel
        leftPanel.add(new JLabel("Text Prompt"));
        leftPanel.add(Box.createVerticalStrut(5));
        // Add the TextPromptPanel
        leftPanel.add(textPromptPanel);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(viewFreelyPanel); // Add the "View Freely" button panel

        return leftPanel;
    }

    private JPanel createMapTitlePanel() {
        final JPanel mapTitlePanel = new JPanel();
        // Light grey background
        mapTitlePanel.setBackground(SOFT_LIGHT_GREY);
        // Use BorderLayout to align content
        mapTitlePanel.setLayout(new BorderLayout());
        // Add padding
        mapTitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Label for "Displaying"
        final JLabel displayingLabel = new JLabel("Displaying ");
        displayingLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        displayingLabel.setForeground(new Color(150, 150, 150));

        // Label for "Bahen Centre"
        final JLabel bahenLabel = new JLabel("Bahen Centre");
        bahenLabel.setFont(new Font("Arial", Font.BOLD, 14));
        bahenLabel.setForeground(SOFT_BLUE);

        // Use a FlowLayout to group "Displaying" and "Bahen Centre"
        // Align fully left, no gaps
        final JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        // Match background color
        textPanel.setBackground(SOFT_LIGHT_GREY);
        textPanel.add(displayingLabel);
        textPanel.add(bahenLabel);

        // Add the textPanel to the left side of mapTitlePanel
        mapTitlePanel.add(textPanel, BorderLayout.WEST);

        return mapTitlePanel;
    }

    private void styleTextField(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setForeground(Color.BLACK);
        // Light grey background
        textField.setBackground(LIGHT_GREY);
        textField.setBorder(BorderFactory.createCompoundBorder(
                // Light grey border
                BorderFactory.createLineBorder(BORDER_COLOR),
                // Padding inside the field
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        // Fixed size
        textField.setMaximumSize(new Dimension(400, 30));
    }

    // Method triggered by the "Begin Navigation" button
    private void onBeginNavigation() {
        // Retrieve room inputs from text fields
        final String departureRoom = departureRoomField.getText();
        final String destinationRoom = destinationRoomField.getText();

        // Pass these inputs to the controller to process navigation
        controller.execute(departureRoom, destinationRoom);
    }

    // Property change listener to react to updates in the InputRoomsViewModel
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final InputRoomsState state = (InputRoomsState) evt.getNewValue();

            // Update text fields
            departureRoomField.setText(state.getDepartureRoomCode());
            destinationRoomField.setText(state.getDestinationRoomCode());

            // Handle errors (if any)
            if (state.getDepartureRoomCodeError() != null) {
                departureRoomField.setForeground(Color.RED);
                departureRoomField.setToolTipText(state.getDepartureRoomCodeError());
            }
            else {
                departureRoomField.setForeground(Color.BLACK);
                departureRoomField.setToolTipText(null);
            }

            if (state.getDestinationRoomCodeError() != null) {
                destinationRoomField.setForeground(Color.RED);
                destinationRoomField.setToolTipText(state.getDestinationRoomCodeError());
            }
            else {
                destinationRoomField.setForeground(Color.BLACK);
                destinationRoomField.setToolTipText(null);
            }

            // Update the map if a path is available
            if (state.getPath() != null && !state.getPath().isEmpty()) {
                updateMapWithPath(state.getPath());
                // TODO: Update textPromptPanel with output from instructions use case (Likely requires new presenter)
                textPromptPanel.updatePrompt(state.getPath().toString());
            }
        }
    }

    private void openFreeViewScreen() {
        JFrame freeViewFrame = new JFrame("Free View Mode");
        freeViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        freeViewFrame.setSize(1350, 1100);

        // Panel for displaying blueprints
        JPanel blueprintPanel = new JPanel(new BorderLayout());

        // Dropdown for selecting blueprints
        String[] blueprints = {"floor1.jpg", "floor2.jpg"};
        JComboBox<String> blueprintDropdown = new JComboBox<>(blueprints);

        // Map panel to show the selected blueprint
        MapPanel blueprintMapPanel = new MapPanel((String) blueprintDropdown.getSelectedItem());

        // Update the map when a new blueprint is selected
        blueprintDropdown.addActionListener(e -> {
            String selectedBlueprint = (String) blueprintDropdown.getSelectedItem();
            blueprintMapPanel.updateBlueprint(selectedBlueprint);
        });

        // Add components to the blueprint panel
        blueprintPanel.add(blueprintDropdown, BorderLayout.NORTH);
        blueprintPanel.add(blueprintMapPanel, BorderLayout.CENTER);

        freeViewFrame.add(blueprintPanel);
        freeViewFrame.setVisible(true);
    }

    private void updateMapWithPath(List<Point> path) {
        // Directly pass the List<Point>
        mapPanel.setPath(path);
        // Refresh the map panel
        mapPanel.repaint();
    }
}
