package entity;

/**
 * Factory for creating locations.
 */
abstract class LocationFactory {
    /**
     * Create a location.
     * @param id The id of the location.
     * @return The location.
     */
    public abstract Location create(String id);
}
