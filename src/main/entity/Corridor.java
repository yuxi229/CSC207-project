package entity;

import java.util.List;

/**
 * A Corridor implementation that inherits the Location class.
 */
public class Corridor extends AbstractSingleFloorLocation {
    private final double length;

    Corridor(String id, List<String> connected, int size, int floor, double length) {
        super(id, connected, size, floor);
        this.length = length;
    }

    public double getLength() {
        return length;
    }
}
