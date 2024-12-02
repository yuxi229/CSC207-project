package api_infrastructure;

import java.util.Map;

/**
 * Interface for interacting with the building data API.
 */
public interface ApiCilent {
    // TODO: Discuss wrapping the return types in a proxy object to avoid implicit dependency via casting.

    /**
     * TODO: javadoc comment.
     * @return
     */
    Map<String, Object> fetchRooms();

    /**
     * TODO: javadoc comment.
     * @return
     */
    Map<String, Object> fetchCorridors();

    /**
     * TODO: javadoc comment.
     * @return
     */
    Map<String, Object> fetchWashrooms();

    /**
     * TODO: javadoc comment.
     * @return
     */
    Map<String, Object> fetchValves();

    /**
     * TODO: javadoc comment.
     * @return
     */
    Map<String, Object> fetchStairs();

    /**
     * TODO: javadoc comment.
     * @return
     */
    Map<String, Object> fetchElevators();
}
