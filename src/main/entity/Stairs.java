package entity;

import java.util.List;

/**
 * Represents a stair that spans multiple floors.
 */
public class Stairs extends AbstractMultiFloorLocation {
    public Stairs(String id, int size, List<String> connected, List<Integer> floors) {
        super(id, connected, size, floors);
    }
}
