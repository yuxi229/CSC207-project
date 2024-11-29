package entity;

import java.util.List;

/**
 * Implementation of Elevator.
 */
public class Elevator extends AbstractMultiFloorLocation {
    public Elevator(String id, List<String> connected, List<Integer> floors, int size) {
        super(id, connected, size, floors);
    }
}
