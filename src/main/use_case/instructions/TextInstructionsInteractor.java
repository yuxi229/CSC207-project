package use_case.instructions;

import java.util.List;

import use_case.navigation.maplocation.MapLocation;

/**
 * The interactor for the make instructions use case.
 */
public class TextInstructionsInteractor {
    private final InstructionsOutputBoundary presenter;

    public TextInstructionsInteractor(InstructionsOutputBoundary presenter) {
        this.presenter = presenter;
    }

    /**
     * Make the instructions and send it to the presenter.
     * @param path A list of map locations to make instructions for.
     */
    public void execute(List<MapLocation> path) {
        presenter.presentInstructions(new BasicTextInstructions(path));
    }
}
