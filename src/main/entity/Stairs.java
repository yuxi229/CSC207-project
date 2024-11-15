package entity;

public class Stairs implements Location {
    private int floorFrom;
    private int floorTo;
    private Location topConnection;
    private Location bottomConnection; // ID of the room or corridor at the bottom of the stairs
    private double length;

    public Stairs(int floorFrom, int floorTo, Location topConnection, Location bottomConnection, double length) {
        this.floorFrom = floorFrom;
        this.floorTo = floorTo;
        this.topConnection = topConnection;
        this.bottomConnection = bottomConnection;
        this.length = length;
    }

    public int getFloorFrom() {
        return floorFrom;
    }

    public void setFloorFrom(int floorFrom) {
        this.floorFrom = floorFrom;
    }

    public int getFloorTo() {
        return floorTo;
    }

    public void setFloorTo(int floorTo) {
        this.floorTo = floorTo;
    }

    public Location getTopConnection() {
        return topConnection;
    }

    public void setTopConnection(Location topConnection) {
        this.topConnection = topConnection;
    }

    public Location getBottomConnection() {
        return bottomConnection;
    }

    public void setBottomConnection(Location bottomConnection) {
        this.bottomConnection = bottomConnection;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
