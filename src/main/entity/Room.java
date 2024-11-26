package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A Room implementation that inherits the Location class.
 */
public class Room extends AbstractLocation {
    private final List<Corridor> corridorsList;
    private final List<Floor> floorsList;

    public Room(String id, List<Corridor> corridorsList, List<Floor> floorsList) {
        super(id);
        this.corridorsList = corridorsList;
        this.floorsList = floorsList;
    }

    public String getRoomCode() {
        return this.getId();
    }

    public List<Corridor> getConnectedCorridors() {
        return List.copyOf(corridorsList);
    }

    public List<Floor> getFloors() {
        return List.copyOf(floorsList);
    }

    @Override
    public List<AbstractLocation> getConnectedLocations() {
        return new ArrayList<>(corridorsList);
    }
}
