package main.entity;

import java.util.List;

/**
 * Represents a room in the building.
 */
public class Room extends Location {
    private boolean restricted;      // Whether access is restricted
    private List<String> connected; // List of connected location IDs

    public Room(String id, int size, int floor, int imgXpos, int imgYpos, boolean restricted, List<String> connected) {
        super(id, size, floor, imgXpos, imgYpos);
        this.restricted = restricted;
        this.connected = connected;
    }

    public boolean isRestricted() { return restricted; }
    public List<String> getConnected() { return connected; }

    @Override
    public String toString() {
        return "Room{" +
                "restricted=" + restricted +
                ", connected=" + connected +
                "} " + super.toString();
    }
}
