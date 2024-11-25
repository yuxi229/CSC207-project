package entity;

import java.util.ArrayList;

/**
 * A implementation of Elevator that inherits the Location class.
 */
public class Elevator extends Location {
    private ArrayList<Integer> floorsConnected;
    private int length;

    public Elevator(String id, ArrayList<Integer> floorsConnected, int length) {
        super(id);
        this.floorsConnected = floorsConnected;
        this.length = length;
    }

    public ArrayList<Integer> getFloorsConnected() {
        return floorsConnected;
    }

    public void setFloorsConnected(ArrayList<Integer> floorsConnected) {
        this.floorsConnected = floorsConnected;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
