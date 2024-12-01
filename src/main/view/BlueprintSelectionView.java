package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BlueprintSelectionView extends JPanel {
    private final JLabel blueprintLabel;

    public BlueprintSelectionView(List<String> blueprints, Runnable onGoBack, Runnable onSwitchBlueprint) {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        // Dropdown for blueprints
        JComboBox<String> blueprintDropdown = new JComboBox<>(blueprints.toArray(new String[0]));
        blueprintDropdown.addActionListener(e -> updateBlueprint((String) blueprintDropdown.getSelectedItem()));

        // Blueprint display
        blueprintLabel = new JLabel("No blueprint selected", SwingConstants.CENTER);

        // Buttons
        JButton goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(e -> onGoBack.run());

        JButton switchBlueprintButton = new JButton("Switch Blueprint");
        switchBlueprintButton.addActionListener(e -> onSwitchBlueprint.run());

        JPanel buttonPanel = new JPanel();
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

    public void updateBlueprintImage(String newValue) {
    }
}
