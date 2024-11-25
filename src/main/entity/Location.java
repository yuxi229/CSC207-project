package entity;

/**
 * The representation of a location in our program.
 */
abstract class Location {
    private String id;

    public Location(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

