package entity;

import java.util.List;

/**
 * Factory for creating Stairs.
 */
public class StairsFactory {
    /**
     * Creates a new Stairs object.
     *
     * @param id           the unique identifier of the stairs
     * @param lowerFloor   the floor number of the lower end of the stairs
     * @param upperFloor   the floor number of the upper end of the stairs
     * @param lowerCorridor the unique identifier of the corridor at the lower end of the stairs
     * @param upperCorridor the unique identifier of the corridor at the upper end of the stairs
     * @param size         the size of the stairs
     */
    public Stairs createStairs(String id, int lowerFloor, int upperFloor, String lowerCorridor,
                               String upperCorridor, int size) {

        final List<String> connected = List.of(lowerCorridor, upperCorridor);
        final List<Integer> floors = List.of(lowerFloor, upperFloor);
        return new Stairs(id, size, connected, floors);
    }
}
