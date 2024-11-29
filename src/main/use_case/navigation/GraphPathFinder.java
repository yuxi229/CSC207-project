package use_case.navigation;

import java.util.List;

import data_access.MapLocationDataAccess;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import data_access.LocationDataAccess;
import entity.Location;
import entity.Room;
import use_case.navigation.MapLocation.MapLocation;

/**
 * A class that finds the shortest path between two locations on the map.
 */
public class GraphPathFinder implements PathFinder {
    private static final double DEFAULT_WEIGHT = 1.0;
    private final SimpleWeightedGraph<MapLocation, DefaultWeightedEdge> map =
            new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
    private LocationDataAccess locationDao;
    private MapLocationDataAccess mapLocationDao;

    /**
     * Default constructor. Initializes the pathfinder with no data.
     */
    public GraphPathFinder() {
    }

    /**
     * Constructor that initializes the pathfinder with the given data.
     * @param locationDao the data access object to use for locations
     * @param mapLocationDao the map location data access object to use for map locations
     */
    public GraphPathFinder(LocationDataAccess locationDao, MapLocationDataAccess mapLocationDao) {
        this.locationDao = locationDao;
        this.mapLocationDao = mapLocationDao;
    }

    /**
     * Initializes the pathfinder with the given data.
     * @param inMemoryDao the data access object to use
     */
    @Override
    public void loadData(LocationDataAccess inMemoryDao) {
        locationDao = inMemoryDao;
        for (int floor: locationDao.getFloorIds()) {
            buildFloor(floor);
        }
        linkFloors();
    }

    /**
     * Go through all the locations on the floor and link them together.
     * @param floor the floor to build the graph for
     */
    private void buildFloor(int floor) {
        // Loop through all the locations on the floor
        for (Location location1 : locationDao.getLocations(floor)) {
            final String location1Id = location1.getId();
            final MapLocation mapLocation1 = mapLocationDao.getMapLocation(location1Id, floor);

            // Link the location to all the locations it is connected to
            for (String location2Id : location1.getConnectedLocations()) {
                final MapLocation mapLocation2 = mapLocationDao.getMapLocation(location2Id, floor);
                linkLocations(mapLocation1, mapLocation2, calculateWeight(location1, location2Id));
            }
        }
    }

    /**
     * Go through all the locations that span multiple floors and link them together.
     */
    private void linkFloors() {
        for (Location location : locationDao.getLocations()) {
            final List<Integer> floorsConnected = location.getFloors();
            for (int floor1Id : floorsConnected) {
                for (int floor2Id : floorsConnected) {
                    if (floor1Id != floor2Id) {
                        final MapLocation mapLocation1 = mapLocationDao
                                .getMapLocation(location.getId(), floor1Id);
                        final MapLocation mapLocation2 = mapLocationDao
                                .getMapLocation(location.getId(), floor2Id);
                        linkLocations(mapLocation1, mapLocation2, DEFAULT_WEIGHT);
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
        map.addVertex(location1);
        map.addVertex(location2);
        final DefaultWeightedEdge edge = map.addEdge(location1, location2);
        map.setEdgeWeight(edge, weight);
    }

    /**
     * Calculates the weight between two locations.
     * @param location1 the first location
     * @param location2 the second location
     * @return the weight between the two locations
     */
    private double calculateWeight(Location location1, String location2) {
        // TODO: Decide on weight strategy in meeting
        return DEFAULT_WEIGHT;
    }

    private MapLocation roomCodeToMapLocation(String roomCode) {
        final Room room = locationDao.getRoom(roomCode);
        return mapLocationDao.getMapLocation(room.getId(), room.getFloor());
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
