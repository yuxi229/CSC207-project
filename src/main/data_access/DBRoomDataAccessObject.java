package data_access;

import entity.Room;
import entity.RoomFactory;
import use_case.navigation.NavigationDataAccessInterface;

/**
 * The DAO for room data.
 */
public class DBRoomDataAccessObject implements NavigationDataAccessInterface {
    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String ROOMCODE = "roomcode";
    private static final String MESSAGE = "message";
    private final RoomFactory roomFactory;

    public DBRoomDataAccessObject(RoomFactory roomFactory) {
        this.roomFactory = roomFactory;
    }

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