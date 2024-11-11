package use_case.navigation;

/**
 * The Navigation Data Access Use Case.
 */
public interface NavigationDataAccessInterface {
    /**
     * Checks if the given roomcode exists.
     * @param roomcode the roomcode to look for
     * @return true if a room with the given roomcode exists; false otherwise
     */
    boolean existsByName(String roomcode);

}
