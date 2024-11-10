package entity;

public class Stairs {
    private int floorFrom;
    private int floorTo;
    private String topConnection;
    private String bottomConnection; // ID of the room or corridor at the bottom of the stairs
    private double length;

    public Stairs(int floorFrom, int floorTo, String topConnection, String bottomConnection, double length) {
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

    public String getTopConnection() {
        return topConnection;
    }

    public void setTopConnection(String topConnection) {
        this.topConnection = topConnection;
    }

    public String getBottomConnection() {
        return bottomConnection;
    }

    public void setBottomConnection(String bottomConnection) {
        this.bottomConnection = bottomConnection;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
