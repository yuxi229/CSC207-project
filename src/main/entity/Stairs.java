package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A Stair implementation that inherits location.
 */

public class Stairs extends AbstractLocation {
    private final String lowerFloorId;
    private final String upperFloorId;
    private final Corridor lowerCorridor;
    private final Corridor upperCorridor;
    private final double length;

    public Stairs(String id, String lowerFloorId, String upperFloorId, Corridor lowerCorridor,
                  Corridor upperCorridor, double length) {
        super(id);
        this.lowerFloorId = lowerFloorId;
        this.upperFloorId = upperFloorId;
        this.lowerCorridor = lowerCorridor;
        this.upperCorridor = upperCorridor;
        this.length = length;
    }

    @Override
    public List<String> getFloors() {
        return List.of(lowerFloorId, upperFloorId);
    }

    @Override
    public List<AbstractLocation> getConnectedLocations() {
        final ArrayList<AbstractLocation> connected = new ArrayList<>();
        connected.add(lowerCorridor);
        connected.add(upperCorridor);
        return connected;
    }

    public String getLowerFloorId() {
        return lowerFloorId;
    }

    public String getUpperFloorId() {
        return upperFloorId;
    }

    public Corridor getLowerCorridor() {
        return lowerCorridor;
    }

    public Corridor getUpperCorridor() {
        return upperCorridor;
    }

    public double getLength() {
        return length;
    }
}
