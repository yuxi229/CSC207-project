package use_case.navigation;

import entity.Location;
import entity.MultiFloorLocation;
import use_case.LocationDataAccessInterface;
import use_case.navigation.PathFinder;

import java.util.*;

public class GraphPathFinder implements PathFinder {
    private final HashMap<String, List<String>> adjacencyList;

    public GraphPathFinder(LocationDataAccessInterface locationDataAccess) {
        this.adjacencyList = new HashMap<>();
        buildGraph(locationDataAccess);
    }

    // Build the graph using the LocationDataAccessInterface
    private void buildGraph(LocationDataAccessInterface locationDataAccess) {
        // Add single-floor locations
        for (Location location : locationDataAccess.getSingleFloorLocations()) {
            adjacencyList.putIfAbsent(location.getId(), new ArrayList<>());
            List<String> connected = getConnected(locationDataAccess, location.getId());
            adjacencyList.get(location.getId()).addAll(connected);
        }

        // Add multi-floor locations
        for (MultiFloorLocation location : locationDataAccess.getMultiFloorLocations()) {
            adjacencyList.putIfAbsent(location.getId(), new ArrayList<>());
            List<String> connected = getConnected(locationDataAccess, location.getId());
            adjacencyList.get(location.getId()).addAll(connected);
        }
    }

    // Updated getConnected method
    private List<String> getConnected(LocationDataAccessInterface locationDataAccess, String id) {
        // Check if it's a single-floor location
        if (locationDataAccess.getLocation(id) != null) {
            Location location = locationDataAccess.getLocation(id);

            if (location instanceof entity.Room) {
                return ((entity.Room) location).getConnected();
            } else if (location instanceof entity.Corridor) {
                return ((entity.Corridor) location).getConnected();
            } else if (location instanceof entity.Washroom) {
                return ((entity.Washroom) location).getConnected();
            } else if (location instanceof entity.Valve) {
                return ((entity.Valve) location).getConnected();
            }
        }

        // Check if it's a multi-floor location
        if (locationDataAccess.getMultiFloorLocation(id) != null) {
            MultiFloorLocation location = locationDataAccess.getMultiFloorLocation(id);

            if (location instanceof entity.Stair) {
                return ((entity.Stair) location).getConnected();
            } else if (location instanceof entity.Elevator) {
                return ((entity.Elevator) location).getConnected();
            }
        }

        // Return empty if no connections are found
        return Collections.emptyList();
    }


    // Find the shortest path using BFS
    public List<String> findShortestPath(String startId, String endId) {
        if (!adjacencyList.containsKey(startId) || !adjacencyList.containsKey(endId)) {
            throw new IllegalArgumentException("Invalid start or end location ID.");
        }

        Queue<List<String>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        // Start BFS with the starting node
        queue.add(List.of(startId));
        visited.add(startId);

        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String current = path.get(path.size() - 1);

            // If we reach the destination, return the path
            if (current.equals(endId)) {
                return path;
            }

            // Explore neighbors
            for (String neighbor : adjacencyList.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);

                    // Create a new path extending the current one
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }

        // Return an empty list if no path is found
        return Collections.emptyList();
    }

    // For debugging: Print the adjacency list
    public void printGraph() {
        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
