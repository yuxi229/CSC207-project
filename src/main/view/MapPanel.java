package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

public class MapPanel extends JPanel {
    private Image mapImage;
    private List<Point> path;
    private double scale = 1.0; // Initial zoom scale
    private Point imageOffset = new Point(0, 0); // Offset for panning
    private Point lastDragPoint = null; // Track the last drag point

    public MapPanel(String imagePath) {
        // Load the image from the resources folder
        try {
            mapImage = new ImageIcon(getClass().getClassLoader().getResource(imagePath)).getImage();
            setPreferredSize(new Dimension(1200, 900));
        } catch (NullPointerException e) {
            System.err.println("Image not found at path: " + imagePath);
            mapImage = null; // Fallback to null if image is not found
        }

        // Add mouse wheel listener for zooming
        addMouseWheelListener(e -> {
            double delta = 0.1; // Zoom step
            if (e.getPreciseWheelRotation() < 0) {
                scale += delta; // Zoom in
            } else {
                scale = Math.max(0.1, scale - delta); // Zoom out, minimum scale 0.1
            }
            repaint();
        });

        // Add mouse listeners for panning
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastDragPoint = e.getPoint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (lastDragPoint != null) {
                    int dx = e.getX() - lastDragPoint.x;
                    int dy = e.getY() - lastDragPoint.y;
                    imageOffset.translate(dx, dy);
                    lastDragPoint = e.getPoint();
                    repaint();
                }
            }
        });

        // Reset drag point when mouse is released
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                lastDragPoint = null;
            }
        });
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
            int imageWidth = (int) (mapImage.getWidth(this) * scale);
            int imageHeight = (int) (mapImage.getHeight(this) * scale);

            // Center the image with panning offset
            int x = (getWidth() - imageWidth) / 2 + imageOffset.x;
            int y = (getHeight() - imageHeight) / 2 + imageOffset.y;

            // Draw the scaled image
            g2d.drawImage(mapImage, x, y, imageWidth, imageHeight, this);

            // Draw the path
            if (path != null) {
                g2d.setColor(Color.BLUE);
                g2d.setStroke(new BasicStroke(3));
                for (int i = 0; i < path.size() - 1; i++) {
                    Point p1 = scalePoint(path.get(i), x, y, imageWidth, imageHeight);
                    Point p2 = scalePoint(path.get(i + 1), x, y, imageWidth, imageHeight);
                    g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
                    drawArrowHead(g2d, p1, p2); // Draw an arrowhead at the end of each path segment
                }
            }
        }
    }

    /**
     * Draws an arrowhead.
     */
    private void drawArrowHead(Graphics2D g2d, Point from, Point to) {
        double arrowLength = 33; // Arrow length
        double arrowWidth = 50;  // Arrow width
        double theta = Math.atan2(to.y - from.y, to.x - from.x); // Calculate the angle of direction

        // Two side points of the arrowhead
        double x1 = to.x - arrowLength * Math.cos(theta - Math.PI / 6);
        double y1 = to.y - arrowLength * Math.sin(theta - Math.PI / 6);
        double x2 = to.x - arrowLength * Math.cos(theta + Math.PI / 6);
        double y2 = to.y - arrowLength * Math.sin(theta + Math.PI / 6);

        // Draw the arrowhead lines
        int[] xPoints = {(int) to.x, (int) x1, (int) x2};
        int[] yPoints = {(int) to.y, (int) y1, (int) y2};
        g2d.fillPolygon(xPoints, yPoints, 3); // Fill the arrowhead with a polygon
    }

    /**
     * Scales a point based on the image's original and scaled dimensions.
     */
    private Point scalePoint(Point original, int offsetX, int offsetY, int scaledWidth, int scaledHeight) {
        double xRatio = scaledWidth / (double) mapImage.getWidth(this);
        double yRatio = scaledHeight / (double) mapImage.getHeight(this);

        int scaledX = (int) (original.x * xRatio) + offsetX;
        int scaledY = (int) (original.y * yRatio) + offsetY;

        return new Point(scaledX, scaledY);
    }
}
