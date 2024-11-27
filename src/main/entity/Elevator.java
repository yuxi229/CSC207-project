package entity;

import java.util.List;

/**
 * Represents an elevator that spans multiple floors.
 */
public class Elevator extends MultiFloorLocation {
    private List<String> connected; // List of connected location IDs

    public Elevator(String id, int size, int floorStart, int floorEnd, int startX, int startY, int endX, int endY, List<String> connected) {
        super(id, size, floorStart, floorEnd, startX, startY, endX, endY);
        this.connected = connected;
    }

    public List<String> getConnected() { return connected; }

    @Override
    public String toString() {
        return "Elevator{" +
                "connected=" + connected +
                "} " + super.toString();
    }
}
