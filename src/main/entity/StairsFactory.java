package entity;

/**
 * Factory for creating Stairs.
 */
public class StairsFactory {
    /**
     * Creates a new Room.
     *
     * @param id the id of the new room
     * @param lowerFloor the lower floor the stairs connect to.
     * @param upperFloor the upper floor the stairs connect to.
     * @param lowerCorridor the corridor of the lower floor the stairs connect to.
     * @param upperCorridor the corridor of the upper floor the stairs connect to.
     * @param length the length of the stairs.
     * @return the new room
     */
    public Stairs createStairs(String id, Floor lowerFloor, Floor upperFloor, Corridor lowerCorridor,
                               Corridor upperCorridor, double length) {

        return new Stairs(id, lowerFloor, upperFloor, lowerCorridor, upperCorridor, length);
    }

}
