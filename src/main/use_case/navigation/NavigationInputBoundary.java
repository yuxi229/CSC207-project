package use_case.navigation;

/**
 * Input Boundary for actions which are related to logging in.
 */

public interface NavigationInputBoundary {
    /**
     * Executes the navigation use case.
     * @param NavigationInputData the input data
     */
    void execute(NavigationInputData navigationInputData);
}
