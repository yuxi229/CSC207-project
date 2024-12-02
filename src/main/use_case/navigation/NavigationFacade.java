package use_case.navigation;

import use_case.instructions.TextInstructionsInteractor;
import use_case.room_exists.RoomValidationInteractor;

public class NavigationFacade implements NavigationInputBoundary {
    private final RoomValidationInteractor roomValidationInteractor;
    private final NavigationInteractor navigationInteractor;
    private final TextInstructionsInteractor textInstructionsInteractor;

    public NavigationFacade(RoomValidationInteractor roomValidationInteractor,
                            NavigationInteractor navigationInteractor,
                            TextInstructionsInteractor textInstructionsInteractor) {
        this.roomValidationInteractor = roomValidationInteractor;
        this.navigationInteractor = navigationInteractor;
        this.textInstructionsInteractor = textInstructionsInteractor;
    }

    @Override
    public void execute(NavigationInputData navigationInputData) {
        roomValidationInteractor.execute(navigationInputData);
        navigationInteractor.execute(navigationInputData);
//        textInstructionsInteractor.execute();
    }
}