package entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private String id;
    private Point center;
    private List<Room> neighbors;

    public Room(String id, Point center) {
        this.id = id;
        this.center = center;
        this.neighbors = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public Point getCenter() {
        return center;
    }

    public List<Room> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Room neighbor) {
        this.neighbors.add(neighbor);
    }
}