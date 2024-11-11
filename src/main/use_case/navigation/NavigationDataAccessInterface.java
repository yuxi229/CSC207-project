package use_case.navigation;

import entity.Room;

/**
 * The Navigation Data Access Use Case.
 */
public interface NavigationDataAccessInterface {
    /**
     * Checks if the given roomCode exists.
     * @param roomCode the roomCode to look for
     * @return true if a room with the given roomCode exists; false otherwise
     */
    boolean existsByName(String roomCode);

    /**
     * Returns the room with the given roomCode.
     * @param roomCode the roomCode to look up
     * @return the room with the given roomCode
     */
    Room get(String roomCode);

    /**
     * Returns the roomCode of the curren room of the application.
     * @return the roomCode of the current room; null indicates that no one is logged into the application.
     */
    String getCurrentRoomCode();

}
