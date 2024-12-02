package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Text Prompt Panel.
 */
public class TextPromptPanel extends JPanel {
    public static final Dimension DIMENSION = new Dimension(300, 100);
    public static final Color LIGHT_GREY = new Color(240, 240, 240);
    public static final int FONT_SIZE = 14;
    public static final int BORDER_MARGINS = 5;
    public static final Color TORY_BLUE = new Color(48, 63, 159);
    private final JTextArea promptTextArea;

    public TextPromptPanel() {
        // Set layout and styling for the panel
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createCompoundBorder(
                // Blue border
                BorderFactory.createLineBorder(TORY_BLUE, 2),
                // Padding
                BorderFactory.createEmptyBorder(BORDER_MARGINS, BORDER_MARGINS, BORDER_MARGINS, BORDER_MARGINS)
        ));

        // Create and configure the text area
        promptTextArea = new JTextArea();
        promptTextArea.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE));
        promptTextArea.setForeground(Color.BLACK);
        promptTextArea.setLineWrap(true);
        promptTextArea.setWrapStyleWord(true);
        // Make text area read-only
        promptTextArea.setEditable(false); 
        // Light grey background
        promptTextArea.setBackground(LIGHT_GREY);

        // Add the text area to a scroll pane
        final JScrollPane scrollPane = new JScrollPane(promptTextArea);
        
        // Remove default scroll pane border
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); 
        this.add(scrollPane, BorderLayout.CENTER);

        // Set the preferred size for the panel
        // Reasonable width and height for display
        this.setPreferredSize(DIMENSION); 
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
