package use_case.favourites;

import data_access.FavouritesDataAccess;

import java.util.List;

public class FavouritesInteractor implements FavouritesInputBoundary {

    private FavouritesDataAccess favouritesDataAccess;
    private FavouritesOutputBoundary favouritesOutputBoundary;

    public FavouritesInteractor() {
        favouritesDataAccess = new FavouritesDataAccess();  // Initialize data access
    }

    // Method to add room code to favourites
    @Override
    public void addRouteToFavourites(FavouritesInputData favouritesInputData) {
        favouritesDataAccess.saveFavourites(favouritesInputData.getDepartureRoomCode() + "," + favouritesInputData.getDestinationRoomCode());

        FavouritesOutputData favouritesOutputData = new FavouritesOutputData(favouritesDataAccess.loadFavourites());

        favouritesOutputBoundary.presentFavourites(favouritesOutputData);
    }

    @Override
    // Method to get all favourite room codes
    public void getFavouriteRooms() {
        FavouritesOutputData favouritesOutputData = new FavouritesOutputData(favouritesDataAccess.loadFavourites());

        favouritesOutputBoundary.presentFavourites(favouritesOutputData);
    };
}
