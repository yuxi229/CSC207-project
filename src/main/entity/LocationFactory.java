package entity;

/**
 * Factory for creating locations.
 */
public interface LocationFactory {
    /**
     * Creates a new Room.
     * @param code the code of the new location
     * @return the new location
     */
    User create(String code);

}
