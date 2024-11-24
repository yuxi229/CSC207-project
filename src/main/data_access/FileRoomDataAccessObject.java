package data_access;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import entity.Room;
import use_case.navigation.NavigationDataAccessInterface;

/**
 * DAO for room data implemented using a File to persist the data.
 */
public class FileRoomDataAccessObject implements NavigationDataAccessInterface {

    private static final String HEADER = "Departure room,Destination room";

    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, Room> roomcodes = new HashMap<>();
    private String roomCode;

    @Override
    public boolean existsByRoomCode(String roomCode) {
        return false;
    }

    @Override
    public void save(Room room) {

    }

    @Override
    public Room getRoomCode(String roomCode) {
        return null;
    }

    public Room get(String roomCode) {
        return null;
    }
}