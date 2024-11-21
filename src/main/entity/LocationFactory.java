package entity;

/**
 * Factory for creating locations.
 */
abstract class LocationFactory {
    /**
     * Creates a new Room.
     * @param code the code of the new location
     * @return the new location
     */
    User create(String code);

}
