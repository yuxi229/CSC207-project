package interface_adapter.inputrooms;

import use_case.favourites.FavouritesOutputBoundary;
import use_case.favourites.FavouritesOutputData;
import view.InputRoomsView;

import java.beans.PropertyChangeSupport;
import java.util.List;

public class FavouritesPresenter implements FavouritesOutputBoundary {

    @Override
    public List<String> presentFavourites(FavouritesOutputData data) {
        return data.getFavouriteRooms();
    }

}
