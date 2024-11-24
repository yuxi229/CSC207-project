package use_case.navigation;

import java.util.List;

public interface PathFinder {
    /**
     * Initializes the pathfinder with the given data.
     * @param inMemoryDAO
     */
    public void loadData(NavigationDataAccessInterface inMemoryDAO);

    /**
     * Returns the path from the start room to the end room as a list of ids.
     * @param startRoomCode
     * @param endRoomCode
     * @return
     */
    public List<String> getPath(String startRoomCode, String endRoomCode);
}
