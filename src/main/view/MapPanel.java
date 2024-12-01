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
        loadImage(imagePath);
        setupListeners();
        setPreferredSize(new Dimension(1200, 900));
    }

    private void loadImage(String imagePath) {
        try {
            mapImage = new ImageIcon(getClass().getClassLoader().getResource(imagePath)).getImage();
        } catch (NullPointerException e) {
            System.err.println("Image not found at path: " + imagePath);
            mapImage = null; // Fallback if image is not found
        }
    }

    private void setupListeners() {
        // Mouse wheel listener for zooming
        addMouseWheelListener(e -> {
            double zoomStep = 0.005;
            double rotation = e.getPreciseWheelRotation();
            if (rotation < 0) {
                scale = Math.min(scale + zoomStep, 5.0); // Zoom in
            } else if (rotation > 0) {
                scale = Math.max(0.3, scale - zoomStep); // Zoom out
            }
            repaint();
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
            int x = (getWidth() - imageWidth) / 2 + imageOffset.x;
            int y = (getHeight() - imageHeight) / 2 + imageOffset.y;

            // Draw map
            g2d.drawImage(mapImage, x, y, imageWidth, imageHeight, this);

            // Draw path
            if (path != null) {
                g2d.setColor(Color.BLUE);
                g2d.setStroke(new BasicStroke(3));
                for (int i = 0; i < path.size() - 1; i++) {
                    Point p1 = scalePointToMap(path.get(i), x, y, imageWidth, imageHeight);
                    Point p2 = scalePointToMap(path.get(i + 1), x, y, imageWidth, imageHeight);
                    g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
                    drawArrowHead(g2d, p1, p2); // Draw arrowheads
                }
            }
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

    private Point scalePointToMap(Point original, int offsetX, int offsetY, int scaledWidth, int scaledHeight) {
        double xRatio = (double) scaledWidth / mapImage.getWidth(this);
        double yRatio = (double) scaledHeight / mapImage.getHeight(this);

        int scaledX = (int) (original.x * xRatio) + offsetX;
        int scaledY = (int) (original.y * yRatio) + offsetY;

        return new Point(scaledX, scaledY);
    }
}
