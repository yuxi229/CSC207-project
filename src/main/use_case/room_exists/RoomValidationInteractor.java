package use_case.room_exists;

import data_access.LocationDataAccess;
import use_case.navigation.NavigationInputBoundary;
import use_case.navigation.NavigationInputData;
import use_case.navigation.NavigationOutputBoundary;

public class RoomValidationInteractor implements NavigationInputBoundary {
    private final LocationDataAccess locationDAO;
    private final NavigationOutputBoundary naviPresenter;

    // TODO: Make output boundary and presenter and pass it in as a parameter
    public RoomValidationInteractor(LocationDataAccess locationDao, ) {
        this.locationDAO = locationDao;
        this.naviPresenter = ; //TODO: put presenter here
    }


    @Override
    public void execute(NavigationInputData navigationInputData) {
        //TODO: Implement this method accoriding to how the output boundary is designed!

    }
}
