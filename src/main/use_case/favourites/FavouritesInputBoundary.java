package use_case.favourites;

import java.util.List;

public interface FavouritesInputBoundary {

    public void addRouteToFavourites(FavouritesInputData favouritesInputData);

    public List<String> getFavouriteRooms();
}
