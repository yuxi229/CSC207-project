package use_case.navigation;

import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import data_access.LocationDataAccessInterface;
import entity.AbstractLocation;
import entity.Floor;
import entity.Room;

/**
 * A class that finds the shortest path between two locations on the map.
 */
public class GraphPathFinder implements PathFinder {
    private static final double DEFAULT_WEIGHT = 1.0;
    private final SimpleWeightedGraph<MapLocation, DefaultWeightedEdge> map =
            new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
    private LocationDataAccessInterface database;

    /**
     * Default constructor. Initializes the pathfinder with no data.
     */
    public GraphPathFinder() {
    }

    /**
     * Constructor that initializes the pathfinder with the given data.
     * @param locationDao the data access object to use
     */
    public GraphPathFinder(LocationDataAccessInterface locationDao) {
        this.database = locationDao;
        loadData(database);
    }

    /**
     * Initializes the pathfinder with the given data.
     * @param inMemoryDao the data access object to use
     */
    @Override
    public void loadData(LocationDataAccessInterface inMemoryDao) {
        database = inMemoryDao;
        for (Floor floor: database.getFloors()) {
            buildFloor(floor);
        }
        linkFloors();
    }

    /**
     * Go through all the locations on the floor and link them together.
     * @param floor the floor to build the graph for
     */
    private void buildFloor(Floor floor) {
        // Loop through all the locations on the floor
        for (AbstractLocation location1 : floor.getLocationsList()) {
            final MapLocation mapLocation1 = database.getMapLocation(location1.getId(), floor.getFloorId());
            // Link the location to all the locations it is connected to
            for (AbstractLocation location2 : location1.getConnectedLocations()) {
                final MapLocation mapLocation2 = database.getMapLocation(location2.getId(), floor.getFloorId());
                linkLocations(mapLocation1, mapLocation2, calculateWeight(location1, location2));
            }
        }
    }

    /**
     * Go through all the locations that span multiple floors and link them together.
     */
    private void linkFloors() {
        for (AbstractLocation location : database.getLocations()) {
            final List<String> floorsConnected = location.getFloors();
            // If the location is on multiple floors, link its map locations on the different floors
            if (floorsConnected.size() > 1) {
                for (String floor1Id : floorsConnected) {
                    for (String floor2Id : floorsConnected) {
                        if (!floor1Id.equals(floor2Id)) {
                            final MapLocation mapLocation1 = database
                                    .getMapLocation(location.getId(), floor1Id);
                            final MapLocation mapLocation2 = database
                                    .getMapLocation(location.getId(), floor2Id);
                            linkLocations(mapLocation1, mapLocation2, DEFAULT_WEIGHT);
                        }
                    }
                }
            }
        }
    }

    /**
     * Links two map locations together with the given weight.
     * @param location1 the first location to link
     * @param location2 the second location to link
     * @param weight the weight of the edge between the two locations
     */
    private void linkLocations(MapLocation location1, MapLocation location2, Double weight) {
        // No duplicate vertices will be added because the vertices are stored in a set
        if (!map.containsVertex(location1)) {
            map.addVertex(location1);
        }
        else if (!map.containsVertex(location2)) {
            map.addVertex(location2);
        }
        final DefaultWeightedEdge edge = map.addEdge(location1, location2);
        map.setEdgeWeight(edge, weight);
    }

    /**
     * Calculates the weight between two locations.
     * @param location1 the first location
     * @param location2 the second location
     * @return the weight between the two locations
     */
    private double calculateWeight(AbstractLocation location1, AbstractLocation location2) {
        // TODO: Decide on weight strategy in meeting
        return DEFAULT_WEIGHT;
    }

    private MapLocation roomCodeToMapLocation(String roomCode) {
        final Room room = database.getRoom(roomCode);
        // Pick the first floor the room is on
        final String floorID = room.getFloors().get(0);
        return database.getMapLocation(room.getId(), floorID);
    }

    /**
     * Returns the path from the start room to the end room as a list of ids.
     * @param startRoomCode valid room code for starting room
     * @param endRoomCode valid room code for ending room
     * @return A list of MapLocation objects representing the path.
     */
    @Override
    public List<MapLocation> getPath(String startRoomCode, String endRoomCode) {
        // Use Dijkstra's algorithm to find the shortest path. Change algorithm if needed.
        final DijkstraShortestPath<MapLocation, DefaultWeightedEdge> dijkstraAlg = new DijkstraShortestPath<>(map);
        final GraphPath<MapLocation, DefaultWeightedEdge> route =
                dijkstraAlg.getPath(roomCodeToMapLocation(startRoomCode), roomCodeToMapLocation(endRoomCode));
        return route.getVertexList();
    }
}
