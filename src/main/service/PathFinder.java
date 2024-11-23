package service;

import entity.Room;

import java.awt.Point;
import java.util.*;

public class PathFinder {
    public static List<Point> findPath(Room start, Room end) {
        Map<Room, Room> cameFrom = new HashMap<>();
        Queue<Room> queue = new LinkedList<>();
        queue.add(start);
        cameFrom.put(start, null);

        while (!queue.isEmpty()) {
            Room current = queue.poll();

            if (current.equals(end)) {
                break;
            }

            for (Room neighbor : current.getNeighbors()) {
                if (!cameFrom.containsKey(neighbor)) {
                    queue.add(neighbor);
                    cameFrom.put(neighbor, current);
                }
            }
        }

        List<Point> path = new ArrayList<>();
        for (Room room = end; room != null; room = cameFrom.get(room)) {
            path.add(room.getCenter());
        }
        Collections.reverse(path);
        return path;
    }
}
