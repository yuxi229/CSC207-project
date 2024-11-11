package use_case.navigation;

import use_case.navigation.NavigationOutputData;
/**
 * Output boundary for the Navigation Use Case.
 */
public interface NavigationOutputBoundary {
    /**
     * Prepares the success view for the Navigation Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(NavigationOutputData outputData);

    /**
     * Prepares the failure view for the Navigation Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
