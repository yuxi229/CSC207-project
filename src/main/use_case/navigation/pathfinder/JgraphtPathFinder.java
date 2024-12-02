package use_case.navigation.pathfinder;

import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import data_access.LocationDataAccess;
import data_access.MapLocationDataAccess;
import entity.Location;
import entity.Room;
import use_case.navigation.maplocation.MapLocation;

/**
 * A class that finds the shortest path between two locations on the map.
 */
public class JgraphtPathFinder implements PathFinder {
    private static final double DEFAULT_WEIGHT = 1.0;
    private final SimpleWeightedGraph<MapLocation, DefaultWeightedEdge> map =
            new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
    private LocationDataAccess locationDao;
    private MapLocationDataAccess mapLocationDao;

    /**
     * Default constructor. Initializes the pathfinder with no data.
     */
    JgraphtPathFinder() {
    }

    /**
     * Constructor that initializes the pathfinder with the given data.
     * @param locationDao the data access object to use for locations
     * @param mapLocationDao the map location data access object to use for map locations
     */
    JgraphtPathFinder(LocationDataAccess locationDao, MapLocationDataAccess mapLocationDao) {
        loadData(locationDao, mapLocationDao);
    }

    @Override
    public void loadData(LocationDataAccess entityDao, MapLocationDataAccess mapDao) {
        this.locationDao = entityDao;
        this.mapLocationDao = mapDao;
        for (int floor: this.locationDao.getFloors()) {
            buildFloor(floor);
        }
        linkFloors();
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

    /**
     * Calculates the weight between two locations.
     * @param location1 the first location
     * @param location2 the second location
     * @return the weight between the two locations
     */
    protected Double calculateWeight(Location location1, Location location2) {
        return DEFAULT_WEIGHT;
    }

    /**
     * Returns the map used by the pathfinder, used only by subclasses.
     * @return the map used by the pathfinder
     */
    protected SimpleWeightedGraph<MapLocation, DefaultWeightedEdge> getMap() {
        return map;
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
                final Location location2 = locationDao.getLocation(location2Id);
                //TODO: Discuss stairs that only have one floor
                if (locationDao.idExists(location2Id) && location2.getFloors().contains(floor)) {
                    final MapLocation mapLocation2 = mapLocationDao.getMapLocation(location2Id, floor);
                    linkLocations(mapLocation1, mapLocation2, calculateWeight(location1, location2));
                }
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
                        linkLocations(mapLocation1, mapLocation2, calculateWeight(location, location));
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

        // Check if an edge exist already
        if (!map.containsEdge(location1, location2)) {
            final DefaultWeightedEdge edge = map.addEdge(location1, location2);
            map.setEdgeWeight(edge, weight);
        }
    }

    /**
     * Helper function that converts a room code to a map location.
     * @param roomCode the room code to convert
     * @return the map location corresponding to the room code
     */
    private MapLocation roomCodeToMapLocation(String roomCode) {
        final Room room = locationDao.getRoom(roomCode);
        return mapLocationDao.getMapLocation(room.getId(), room.getFloor());
    }
}
