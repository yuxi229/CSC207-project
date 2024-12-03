package use_case;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the business logic for blueprint selection.
 */
public class BlueprintSelectionInteractor {
    private final List<String> blueprints = new ArrayList<>();
    private String activeBlueprint;

    public BlueprintSelectionInteractor() {
        // Initialize with some dummy data
        blueprints.add("floor1.jpg");
        blueprints.add("floor2.jpg");
    }

    /**
     * Fetches the list of available blueprints.
     *
     * @return A copy of the list of available blueprints.
     */

    public List<String> fetchAvailableBlueprints() {
        // Return a copy to prevent modification
        return new ArrayList<>(blueprints);
    }

    /**
     * Switches to the specified blueprint if it exists in the list of available blueprints.
     *
     * @param blueprintName The name of the blueprint to switch to.
     * @throws IllegalArgumentException If the specified blueprint does not exist.
     */
    public void switchBlueprint(String blueprintName) {
        if (blueprints.contains(blueprintName)) {
            activeBlueprint = blueprintName;
        }
        else {
            throw new IllegalArgumentException("Blueprint not found: " + blueprintName);
        }
    }

    public String getActiveBlueprint() {
        return activeBlueprint;
    }
}
