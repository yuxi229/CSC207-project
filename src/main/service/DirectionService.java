package service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DirectionService {
    public static List<Point> getPathForRoom(String room) {
        List<Point> path = new ArrayList<>();
        switch (room) {
            case "Room 101":
                path.add(new Point(50, 50));
                path.add(new Point(50, 150));
                break;
            case "Room 102":
                path.add(new Point(50, 50));
                path.add(new Point(150, 150));
                break;
            case "Room 201":
                path.add(new Point(50, 50));
                path.add(new Point(50, 150));
                path.add(new Point(50, 250));
                break;
            case "Room 202":
                path.add(new Point(50, 50));
                path.add(new Point(150, 150));
                path.add(new Point(150, 250));
                break;
        }
        return path;
    }

    // get text direction
    public static String[] getDirections(String room) {
        switch (room) {
            case "Room 101":
                return new String[]{"Enter the lobby", "Go straight to the first floor, turn left to Room 101"};
            case "Room 102":
                return new String[]{"Enter the lobby", "Go straight to the first floor, turn right to Room 102"};
            case "Room 201":
                return new String[]{"Enter the lobby", "Go straight to the first floor", "Go up to the second floor, turn left to Room 201"};
            case "Room 202":
                return new String[]{"Enter the lobby", "Go straight to the first floor", "Go up to the second floor, turn right to Room 202"};
            default:
                return new String[]{"Unknown path"};
        }
    }
}