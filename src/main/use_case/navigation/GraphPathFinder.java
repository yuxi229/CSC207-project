package use_case.navigation;

import java.util.List;

import entity.Corridor;
import entity.Room;
import entity.Stairs;

import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

public class GraphPathFinder implements PathFinder {
    private SimpleWeightedGraph<String, DefaultWeightedEdge> map = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
    private static final double DEFAULT_WEIGHT = 1.0;

    /**
     * Default constructor. Initializes the pathfinder with no data.
     */
    public GraphPathFinder() {
    }

    /**
     * Constructor that initializes the pathfinder with the given data.
     * @param inMemoryDAO
     */
    public GraphPathFinder(NavigationDataAccessInterface inMemoryDAO) {
        loadData(inMemoryDAO);
    }

    /**
     * Initializes the pathfinder with the given data.
     * @param inMemoryDAO
     */
    @Override
    public void loadData(NavigationDataAccessInterface inMemoryDAO) {
        List<Corridor> corridors = inMemoryDAO.getCorridors();

        // Loop through all corridors and add them to the graph
        for (Corridor corridor : corridors) {
            map.addVertex(corridor.getId());

            // Connect the corridor to rooms
            for (Room room : corridor.getRoomList()) {
                //TODO: Decide on weight strategy in meeting
                addLocation(room.getId(), corridor.getId(), DEFAULT_WEIGHT);
            }

            // Connect the corridor to other corridors
            for (Corridor otherCorridor : corridor.getConnectedCorridors()) {
                //TODO: Decide on weight strategy in meeting
                addLocation(otherCorridor.getId(), corridor.getId(), DEFAULT_WEIGHT);
            }

            // Connect the corridor to stairs
            for (Stairs stair : corridor.getStairsList()) {
                //TODO: Decide on weight strategy in meeting
                addLocation(stair.getId(), corridor.getId(), DEFAULT_WEIGHT);
            }
        }
    }

    private void addLocation(String locationID, String corridorID, Double weight) {
        if (!map.containsVertex(locationID)){
            map.addVertex(locationID);
        }
        DefaultWeightedEdge edge = map.addEdge(corridorID, locationID);
        map.setEdgeWeight(edge, weight);
    }


    /**
     * Returns the path from the start room to the end room as a list of ids.
     * @param startRoomCode valid room code for starting room
     * @param endRoomCode valid room code for ending room
     * @return
     */
    @Override
    public List<String> getPath(String startRoomCode, String endRoomCode) {
        // Use Dijkstra's algorithm to find the shortest path. Change algorithm if needed.
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraAlg = new DijkstraShortestPath<>(map);
        GraphPath route = dijkstraAlg.getPath(startRoomCode, endRoomCode);
        return route.getVertexList();
    }
}
