package entity;

import java.util.List;

/**
 * Represents a corridor in the building.
 */
public class Corridor extends Location {
    private List<String> connected;

    public Corridor(String id, int size, int floor, int imgXpos, int imgYpos, List<String> connected) {
        super(id, size, floor, imgXpos, imgYpos);
        this.connected = connected;
    }

    public List<String> getConnected() { return connected; }

    @Override
    public String toString() {
        return "Corridor{" +
                "connected=" + connected +
                "} " + super.toString();
    }
}
