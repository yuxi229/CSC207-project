package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * A view component for selecting and displaying blueprints.
 * Provides a dropdown menu for blueprint selection and buttons for navigation and switching blueprints.
 */
public class BlueprintSelectionView extends JPanel {
    private final JLabel blueprintLabel;

    public BlueprintSelectionView(List<String> blueprints, Runnable onGoBack, Runnable onSwitchBlueprint) {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        // Dropdown for blueprints
        final JComboBox<String> blueprintDropdown = new JComboBox<>(blueprints.toArray(new String[0]));
        blueprintDropdown.addActionListener(actionEvent
                -> updateBlueprint((String) blueprintDropdown.getSelectedItem()));

        // Blueprint display
        blueprintLabel = new JLabel("No blueprint selected", SwingConstants.CENTER);

        // Buttons
        final JButton goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(actionEvent -> onGoBack.run());

        final JButton switchBlueprintButton = new JButton("Switch Blueprint");
        switchBlueprintButton.addActionListener(actionEvent -> onSwitchBlueprint.run());

        final JPanel buttonPanel = new JPanel();
        buttonPanel.add(goBackButton);
        buttonPanel.add(switchBlueprintButton);

        this.add(blueprintDropdown, BorderLayout.NORTH);
        this.add(blueprintLabel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updateBlueprint(String blueprintPath) {
        blueprintLabel.setText("Displaying: " + blueprintPath);
        // Update actual image if needed
    }

    /**
     * Updates the blueprint image displayed with a new value.
     * @param newValue the new blueprint image value to display.
     */
    public void updateBlueprintImage(String newValue) {
    }
}
