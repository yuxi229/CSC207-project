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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interface_adapter.inputrooms.InputRoomsController;
import interface_adapter.inputrooms.NavigationState;
import interface_adapter.inputrooms.NavigationViewModel;

/**
 * A view for inputting room navigation details and displaying the map.
 */
public class InputRoomsView extends JPanel implements PropertyChangeListener {

    // Colours and Fonts
    public static final Color WHITE = new Color(245, 245, 245);
    public static final Color DARKER_GREY = new Color(200, 200, 200);
    public static final Color SOFT_BLUE = new Color(48, 63, 159);
    public static final Font HEADER_STYLE = new Font("Arial", Font.BOLD, 20);
    public static final Color LIGHT_GREY = new Color(240, 240, 240);
    public static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 14);
    public static final Color GREY = new Color(150, 150, 150);

    // File Paths
    public static final String IMAGE_PATH = "floor1.jpg";

    // title
    public static final String HEADER_STRING = "UofT Indoor Navigation";

    // Constants for Dimensions
    public static final int FREE_VIEW_HEIGHT = 1100;
    public static final int FREE_VIEW_WIDTH = 1350;
    public static final int LEFT_PANEL_WIDTH = 300;
    public static final int FIELD_PADDING = 20;
    public static final int MAP_PANEL_TITLE_PADDING = 10;
    public static final int TEXT_PROMPT_WIDTH = 300;
    public static final int TEXT_PROMPT_HEIGHT = 100;
    public static final int FIELD_HEIGHT = 30;
    public static final int FIELD_WIDTH = 400;
    public static final int BORDER_WIDTH = 5;

    // Instance variables
    private final NavigationViewModel navigationViewModel;
    private final JTextField departureRoomField = new JTextField(15);
    private final JTextField destinationRoomField = new JTextField(15);
    private final MapPanel mapPanel = new MapPanel(IMAGE_PATH);
    private final BeginNavigationView beginNavigationView = new BeginNavigationView(this::onBeginNavigation);
    private final TextPromptPanel textPromptPanel;
    private final InputRoomsController controller;

    public InputRoomsView(NavigationViewModel navigationViewModel, TextPromptPanel textPromptPanel,
                          InputRoomsController controller) {
        this.navigationViewModel = navigationViewModel;
        this.textPromptPanel = textPromptPanel;
        this.controller = controller;
        navigationViewModel.addPropertyChangeListener(this);
        makeView();
    }

    private void makeView() {
        // Layout and design improvements
        this.setLayout(new BorderLayout());

        // Soft light grey
        this.setBackground(WHITE);

        // Header Panel
        final JPanel headerPanel = createHeaderPanel();
        this.add(headerPanel, BorderLayout.NORTH);

        // Left panel for user input
        final JPanel leftPanel = createLeftPanel();
        this.add(leftPanel, BorderLayout.WEST);

        // Map panel for rendering routes
        mapPanel.setBorder(BorderFactory.createLineBorder(DARKER_GREY));
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
        leftPanel.setPreferredSize(new Dimension(LEFT_PANEL_WIDTH, 0));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);
        // Add padding
        leftPanel.setBorder(BorderFactory.createEmptyBorder(MAP_PANEL_TITLE_PADDING, FIELD_PADDING,
                MAP_PANEL_TITLE_PADDING, FIELD_PADDING));

        final JLabel title = new JLabel("Where To?");
        title.setFont(HEADER_STYLE);
        title.setForeground(SOFT_BLUE);

        final JLabel departureLabel = new JLabel("Departure Room");
        // Apply styling to the input field
        styleTextField(departureRoomField);

        final JLabel destinationLabel = new JLabel("Destination Room");
        // Apply styling to the input field
        styleTextField(destinationRoomField);

        // Set a preferred size for the TextPromptPanel
        // Adjust height as needed
        textPromptPanel.setPreferredSize(new Dimension(TEXT_PROMPT_WIDTH, TEXT_PROMPT_HEIGHT));
        // Prevent expansion
        textPromptPanel.setMaximumSize(new Dimension(TEXT_PROMPT_WIDTH, TEXT_PROMPT_HEIGHT));

        // Add "View Freely" button with right alignment
        final JButton viewFreelyButton = createViewFreelyScreen();

        final JPanel viewFreelyPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        viewFreelyPanel.setBackground(Color.WHITE);
        viewFreelyPanel.add(viewFreelyButton);

        // Add components to the left panel
        leftPanel.add(title);
        leftPanel.add(Box.createVerticalStrut(MAP_PANEL_TITLE_PADDING));
        leftPanel.add(departureLabel);
        leftPanel.add(departureRoomField);
        leftPanel.add(Box.createVerticalStrut(MAP_PANEL_TITLE_PADDING));
        leftPanel.add(destinationLabel);
        leftPanel.add(destinationRoomField);
        leftPanel.add(Box.createVerticalStrut(FIELD_PADDING));
        // Add the button
        leftPanel.add(beginNavigationView.getButton());
        leftPanel.add(Box.createVerticalStrut(FIELD_PADDING));
        // Label for the TextPromptPanel
        leftPanel.add(new JLabel("Text Prompt"));
        leftPanel.add(Box.createVerticalStrut(BORDER_WIDTH));
        // Add the TextPromptPanel
        leftPanel.add(textPromptPanel);
        leftPanel.add(Box.createVerticalStrut(FIELD_PADDING));
        // Add the "View Freely" button panel
        leftPanel.add(viewFreelyPanel);

        return leftPanel;
    }

    private JButton createViewFreelyScreen() {
        final JButton viewFreelyButton = new JButton("View Freely");
        viewFreelyButton.setFont(DEFAULT_FONT);
        viewFreelyButton.setBackground(WHITE);
        viewFreelyButton.setForeground(SOFT_BLUE);
        viewFreelyButton.addActionListener(event -> openFreeViewScreen());
        return viewFreelyButton;
    }

    private JPanel createMapTitlePanel() {
        final JPanel mapTitlePanel = new JPanel();
        // Light grey background
        mapTitlePanel.setBackground(WHITE);
        // Use BorderLayout to align content
        mapTitlePanel.setLayout(new BorderLayout());
        // Add padding
        mapTitlePanel.setBorder(BorderFactory.createEmptyBorder(MAP_PANEL_TITLE_PADDING, FIELD_PADDING,
                MAP_PANEL_TITLE_PADDING, FIELD_PADDING));

        // Label for "Displaying"
        final JLabel displayingLabel = new JLabel("Displaying ");
        displayingLabel.setFont(DEFAULT_FONT);
        displayingLabel.setForeground(GREY);

        // Label for "Bahen Centre"
        final JLabel bahenLabel = new JLabel("Bahen Centre");
        bahenLabel.setFont(DEFAULT_FONT);
        bahenLabel.setForeground(SOFT_BLUE);

        // Use a FlowLayout to group "Displaying" and "Bahen Centre"
        // Align fully left, no gaps
        final JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        // Match background color
        textPanel.setBackground(WHITE);
        textPanel.add(displayingLabel);
        textPanel.add(bahenLabel);

        // Add the textPanel to the left side of mapTitlePanel
        mapTitlePanel.add(textPanel, BorderLayout.WEST);

        return mapTitlePanel;
    }

    private void styleTextField(JTextField textField) {
        textField.setFont(DEFAULT_FONT);
        textField.setForeground(Color.BLACK);
        // Light grey background
        textField.setBackground(LIGHT_GREY);
        textField.setBorder(BorderFactory.createCompoundBorder(
                // Light grey border
                BorderFactory.createLineBorder(DARKER_GREY),
                // Padding inside the field
                BorderFactory.createEmptyBorder(BORDER_WIDTH, MAP_PANEL_TITLE_PADDING,
                        BORDER_WIDTH, MAP_PANEL_TITLE_PADDING)));
        // Fixed size
        textField.setMaximumSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
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
            final NavigationState state = (NavigationState) evt.getNewValue();

            // Update text fields
            departureRoomField.setText(state.getDepartureRoomCode());
            destinationRoomField.setText(state.getDestinationRoomCode());

            String departureError = state.getDepartureRoomCodeError();
            String destinationError = state.getDestinationRoomCodeError();

            // Handle errors (if any)
            if (state.getDepartureRoomCodeError() != null) {
                JOptionPane.showMessageDialog(null, state.getDepartureRoomCodeError(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if (state.getDestinationRoomCodeError() != null) {
                JOptionPane.showMessageDialog(null, state.getDestinationRoomCodeError(), "Error", JOptionPane.ERROR_MESSAGE);
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

    private void showErrorPopup(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void openFreeViewScreen() {
        final JFrame freeViewFrame = new JFrame("Free View Mode");
        freeViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        freeViewFrame.setSize(FREE_VIEW_WIDTH, FREE_VIEW_HEIGHT);

        // Panel for displaying blueprints
        final JPanel blueprintPanel = new JPanel(new BorderLayout());

        // Dropdown for selecting blueprints
        final String[] blueprints = {"floor1.jpg", "floor2.jpg"};
        final JComboBox<String> blueprintDropdown = new JComboBox<>(blueprints);

        // Map panel to show the selected blueprint
        final MapPanel blueprintMapPanel = new MapPanel((String) blueprintDropdown.getSelectedItem());

        // Update the map when a new blueprint is selected
        blueprintDropdown.addActionListener(actionEvent -> {
            final String selectedBlueprint = (String) blueprintDropdown.getSelectedItem();
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
