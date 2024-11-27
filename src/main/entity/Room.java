package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A Room implementation that inherits the Location class.
 */
public class Room extends AbstractLocation {
    private final List<Corridor> corridorsList;
    private final List<String> floorIdsList;

    public Room(String id, List<Corridor> corridorsList, List<String> floorIdsList) {
        super(id);
        this.corridorsList = corridorsList;
        this.floorIdsList = floorIdsList;
    }

    public String getRoomCode() {
        return this.getId();
    }

    public List<Corridor> getConnectedCorridors() {
        return List.copyOf(corridorsList);
    }

    @Override
    public List<String> getFloors() {
        return List.copyOf(floorIdsList);
    }

    @Override
    public List<AbstractLocation> getConnectedLocations() {
        return new ArrayList<>(corridorsList);
    }
}
