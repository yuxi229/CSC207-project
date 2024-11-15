package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.Room;
import use_case.navigation.NavigationDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing navigation data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryRoomDataAccessObject implements NavigationDataAccessInterface{
    private final Map<String, Room> rooms = new HashMap<>();

    private String roomCode;

    @Override
    public boolean existsByroomCode(String identifier) {
        return rooms.containsKey(identifier);
    }

    @Override
    public Room get(String roomCode) {
        return rooms.get(roomCode);
    }

}
