package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A Stair implementation that inherits location.
 */

public class Stairs extends Location {
    private Floor lowerFloor;
    private Floor upperFloor;
    private Corridor lowerCorridor;
    private Corridor upperCorridor;
    private double length;

    public Stairs(String id) {
        super(id);
    }

    @Override
    public List<Floor> getFloors() {
        return List.of(lowerFloor, upperFloor);
    }

    @Override
    public List<Location> getConnected() {
        ArrayList<Location> connected = new ArrayList<>();
        connected.add(lowerCorridor);
        connected.add(upperCorridor);
        return connected;
    }

    public Floor getLowerFloor() {
        return lowerFloor;
    }

    public void setLowerFloor(Floor lowerFloor) {
        this.lowerFloor = lowerFloor;
    }

    public Floor getUpperFloor() {
        return upperFloor;
    }

    public void setUpperFloor(Floor upperFloor) {
        this.upperFloor = upperFloor;
    }

    public Corridor getLowerCorridor() {
        return lowerCorridor;
    }

    public void setLowerCorridor(Corridor lowerCorridor) {
        this.lowerCorridor = lowerCorridor;
    }

    public Corridor getUpperCorridor() {
        return upperCorridor;
    }

    public void setUpperCorridor(Corridor upperCorridor) {
        this.upperCorridor = upperCorridor;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}