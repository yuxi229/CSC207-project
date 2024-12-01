package use_case.navigation;

import use_case.room_exists.RoomValidationInteractor;

public class NavigationFacade implements NavigationInputBoundary {

    private final NavigationInputData navigationInputData;
    private RoomValidationInteractor roomValidationInteractor;
    private NavigationInteractor navigationInteractor;
    private TextInstructionsInteractor textInstructionsInteractor;

    public NavigationFacade(NavigationInputData navigationInputData) {
        this.navigationInputData = navigationInputData;
    }

    public void setRoomValidationInteractor (RoomValidationInteractor roomValidationInteractor) {
        this.roomValidationInteractor = roomValidationInteractor;
    }

    public void setNavigationInteractor (NavigationInteractor navigationInteractor) {
        this.navigationInteractor = navigationInteractor;
    }

    public void setTextInstructionsInteractor (TextInstructionsInteractor textInstructionsInteractor ) {
        this.textInstructionsInteractor = textInstructionsInteractor;
    }


    @Override
    public void execute(NavigationInputData navigationInputData) {
        this.setNavigationInteractor(navigationInteractor);
        this.setRoomValidationInteractor(roomValidationInteractor);
        this.setTextInstructionsInteractor(textInstructionsInteractor);
        navigationInteractor.execute(navigationInputData);
        roomValidationInteractor.execute(navigationInputData);
        textInstructionsInteractor.execute(navigationInputData);
    }
}
