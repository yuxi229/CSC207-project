package entity;

import java.util.List;

public class Elevator {
    private List<Integer> floorsConnected; // List of floor numbers the elevator can access
    private int length; // Represents the number of floors spanned by this elevator

    // Constructor
    public Elevator(List<Integer> floorsConnected, int length) {
        this.floorsConnected = floorsConnected;
        this.length = length;
    }

    public List<Integer> getFloorsConnected() {
        return floorsConnected;
    }

    public void setFloorsConnected(List<Integer> floorsConnected) {
        this.floorsConnected = floorsConnected;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
