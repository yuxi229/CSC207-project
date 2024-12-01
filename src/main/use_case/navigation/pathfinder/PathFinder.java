package use_case.navigation.pathfinder;

import java.util.List;

import data_access.LocationDataAccess;
import data_access.MapLocationDataAccess;
import use_case.navigation.maplocation.MapLocation;

/**
 * A pathfinder that can find the shortest path between two rooms.
 */
public interface PathFinder {
    /**
     * Initializes the pathfinder with the given data.
     * @param inMemoryDao The data access object to use for locations.
     * @param mapLocationDao The data access object to use for map locations.
     */
    void loadData(LocationDataAccess inMemoryDao, MapLocationDataAccess mapLocationDao);

    /**
     * Returns the path from the start room to the end room as a list of ids.
     * @param startRoomCode The room code of the room to start at.
     * @param endRoomCode The room code of the room to end at.
     * @return A list of MapLocation objects representing the path.
     */
    List<MapLocation> getPath(String startRoomCode, String endRoomCode);
}
