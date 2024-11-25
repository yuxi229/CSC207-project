package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A Corridor implementation that inherits the Location class.
 */
class Corridor extends Location {
    private ArrayList<Room> roomList = new ArrayList<>();
    private ArrayList<Stairs> stairsList = new ArrayList<>();
    private Floor floor;
    private double length;

    public Corridor(String id) {
        super(id);
    }

    public Floor getFloor() {
        return floor;
    }

    private void setFloor(Floor floor) {
        this.floor = floor;
    }

    public double getLength() {
        return length;
    }

    private void setLength(double length) {
        this.length = length;
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    private void setRoomList(ArrayList<Room> roomList) {
        this.roomList = roomList;
    }
}
