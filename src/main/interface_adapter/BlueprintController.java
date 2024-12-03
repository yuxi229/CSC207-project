package interface_adapter;

import java.util.List;

import use_case.BlueprintSelectionInteractor;

/**
 * Controller for managing blueprint selection.
 */
public class BlueprintController {
    private final BlueprintSelectionInteractor interactor;
    private final BlueprintViewModel viewModel;

    /**
     * Constructs a BlueprintController with the specified interactor and view model.
     *
     * @param interactor The interactor responsible for blueprint-related use cases.
     * @param viewModel  The view model to update the blueprint-related UI state.
     */
    public BlueprintController(BlueprintSelectionInteractor interactor, BlueprintViewModel viewModel) {
        this.interactor = interactor;
        this.viewModel = viewModel;
    }

    /**
     * Initializes the list of available blueprints and updates the view model.
     */
    public void initializeBlueprints() {
        final List<String> blueprints = interactor.fetchAvailableBlueprints();
        viewModel.setBlueprintList(blueprints);
    }

    /**
     * Handles the event of a blueprint being selected.
     *
     * @param blueprintName The name of the selected blueprint.
     */
    public void onBlueprintSelected(String blueprintName) {
        interactor.switchBlueprint(blueprintName);
        viewModel.setCurrentBlueprint(blueprintName);
    }
}
