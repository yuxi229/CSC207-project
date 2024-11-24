package interface_adapter.inputrooms;

import interface_adapter.ViewManagerModel;
import interface_adapter.beginnavigation.BeginNavigationState;
import interface_adapter.beginnavigation.BeginNavigationViewModel;
import use_case.navigation.NavigationOutputBoundary;
import use_case.navigation.NavigationOutputData;

/**
 * The Presenter for the Navigation Use Case.
 */
public class InputRoomsPresenter implements NavigationOutputBoundary {

    private final InputRoomsViewModel inputRoomsViewModel;
    private final BeginNavigationViewModel beginNavigationViewModel;
    private final ViewManagerModel viewManagerModel;

    public InputRoomsPresenter(ViewManagerModel viewManagerModel, BeginNavigationViewModel beginNavigationViewModel,
                               InputRoomsViewModel inputRoomsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.beginNavigationViewModel = beginNavigationViewModel;
        this.inputRoomsViewModel = inputRoomsViewModel;
    }

    @Override
    public void prepareSuccessView(NavigationOutputData response) {
        // On success, switch to the begin navigation view.

        final BeginNavigationState beginNavigationState = beginNavigationViewModel.getState();
        beginNavigationState.setDepartureRoom(response.getDepartureRoomCode());
        beginNavigationState.setDestinationRoom(response.getDestinationRoomCode());
        this.beginNavigationViewModel.setState(beginNavigationState);
        this.beginNavigationViewModel.firePropertyChanged();

        this.viewManagerModel.setState(beginNavigationViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final InputRoomsState inputRoomsState = inputRoomsViewModel.getState();
        if (error.contains("departure")) {
            inputRoomsState.setDepartureRoom(error);
        }
        else if (error.contains("destination")) {
            inputRoomsState.setDestinationRoom(error);
        }
        else {
            inputRoomsState.setDepartureRoom(error);
            inputRoomsState.setDestinationRoom(error);
        }
        inputRoomsViewModel.firePropertyChanged();
    }
}

