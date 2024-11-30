package entity;

import java.util.List;

/**
 * A Room implementation that inherits the Location class.
 */
public class Room extends AbstractSingleFloorLocation {
    private final boolean isRestricted;

    public Room(String id, List<String> connected, int floor, int size, boolean isRestricted) {
        super(id, connected, size, floor);
        this.isRestricted = isRestricted;
    }

    public String getRoomCode() {
        return this.getId();
    }

    public boolean isRestricted() {
        return isRestricted;
    }
}
