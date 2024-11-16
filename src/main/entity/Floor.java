package entity;

import java.util.List;

/**
 * Implementation of Floor.
 */
public class Floor {
    private int number;
    private List<String> rooms;
    private List<String> corridors;
    private List<String> stairs;

    public Floor(int number, List<String> rooms, List<String> corridors, List<String> stairs) {
        this.number = number;
        this.rooms = rooms;
        this.corridors = corridors;
        this.stairs = stairs;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<String> getRooms() {
        return rooms;
    }

    public void setRooms(List<String> rooms) {
        this.rooms = rooms;
    }

    public List<String> getCorridors() {
        return corridors;
    }

    public void setCorridors(List<String> corridors) {
        this.corridors = corridors;
    }

    public List<String> getStairs() {
        return stairs;
    }

    public void setStairs(List<String> stairs) {
        this.stairs = stairs;
    }
}
