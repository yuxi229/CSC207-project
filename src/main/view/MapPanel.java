package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;

public class MapPanel extends JPanel {
    private Image mapImage;
    private List<Point> path;
    private double scale = 1.0; // Initial zoom scale
    private Point imageOffset = new Point(0, 0); // Offset for panning
    private Point lastDragPoint = null; // Track the last drag point

    public MapPanel(String imagePath) {
        loadImage(imagePath);
        setupListeners();
        setPreferredSize(new Dimension(1200, 900));
    }

    private void loadImage(String imagePath) {
        try {
            mapImage = ImageIO.read(getClass().getClassLoader().getResource(imagePath));
        } catch (Exception e) {
            System.err.println("Image not found at path: " + imagePath);
            mapImage = null; // Fallback if image is not found
        }
    }

    private void setupListeners() {
        // Mouse wheel listener for zooming
        addMouseWheelListener(e -> {
            double zoomStep = 0.1; // Adjust zoom step as needed
            double rotation = e.getPreciseWheelRotation();
            if (rotation < 0) {
                scale = Math.min(scale + zoomStep, 5.0); // Zoom in
            } else if (rotation > 0) {
                scale = Math.max(0.3, scale - zoomStep); // Zoom out
            }
            repaint();
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                double zoomStep = 0.025; // Adjust zoom step for responsiveness
                double minScale = 0.5; // Minimum zoom scale
                double maxScale = 5; // Maximum zoom scale

                double oldScale = scale;
                if (e.getPreciseWheelRotation() < 0) {
                    // Zoom in
                    scale = Math.min(scale + zoomStep, maxScale);
                } else {
                    // Zoom out
                    scale = Math.max(minScale, scale - zoomStep);
                }

                // Adjust image offset to center zoom around mouse pointer
                double scaleChange = scale / oldScale;
                int mouseX = e.getX();
                int mouseY = e.getY();
                imageOffset.x = (int) (mouseX - scaleChange * (mouseX - imageOffset.x));
                imageOffset.y = (int) (mouseY - scaleChange * (mouseY - imageOffset.y));

                repaint();
            }
        });

        // Mouse listeners for panning
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastDragPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                lastDragPoint = null;
            }
        });

        addMouseMotionListener(new MouseAdapter() {
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
    }

    public void setPath(List<Point> path) {
        this.path = path;
        repaint();
    }

    public void clearPath() {
        this.path = null;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (mapImage != null) {
            Graphics2D g2d = (Graphics2D) g;

            int imageWidth = (int) (mapImage.getWidth(this) * scale);
            int imageHeight = (int) (mapImage.getHeight(this) * scale);
            int x = (getWidth() - imageWidth) / 2 + imageOffset.x;
            int y = (getHeight() - imageHeight) / 2 + imageOffset.y;

            // Draw map
            g2d.drawImage(mapImage, x, y, imageWidth, imageHeight, this);

            // Draw path
            if (path != null && !path.isEmpty()) {
                g2d.setColor(Color.BLUE);
                g2d.setStroke(new BasicStroke(3));
                for (int i = 0; i < path.size() - 1; i++) {
                    Point p1 = scalePointToImage(path.get(i), x, y, scale);
                    Point p2 = scalePointToImage(path.get(i + 1), x, y, scale);
                    g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
                    drawArrowHead(g2d, p1, p2);
                }
            }
        } else {
            g.drawString("Map image not available.", getWidth() / 2 - 50, getHeight() / 2);
        }
    }

    private void drawArrowHead(Graphics2D g2d, Point from, Point to) {
        double arrowLength = 20 * scale;
        double arrowWidth = 10 * scale;
        double theta = Math.atan2(to.y - from.y, to.x - from.x);

        double x1 = to.x - arrowLength * Math.cos(theta - Math.PI / 6);
        double y1 = to.y - arrowLength * Math.sin(theta - Math.PI / 6);
        double x2 = to.x - arrowLength * Math.cos(theta + Math.PI / 6);
        double y2 = to.y - arrowLength * Math.sin(theta + Math.PI / 6);

        int[] xPoints = {(int) to.x, (int) x1, (int) x2};
        int[] yPoints = {(int) to.y, (int) y1, (int) y2};
        g2d.fillPolygon(xPoints, yPoints, 3);
    }

    private Point scalePointToImage(Point point, int offsetX, int offsetY, double scale) {
        int scaledX = (int) (point.x * scale) + offsetX;
        int scaledY = (int) (point.y * scale) + offsetY;
        return new Point(scaledX, scaledY);
    }

    public void updateBlueprint(String imagePath) {
        loadImage(imagePath);
        repaint();
    }
}