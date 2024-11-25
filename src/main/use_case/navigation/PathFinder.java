package use_case.navigation;

import use_case.LocationDataAccessInterface;

import java.util.List;

public interface PathFinder {
    /**
     * Initializes the pathfinder with the given data.
     * @param inMemoryDAO The data access object to use.
     */
    void loadData(LocationDataAccessInterface inMemoryDAO);

    /**
     * Returns the path from the start room to the end room as a list of ids.
     * @param startRoomCode The room code of the room to start at.
     * @param endRoomCode The room code of the room to end at.
     * @return A list of MapLocation objects representing the path.
     */
    List<MapLocation> getPath(String startRoomCode, String endRoomCode);
}
