package entity;

/**
 * A Stair implementation that inherits location.
 */

class Stairs extends Location {
    private Floor lowerFloor;
    private Floor upperFloor;
    private Corridor lowerCorridor;
    private Corridor upperCorridor;
    private double length;

    public Stairs(String id) {
        super(id);
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
