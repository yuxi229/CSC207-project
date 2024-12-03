package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Represents a panel for displaying a map with zoom and pan functionality.
 */

public class MapPanel extends JPanel {
    public static final int N_POINTS = 3;
    public static final int FIFTY = 50;
    public static final Dimension MAP_DIMENSION = new Dimension(1200, 900);
    public static final BasicStroke BASIC_STROKE = new BasicStroke(3);
    public static final BasicStroke BIG_STROKE = new BasicStroke(5);

    private Image mapImage;
    private List<Point> path;
    private List<Integer> floors;
    // Initial zoom scale
    private double scale = 1.0;
    // Offset for panning
    private final Point imageOffset = new Point(0, 0);
    // Track the last drag point
    private Point lastDragPoint;
    private Integer currentInstructionIndex = 0;

    public MapPanel(String imagePath) {
        loadImage(imagePath);
        setupListeners();
        setPreferredSize(MAP_DIMENSION);
    }

    public void previousInstruction() {
        if (currentInstructionIndex > 0) {
            currentInstructionIndex = currentInstructionIndex - 1;
            repaint();
        }
    }

    public void nextInstruction() {
        if (currentInstructionIndex < path.size() - 1) {
            currentInstructionIndex = currentInstructionIndex + 1;
            repaint();
        }
    }

    public Integer getCurrentFloor() {
        return this.floors.get(this.currentInstructionIndex);
    }

    public Integer getCurrentInstructionIndex() {
        return this.currentInstructionIndex;
    }

    private void loadImage(String imagePath) {
        try {
            mapImage = ImageIO.read(getClass().getClassLoader().getResource(imagePath));
        }
        catch (Exception exception) {
            System.err.println("Image not found at path: " + imagePath);
            // Fallback if image is not found
            mapImage = null;
        }
    }

    private void setupListeners() {
        // Mouse wheel listener for zooming
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                // Adjust zoom step for responsiveness
                final double zoomStep = 0.025;
                // Minimum zoom scale
                final double minScale = 0.5;
                // Maximum zoom scale
                final double maxScale = 5;

                final double oldScale = scale;
                if (e.getPreciseWheelRotation() < 0) {
                    // Zoom in
                    scale = Math.min(scale + zoomStep, maxScale);
                }
                else {
                    // Zoom out
                    scale = Math.max(minScale, scale - zoomStep);
                }

                // Adjust image offset to center zoom around mouse pointer
                final double scaleChange = scale / oldScale;
                final int mouseX = e.getX();
                final int mouseY = e.getY();
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
                    final int dx = e.getX() - lastDragPoint.x;
                    final int dy = e.getY() - lastDragPoint.y;
                    imageOffset.translate(dx, dy);
                    lastDragPoint = e.getPoint();
                    repaint();
                }
            }
        });
    }

    /**
     * Sets the path to be drawn on the map.
     * @param path the list of points representing the path.
     * @param floors the list of floors the path traverses.
     */
    public void setPath(List<Point> path, List<Integer> floors) {
        this.path = path;
        this.floors = floors;
        repaint();
    }

    /**
     * Clears the current path displayed on the map.
     */
    public void clearPath() {
        this.path = null;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (mapImage != null) {
            final Graphics2D g2d = (Graphics2D) g;

            final int imageWidth = (int) (mapImage.getWidth(this) * scale);
            final int imageHeight = (int) (mapImage.getHeight(this) * scale);
            final int x = (getWidth() - imageWidth) / 2 + imageOffset.x;
            final int y = (getHeight() - imageHeight) / 2 + imageOffset.y;

            // Draw map
            g2d.drawImage(mapImage, x, y, imageWidth, imageHeight, this);

            // Draw path
            if (path != null && !path.isEmpty()) {
                Integer curFloor = floors.get(currentInstructionIndex);
                for (int i = 0; i < path.size() - 1; i++) {
                    final Point p1 = scalePointToImage(path.get(i), x, y, scale);
                    final Point p2 = scalePointToImage(path.get(i + 1), x, y, scale);

                    if (i == currentInstructionIndex) {
                        drawBigArrow(g2d, p1, p2);
                    }
                    else if (curFloor.equals(floors.get(i+1)) && curFloor.equals(floors.get(i))) {
                        drawNormalArrow(g2d, p1, p2);
                    }
                }
            }

        }
        else {
            g.drawString("Map image not available.", getWidth() / 2 - FIFTY, getHeight() / 2);
        }
    }

    private void drawNormalArrow(Graphics2D g2d, Point p1, Point p2) {
        g2d.setColor(Color.BLUE);
        g2d.setStroke(BASIC_STROKE);
        g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        drawArrowHead(g2d, p1, p2);
    }

    private void drawBigArrow(Graphics2D g2d, Point p1, Point p2) {
        g2d.setColor(Color.RED);
        g2d.setStroke(BIG_STROKE);
        g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        drawArrowHead(g2d, p1, p2);
    }

    private void drawArrowHead(Graphics2D g2d, Point fromPoint, Point toPoint) {
        final double arrowLength = 20 * scale;
        final double theta = Math.atan2(toPoint.y - fromPoint.y, toPoint.x - fromPoint.x);

        final double x1 = toPoint.x - arrowLength * Math.cos(theta - Math.PI / 6);
        final double y1 = toPoint.y - arrowLength * Math.sin(theta - Math.PI / 6);
        final double x2 = toPoint.x - arrowLength * Math.cos(theta + Math.PI / 6);
        final double y2 = toPoint.y - arrowLength * Math.sin(theta + Math.PI / 6);

        final int[] xPoints = {toPoint.x, (int) x1, (int) x2};
        final int[] yPoints = {toPoint.y, (int) y1, (int) y2};
        g2d.fillPolygon(xPoints, yPoints, N_POINTS);
    }


    private Point scalePointToImage(Point point, int offsetX, int offsetY, double localScale) {
        final int scaledX = (int) (point.x * localScale) + offsetX;
        final int scaledY = (int) (point.y * localScale) + offsetY;
        return new Point(scaledX, scaledY);
    }

    /**
     * Updates the displayed map image with a new blueprint.
     * @param imagePath the path to the new image file.
     */
    public void updateMap(String imagePath) {
        loadImage(imagePath);
        repaint();
    }
}
