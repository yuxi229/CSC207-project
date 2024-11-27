package main.use_case.navigation;

import java.util.List;

import main.entity.*;

import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import main.use_case.LocationDataAccessInterface;

public class GraphPathFinder implements PathFinder {
    private SimpleWeightedGraph<MapLocation, DefaultWeightedEdge> map
            = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
    private LocationDataAccessInterface database;
    private static final double DEFAULT_WEIGHT = 1.0;

    /**
     * Default constructor. Initializes the pathfinder with no data.
     */
    public GraphPathFinder() {
    }

    /**
     * Constructor that initializes the pathfinder with the given data.
     * @param locationDAO the data access object to use
     */
    public GraphPathFinder(LocationDataAccessInterface locationDAO) {
        this.database = locationDAO;
        loadData(database);
    }

    /**
     * Initializes the pathfinder with the given data.
     * @param locationDAO the data access object to use
     */
    @Override
    public void loadData(LocationDataAccessInterface locationDAO) {
        database = locationDAO;
        List<Floor> floors = database.getFloors();
        for (Floor floor: floors) {
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
            MapLocation mapLocation1 = database.getMapLocation(location1.getId(), floor.getFloorId());
            // Link the location to all the locations it is connected to
            for (AbstractLocation location2 : location1.getConnectedLocations()) {
                MapLocation mapLocation2 = database.getMapLocation(location2.getId(), floor.getFloorId());
                linkLocations(mapLocation1, mapLocation2, calculateWeight(location1, location2));
            }
        }
    }

    /**
     * Go through all the locations that span multiple floors and link them together.
     */
    private void linkFloors() {
        for (AbstractLocation location : database.getLocations()) {
            List<Floor> floorsConnected = location.getFloors();
            // If the location is on multiple floors, link its map locations on the different floors
            if (floorsConnected.size() > 1) {
                for (Floor floor1 : floorsConnected) {
                    for (Floor floor2 : floorsConnected) {
                        if (!floor1.getFloorId().equals(floor2.getFloorId())) {
                            MapLocation mapLocation1 = database.getMapLocation(location.getId(), floor1.getFloorId());
                            MapLocation mapLocation2 = database.getMapLocation(location.getId(), floor2.getFloorId());
                            linkLocations(mapLocation1, mapLocation2, DEFAULT_WEIGHT);
                        }
                    }
                }
            }
        }
    }

    /**
     * Links two map locations together with the given weight.
     */
    private void linkLocations(MapLocation location1, MapLocation location2, Double weight) {
        // No duplicate vertices will be added because the vertices are stored in a set
        if (!map.containsVertex(location1)){
            map.addVertex(location1);
        } else if (!map.containsVertex(location2)){
            map.addVertex(location2);
        }
        DefaultWeightedEdge edge = map.addEdge(location1, location2);
        map.setEdgeWeight(edge, weight);
    }

    /**
     * Calculates the weight between two locations.
     */
    private double calculateWeight(AbstractLocation location1, AbstractLocation location2) {
        //TODO: Decide on weight strategy in meeting
        return DEFAULT_WEIGHT;
    }

    private MapLocation roomCodeToMapLocation(String roomCode) {
        Room room = database.getRoom(roomCode);
        String floorID = room.getFloors().get(0).getFloorId(); //Pick the first floor the room is on
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
        DijkstraShortestPath<MapLocation, DefaultWeightedEdge> dijkstraAlg = new DijkstraShortestPath<>(map);
        GraphPath<MapLocation, DefaultWeightedEdge> route =
                dijkstraAlg.getPath(roomCodeToMapLocation(startRoomCode), roomCodeToMapLocation(endRoomCode));
        return route.getVertexList();
    }
}
