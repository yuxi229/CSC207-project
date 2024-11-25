package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Elevator.
 */
public class Elevator extends Location {

    // List of floor numbers the elevator can access
    private ArrayList<Room> roomList;
    private ArrayList<Stairs> stairsList;
    private ArrayList<Floor> floorsList;
    // Represents the number of floors spanned by this elevator
    private double length;

    // Constructor

    public Elevator(String id, ArrayList<Room> roomList, ArrayList<Stairs> stairsList,
                    ArrayList<Floor> floorsList, double length) {
        super(id);
        this.roomList = roomList;
        this.stairsList = stairsList;
        this.floorsList = floorsList;
        this.length = length;
    }

    @Override
    public List<Floor> getFloors() {
        return List.of();
    }

    @Override
    public String getId(String id) {
        return id;
    }

    @Override
    public List<Location> getConnected() {
        return null;
    }

    public double getLength() {
        return length;
    }
}
