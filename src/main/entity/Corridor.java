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

//**
// * A Corridor implementation of the Location interface.
// */
//public class Corridor implements Location {
//
//    private final String code;
//
//    public Corridor(String code) {
//        this.code = code;
//    }
//
//    @Override
//    public String getRoomCode() {
//        return code;
//    }
//}

//package entity;
//
//import java.util.List;
//
//public class Corridor implements Location {
//    private String id;
//    private List<Room> connectedRooms;
//    private List<Stairs> connectedStairs;
//    private double length;
//
//    public Corridor(String id, List<Room> connectedRooms, List<Stairs> connectedStairs, double length) {
//        this.id = id;
//        this.connectedRooms = connectedRooms;
//        this.connectedStairs = connectedStairs;
//        this.length = length;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public List<Room> getConnectedRooms() {
//        return connectedRooms;
//    }
//
//    public void setConnectedRooms(List<Room> connectedRooms) {
//        this.connectedRooms = connectedRooms;
//    }
//
//    public List<Stairs> getConnectedStairs() {
//        return connectedStairs;
//    }
//
//    public void setConnectedStairs(List<Stairs> connectedStairs) {
//        this.connectedStairs = connectedStairs;
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
