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
import org.jetbrains.annotations.NotNull;

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
    public static final String F1_IMAGE_PATH = "floor1.jpg";
    public static final String F2_IMAGE_PATH = "floor2.jpg";
    public static final String F3_IMAGE_PATH = "floor3.jpg";
    public static final String F4_IMAGE_PATH = "floor4.jpg";
    public static final String F5_IMAGE_PATH = "floor5.jpg";
    public static final List<String> IMAGE_PATHS = List.of(F1_IMAGE_PATH, F2_IMAGE_PATH, F3_IMAGE_PATH, F4_IMAGE_PATH,
            F5_IMAGE_PATH);

    // title
    public static final String HEADER_STRING = "Indoor Navigation System";

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
    public static final JTextField start_input_field = new JTextField(15);
    public static final JTextField end_input_field =  new JTextField(15);

    private final MapPanel mapPanel = new MapPanel(F1_IMAGE_PATH);
    private final BeginNavigationView beginNavigationView = new BeginNavigationView(this::onBeginNavigation);
    private final TextPromptPanel textPromptPanel;
    private final InputRoomsController controller;
    private final NavigationViewModel navigationViewModel;
    private Integer curMapId = 0;


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
        formatMapPanel();
    }

    private void formatMapPanel() {
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

        final JLabel headerLabel = makeLabel(HEADER_STRING, HEADER_STYLE, Color.WHITE);
        headerPanel.add(headerLabel);

        return headerPanel;
    }

    private JPanel createLeftPanel() {
        final JPanel leftPanel = new JPanel();

        // Format the left panel
        formatLeftPanel(leftPanel);
        // Apply styling to the input field
        styleTextField(start_input_field);
        // Apply styling to the input field
        styleTextField(end_input_field);
        // Format the TextPromptPanel
        formatTextPromptPanel();

        // Add components to the left panel
        addComponents(leftPanel);
        return leftPanel;
    }

    private void addComponents(JPanel leftPanel) {
        leftPanel.add(makeLabel("Where To?", HEADER_STYLE, SOFT_BLUE));
        leftPanel.add(Box.createVerticalStrut(MAP_PANEL_TITLE_PADDING));
        leftPanel.add(new JLabel("Departure Room"));
        leftPanel.add(start_input_field);
        leftPanel.add(Box.createVerticalStrut(MAP_PANEL_TITLE_PADDING));
        leftPanel.add(new JLabel("Destination Room"));
        leftPanel.add(end_input_field);
        leftPanel.add(Box.createVerticalStrut(FIELD_PADDING));
        // Add begin navigation button
        leftPanel.add(beginNavigationView.getButton());
        leftPanel.add(Box.createVerticalStrut(FIELD_PADDING));
        // Add label for the TextPromptPanel
        leftPanel.add(new JLabel("Text Prompt"));
        leftPanel.add(Box.createVerticalStrut(FIELD_PADDING));
        // Add the TextPromptPanel
        leftPanel.add(textPromptPanel);
        leftPanel.add(Box.createVerticalStrut(FIELD_PADDING));
        // Add the previous and next buttons
        leftPanel.add(makePreviousNextPanel());
        leftPanel.add(Box.createVerticalStrut(FIELD_PADDING));
        // Add the "View Freely" button panel
        leftPanel.add(makeViewFreelyPanel());
    }

    @NotNull
    private JPanel makePreviousNextPanel() {
        final JButton previous = new JButton("Previous");
        previous.addActionListener(event -> showPreviousInstruction());
        final JButton next = new JButton("Next");
        next.addActionListener(event -> showNextInstruction());

        final JPanel previousNextPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        previousNextPanel.setBackground(Color.WHITE);
        previousNextPanel.add(previous);
        previousNextPanel.add(next);
        return previousNextPanel;
    }

    @NotNull
    private JPanel makeViewFreelyPanel() {
        final JButton viewFreelyButton = createViewFreelyScreen();
        final JPanel viewFreelyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        viewFreelyPanel.setBackground(Color.WHITE);
        viewFreelyPanel.add(viewFreelyButton);
        return viewFreelyPanel;
    }

    private void formatTextPromptPanel() {
        // Set a preferred size for the TextPromptPanel
        // Adjust height as needed
        textPromptPanel.setPreferredSize(new Dimension(TEXT_PROMPT_WIDTH, TEXT_PROMPT_HEIGHT));
        // Prevent expansion
        textPromptPanel.setMaximumSize(new Dimension(TEXT_PROMPT_WIDTH, TEXT_PROMPT_HEIGHT));
    }

    private static void formatLeftPanel(JPanel leftPanel) {
        // Restrict width
        leftPanel.setPreferredSize(new Dimension(LEFT_PANEL_WIDTH, FREE_VIEW_HEIGHT));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);
        // Add padding
        leftPanel.setBorder(BorderFactory.createEmptyBorder(MAP_PANEL_TITLE_PADDING, FIELD_PADDING,
                MAP_PANEL_TITLE_PADDING, FIELD_PADDING));
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
        final JLabel displayingLabel = makeLabel("Displaying ", DEFAULT_FONT, GREY);

        // Label for "Bahen Centre"
        final JLabel bahenLabel = makeLabel("Bahen Centre", DEFAULT_FONT, SOFT_BLUE);

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

    @NotNull
    private static JLabel makeLabel(String text, Font name, Color color) {
        final JLabel displayingLabel = new JLabel(text);
        displayingLabel.setFont(name);
        displayingLabel.setForeground(color);
        return displayingLabel;
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
        final String departureRoom = start_input_field.getText();
        final String destinationRoom = end_input_field.getText();

        // Pass these inputs to the controller to process navigation
        controller.execute(departureRoom, destinationRoom);
    }

    // Method triggered by the previous button
    private void showPreviousInstruction() {
        mapPanel.previousInstruction();
        updateFloor();
        mapPanel.repaint();
    }

    // Method triggered by the next button
    private void showNextInstruction() {
        mapPanel.nextInstruction();
        updateFloor();
        mapPanel.repaint();
    }

    private void updateFloor() {
        String imgPath = IMAGE_PATHS.get(mapPanel.getCurrentFloor() - 1);
        mapPanel.updateMap(imgPath);
    }

    // Property change listener to react to updates in the InputRoomsViewModel
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final NavigationState state = (NavigationState) evt.getNewValue();

            // Update text fields
            end_input_field.setText(state.getDepartureRoomCode());
            end_input_field.setText(state.getDestinationRoomCode());

            // Handle errors (if any)
            if (state.getDepartureRoomCodeError() != null) {
                end_input_field.setForeground(Color.RED);
                end_input_field.setToolTipText(state.getDepartureRoomCodeError());
            }
            else {
                end_input_field.setForeground(Color.BLACK);
                end_input_field.setToolTipText(null);
            }

            if (state.getDestinationRoomCodeError() != null) {
                end_input_field.setForeground(Color.RED);
                end_input_field.setToolTipText(state.getDestinationRoomCodeError());
            }
            else {
                end_input_field.setForeground(Color.BLACK);
                end_input_field.setToolTipText(null);
            }

            // Update the map if a path is available
            if (state.getPath() != null && !state.getPath().isEmpty()) {
                // TODO: Update textPromptPanel with output from instructions use case (Likely requires new presenter)
                textPromptPanel.updatePrompt(state.getPath().toString());
                updateMapWithPath(state.getPath(), state.getFloors());
            }
        }
    }

    private void updateMapWithPath(List<Point> path, List<Integer> floors) {
        // Directly pass the List<Point>
        mapPanel.setPath(path, floors);
        // Refresh the map panel
        mapPanel.repaint();
    }

    private void openFreeViewScreen() {
        final JFrame freeViewFrame = new JFrame("Free View Mode");
        freeViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        freeViewFrame.setSize(FREE_VIEW_WIDTH, FREE_VIEW_HEIGHT);

        // Panel for displaying blueprints
        final JPanel blueprintPanel = new JPanel(new BorderLayout());

        // Dropdown for selecting blueprints
        final String[] blueprints = {F1_IMAGE_PATH, F2_IMAGE_PATH};
        final JComboBox<String> blueprintDropdown = new JComboBox<>(blueprints);

        // Map panel to show the selected blueprint
        final MapPanel blueprintMapPanel = new MapPanel((String) blueprintDropdown.getSelectedItem());

        // Update the map when a new blueprint is selected
        blueprintDropdown.addActionListener(actionEvent -> {
            final String selectedBlueprint = (String) blueprintDropdown.getSelectedItem();
            blueprintMapPanel.updateMap(selectedBlueprint);
        });

        // Add components to the blueprint panel
        blueprintPanel.add(blueprintDropdown, BorderLayout.NORTH);
        blueprintPanel.add(blueprintMapPanel, BorderLayout.CENTER);

        freeViewFrame.add(blueprintPanel);
        freeViewFrame.setVisible(true);
    }


}
