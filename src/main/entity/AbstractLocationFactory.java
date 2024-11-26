package entity;

/**
 * Factory for creating locations.
 */
public abstract class AbstractLocationFactory {

    // Abstract method to be implemented by specific factories
    public abstract Location createLocation(String id);

}
