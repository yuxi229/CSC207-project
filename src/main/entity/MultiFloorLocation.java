package main.entity;

/**
 * Base class for locations that span multiple floors (e.g., Stairs, Elevators).
 */
public abstract class MultiFloorLocation {
    private String id;          // Unique identifier
    private int size;           // Size of the location
    private int floorStart;     // Starting floor
    private int floorEnd;       // Ending floor
    private int startX;         // X-coordinate on the starting floor
    private int startY;         // Y-coordinate on the starting floor
    private int endX;           // X-coordinate on the ending floor
    private int endY;           // Y-coordinate on the ending floor

    public MultiFloorLocation(String id, int size, int floorStart, int floorEnd, int startX, int startY, int endX, int endY) {
        this.id = id;
        this.size = size;
        this.floorStart = floorStart;
        this.floorEnd = floorEnd;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    // Getters
    public String getId() { return id; }
    public int getSize() { return size; }
    public int getFloorStart() { return floorStart; }
    public int getFloorEnd() { return floorEnd; }
    public int getStartX() { return startX; }
    public int getStartY() { return startY; }
    public int getEndX() { return endX; }
    public int getEndY() { return endY; }

    @Override
    public String toString() {
        return "MultiFloorLocation{" +
                "id='" + id + '\'' +
                ", size=" + size +
                ", floorStart=" + floorStart +
                ", floorEnd=" + floorEnd +
                ", startX=" + startX +
                ", startY=" + startY +
                ", endX=" + endX +
                ", endY=" + endY +
                '}';
    }
}
