package view;

import javax.swing.*;
import java.awt.*;

public class TextPromptPanel extends JPanel {
    private final JTextArea promptTextArea;

    public TextPromptPanel() {
        // Set layout and styling for the panel
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(48, 63, 159), 2), // Blue border
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Padding
        ));

        // Create and configure the text area
        promptTextArea = new JTextArea();
        promptTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        promptTextArea.setForeground(Color.BLACK);
        promptTextArea.setLineWrap(true);
        promptTextArea.setWrapStyleWord(true);
        promptTextArea.setEditable(false); // Make text area read-only
        promptTextArea.setBackground(new Color(240, 240, 240)); // Light grey background

        // Add the text area to a scroll pane
        JScrollPane scrollPane = new JScrollPane(promptTextArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove default scroll pane border
        this.add(scrollPane, BorderLayout.CENTER);

        // Set the preferred size for the panel
        this.setPreferredSize(new Dimension(300, 100)); // Reasonable width and height for display
    }

    /**
     * Updates the text displayed in the prompt area.
     *
     * @param prompt The text to be displayed in the prompt area.
     */
    public void updatePrompt(String prompt) {
        promptTextArea.setText(prompt);
    }
}
