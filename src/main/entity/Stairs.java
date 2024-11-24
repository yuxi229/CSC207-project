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
    }}


//**
// * Implementation of Stairs.
// */
//public class Stairs implements Location {
//    private int floorFrom;
//    private int floorTo;
//    private Location topConnection;
//    // ID of the room or corridor at the bottom of the stairs
//    private Location bottomConnection;
//    private double length;
//
//    public Stairs(int floorFrom, int floorTo, Location topConnection, Location bottomConnection, double length) {
//        this.floorFrom = floorFrom;
//        this.floorTo = floorTo;
//        this.topConnection = topConnection;
//        this.bottomConnection = bottomConnection;
//        this.length = length;
//    }
//
//    public int getFloorFrom() {
//        return floorFrom;
//    }
//
//    public void setFloorFrom(int floorFrom) {
//        this.floorFrom = floorFrom;
//    }
//
//    public int getFloorTo() {
//        return floorTo;
//    }
//
//    public void setFloorTo(int floorTo) {
//        this.floorTo = floorTo;
//    }
//
//    public Location getTopConnection() {
//        return topConnection;
//    }
//
//    public void setTopConnection(Location topConnection) {
//        this.topConnection = topConnection;
//    }
//
//    public Location getBottomConnection() {
//        return bottomConnection;
//    }
//
//    public void setBottomConnection(Location bottomConnection) {
//        this.bottomConnection = bottomConnection;
//    }
//
//    public double getLength() {
//        return length;
//    }
//
//    public void setLength(double length) {
//        this.length = length;
//    }
//}
