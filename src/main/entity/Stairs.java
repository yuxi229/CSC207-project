package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A Stair implementation that inherits location.
 */

public class Stairs extends AbstractLocation {
    private final String lowerFloorId;
    private final String upperFloorId;
    private final String lowerCorridorId;
    private final String upperCorridorId;
    private final double length;

    public Stairs(String id, String lowerFloorId, String upperFloorId, String lowerCorridorId,
                  String upperCorridorId, double length) {
        super(id);
        this.lowerFloorId = lowerFloorId;
        this.upperFloorId = upperFloorId;
        this.lowerCorridorId = lowerCorridorId;
        this.upperCorridorId = upperCorridorId;
        this.length = length;
    }

    @Override
    public List<String> getFloors() {
        return List.of(lowerFloorId, upperFloorId);
    }

    @Override
    public List<String> getConnectedLocations() {
        final ArrayList<String> connected = new ArrayList<>();
        connected.add(lowerCorridorId);
        connected.add(upperCorridorId);
        return connected;
    }

    public String getLowerFloorId() {
        return lowerFloorId;
    }

    public String getUpperFloorId() {
        return upperFloorId;
    }

    public String getLowerCorridorId() {
        return lowerCorridorId;
    }

    public String getUpperCorridorId() {
        return upperCorridorId;
    }

    public double getLength() {
        return length;
    }
}
