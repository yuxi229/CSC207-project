package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * A view component for the "Begin Navigation" button, including styling and hover effects.
 */
public class BeginNavigationView {
    public static final Color TORY_BLUE = new Color(48, 63, 159);
    public static final Color BLUE = new Color(33, 50, 100);
    public static final int FONT_SIZE = 14;
    public static final int BORDER_SIZE = 30;
    public static final int TOP = 15;
    public static final int BOTTOM = 15;
    private final JButton beginNavigationButton;

    public BeginNavigationView(Runnable onNavigateAction) {
        beginNavigationButton = new JButton("Begin Navigation");
        // Font styling
        beginNavigationButton.setFont(new Font("Comic Sans MS", Font.PLAIN, FONT_SIZE));
        beginNavigationButton.setFocusPainted(false);
        // Blue background
        beginNavigationButton.setBackground(TORY_BLUE);
        // White text
        beginNavigationButton.setForeground(Color.WHITE);
        // Ensure background color is visible
        beginNavigationButton.setOpaque(true);
        beginNavigationButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(TORY_BLUE, 1),
                // Padding
                BorderFactory.createEmptyBorder(TOP, BORDER_SIZE, BOTTOM, BORDER_SIZE)));
        beginNavigationButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        beginNavigationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Hover color
                beginNavigationButton.setBackground(BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Original color
                beginNavigationButton.setBackground(TORY_BLUE);
            }
        });

        // Add action listener
        beginNavigationButton.addActionListener(actionEvent -> {
            if (onNavigateAction != null) {
                onNavigateAction.run();
            }
        });
    }

    public JButton getButton() {
        return beginNavigationButton;
    }
}
