package entity;

import java.util.List;

/**
 * Implementation of Elevator.
 */
public class Elevator {
    // List of floor numbers the elevator can access
    private List<Integer> floorsConnected;
    // Represents the number of floors spanned by this elevator
    private int length;

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
