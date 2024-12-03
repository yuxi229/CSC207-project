package use_case.favourites;

import data_access.FavouritesDataAccess;

import java.util.List;

public class FavouritesInteractor implements FavouritesInputBoundary {

    private FavouritesDataAccess favouritesDataAccess;

    public FavouritesInteractor() {
        favouritesDataAccess = new FavouritesDataAccess();  // Initialize data access
    }

    // Method to add room code to favourites
    @Override
    public void addRouteToFavourites(FavouritesInputData favouritesInputData) {
        favouritesDataAccess.saveFavourites(favouritesInputData.getDepartureRoomCode() + "," + favouritesInputData.getDestinationRoomCode());
    }

    @Override
    // Method to get all favourite room codes
    public FavouritesOutputData getFavouriteRooms() {
        return new FavouritesOutputData(favouritesDataAccess.loadFavourites());
    }
}
