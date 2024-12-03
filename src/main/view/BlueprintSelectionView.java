package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.function.Consumer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import interface_adapter.BlueprintViewModel;

/**
 * A view component for selecting and displaying blueprints.
 */
public class BlueprintSelectionView extends JPanel {
    private final JLabel blueprintLabel;

    public BlueprintSelectionView(BlueprintViewModel viewModel, Runnable onGoBack,
                                  Consumer<String> onBlueprintSelected) {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        // Dropdown for blueprints
        final JComboBox<String> blueprintDropdown = new JComboBox<>();
        viewModel.addPropertyChangeListener(evt -> {
            if ("blueprintList".equals(evt.getPropertyName())) {
                blueprintDropdown.setModel(new DefaultComboBoxModel<>((String[]) evt.getNewValue()));
            }
        });
        blueprintDropdown.addActionListener(actionEvent ->
                onBlueprintSelected.accept((String) blueprintDropdown.getSelectedItem()));

        // Blueprint display
        blueprintLabel = new JLabel("No blueprint selected", SwingConstants.CENTER);

        // Buttons
        final JButton goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(actionEvent -> onGoBack.run());

        final JPanel buttonPanel = new JPanel();
        buttonPanel.add(goBackButton);

        this.add(blueprintDropdown, BorderLayout.NORTH);
        this.add(blueprintLabel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
}
