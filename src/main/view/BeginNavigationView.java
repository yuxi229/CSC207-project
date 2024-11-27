package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;

public class BeginNavigationView {
    private final JButton beginNavigationButton;

    public BeginNavigationView(InputRoomsView parentView, HashMap<String, List<Point>> fixedRoute) {
        beginNavigationButton = new JButton("Begin Navigation");
        beginNavigationButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 14)); // Smaller, cuter font
        beginNavigationButton.setFocusPainted(false);
        beginNavigationButton.setBackground(new Color(48, 63, 159)); // Matches the header blue
        beginNavigationButton.setForeground(Color.WHITE); // White text for contrast
        beginNavigationButton.setOpaque(true); // Ensure background color is visible
        beginNavigationButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(48, 63, 159), 1),
                BorderFactory.createEmptyBorder(15, 30, 15, 30))); // Larger padding for size
        beginNavigationButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect for the button
        beginNavigationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                beginNavigationButton.setBackground(new Color(33, 50, 100)); // Slightly darker blue on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                beginNavigationButton.setBackground(new Color(48, 63, 159)); // Original blue
            }
        });

        // Add action listener
        beginNavigationButton.addActionListener(e -> {
            String depart = parentView.getDepartureRoom();
            String dest = parentView.getDestinationRoom();
            List<Point> path = fixedRoute.get(depart + "-" + dest);
            parentView.updatePath(path);
        });
    }

    public JButton getButton() {
        return beginNavigationButton;
    }
}
