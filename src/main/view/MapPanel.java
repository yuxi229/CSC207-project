package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MapPanel extends JPanel {
    private Image mapImage;
    private List<Point> path;

    public MapPanel(String imagePath) {
        // Load the image from the resources folder
        try {
            mapImage = new ImageIcon(getClass().getClassLoader().getResource(imagePath)).getImage();
            setPreferredSize(new Dimension(800, 600));
        } catch (NullPointerException e) {
            System.err.println("Image not found at path: " + imagePath);
            mapImage = null; // Fallback to null if image is not found
        }
    }

    public void setPath(List<Point> path) {
        this.path = path;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (mapImage != null) {
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            int imageWidth = mapImage.getWidth(this);
            int imageHeight = mapImage.getHeight(this);

            // Calculate scaling factors to maintain aspect ratio
            double widthRatio = (double) panelWidth / imageWidth;
            double heightRatio = (double) panelHeight / imageHeight;
            double scale = Math.min(widthRatio, heightRatio); // Keep proportions

            int scaledWidth = (int) (imageWidth * scale);
            int scaledHeight = (int) (imageHeight * scale);

            // Center the image
            int x = (panelWidth - scaledWidth) / 2;
            int y = (panelHeight - scaledHeight) / 2;

            // Draw the image
            g2d.drawImage(mapImage, x, y, scaledWidth, scaledHeight, this);

            // Draw the path
            if (path != null) {
                g2d.setColor(Color.BLUE);
                g2d.setStroke(new BasicStroke(3));
                for (int i = 0; i < path.size() - 1; i++) {
                    Point p1 = scalePoint(path.get(i), imageWidth, imageHeight, scaledWidth, scaledHeight, x, y);
                    Point p2 = scalePoint(path.get(i + 1), imageWidth, imageHeight, scaledWidth, scaledHeight, x, y);
                    g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
        }
    }

    /**
     * Scales a point based on the image's original and scaled dimensions.
     */
    private Point scalePoint(Point original, int originalWidth, int originalHeight,
                             int scaledWidth, int scaledHeight, int offsetX, int offsetY) {
        double xRatio = (double) scaledWidth / originalWidth;
        double yRatio = (double) scaledHeight / originalHeight;

        int scaledX = (int) (original.x * xRatio) + offsetX;
        int scaledY = (int) (original.y * yRatio) + offsetY;

        return new Point(scaledX, scaledY);
    }
}
