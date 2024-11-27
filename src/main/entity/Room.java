package entity;

import java.util.List;

/**
 * A Room implementation that inherits the Location class.
 */
public class Room extends AbstractLocation {
    private final List<String> corridorsList;
    private final List<String> floorIdsList;

    public Room(String id, List<String> corridorsList, List<String> floorIdsList) {
        super(id);
        this.corridorsList = corridorsList;
        this.floorIdsList = floorIdsList;
    }

    public String getRoomCode() {
        return this.getId();
    }

    public List<String> getConnectedCorridors() {
        return List.copyOf(corridorsList);
    }

    @Override
    public List<String> getFloors() {
        return List.copyOf(floorIdsList);
    }

    @Override
    public List<String> getConnectedLocations() {
        return getConnectedCorridors();
    }
}
