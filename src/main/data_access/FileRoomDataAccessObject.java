package data_access;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import entity.Room;
import entity.RoomFactory;
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
    public Room get(String roomCode) {
        return null;
    }
}
