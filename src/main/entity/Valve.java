package entity;

import java.util.List;

/**
 * Represents a valve in the building.
 */
public class Valve extends Location {
    private List<String> connected; // List of connected location IDs

    public Valve(String id, int size, int floor, int imgXpos, int imgYpos, List<String> connected) {
        super(id, size, floor, imgXpos, imgYpos);
        this.connected = connected;
    }

    public List<String> getConnected() { return connected; }

    @Override
    public String toString() {
        return "Valve{" +
                "connected=" + connected +
                "} " + super.toString();
    }
}
