package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BeginNavigationView {
    private final JButton beginNavigationButton;

    public BeginNavigationView(Runnable onNavigateAction) {
        beginNavigationButton = new JButton("Begin Navigation");
        beginNavigationButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 14)); // Font styling
        beginNavigationButton.setFocusPainted(false);
        beginNavigationButton.setBackground(new Color(48, 63, 159)); // Blue background
        beginNavigationButton.setForeground(Color.WHITE); // White text
        beginNavigationButton.setOpaque(true); // Ensure background color is visible
        beginNavigationButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(48, 63, 159), 1),
                BorderFactory.createEmptyBorder(15, 30, 15, 30))); // Padding
        beginNavigationButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        beginNavigationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                beginNavigationButton.setBackground(new Color(33, 50, 100)); // Hover color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                beginNavigationButton.setBackground(new Color(48, 63, 159)); // Original color
            }
        });

        // Add action listener
        beginNavigationButton.addActionListener(e -> {
            if (onNavigateAction != null) {
                onNavigateAction.run();
            }
        });
    }

    public JButton getButton() {
        return beginNavigationButton;
    }
}
