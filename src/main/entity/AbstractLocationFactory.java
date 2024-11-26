package entity;

/**
 * Factory for creating locations.
 */
public abstract class AbstractLocationFactory {

    /**
     *  Abstract method to be implemented by specific factories.
     * @param id the id of the location
     * @return the location created
     */
    public abstract AbstractLocation createLocation(String id);

}
