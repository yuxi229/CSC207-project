package use_case.favourites;

import java.util.List;

public class FavouritesInteractor implements FavouritesInputBoundary {

    private FavouritesDataAccess favouritesDataAccess;

    public FavouritesInteractor() {
        favouritesDataAccess = new FavouritesDataAccess();  // Initialize data access
    }

    // Method to add room code to favourites
    @Override
    public void addRouteToFavourites(FavouritesInputData favouritesInputData) {
        List<String> currentFavourites = favouritesDataAccess.loadFavourites();
        currentFavourites.add(departureRoom);  // Add new room code to the list
        favouritesDataAccess.saveFavourites(currentFavourites);  // Save the updated list
    }

    @Override
    // Method to get all favourite room codes
    public List<String> getFavouriteRooms() {
        return favouritesDataAccess.loadFavourites();  // Load and return the list of favourite rooms
    }
}
