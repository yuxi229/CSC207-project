package view;

import javax.swing.*;
import java.awt.*;

public class TextPromptPanel extends JPanel {
    private final JTextArea promptTextArea;

    public TextPromptPanel() {
        // Set layout and background
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(48, 63, 159), 2), // Blue border
                BorderFactory.createEmptyBorder(1, 0, 1, 1) // Padding
        ));

        // Create and configure the text area
        promptTextArea = new JTextArea();
        promptTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        promptTextArea.setForeground(Color.BLACK);
        promptTextArea.setLineWrap(true);
        promptTextArea.setWrapStyleWord(true);
        promptTextArea.setEditable(false); // Text area is read-only
        promptTextArea.setBackground(new Color(240, 240, 240)); // Light grey background

        // Add the text area to the panel
        JScrollPane scrollPane = new JScrollPane(promptTextArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove default border
        this.add(scrollPane, BorderLayout.CENTER);

        // Set the preferred size for the panel
        this.setPreferredSize(new Dimension(5, 1)); // Adjust width and height as needed
    }

    public void updatePrompt(String prompt) {
        promptTextArea.setText(prompt);
    }
}