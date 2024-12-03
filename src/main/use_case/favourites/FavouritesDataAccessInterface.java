package use_case.favourites;

import java.util.List;

public interface FavouritesDataAccessInterface {
    List<String> loadFavourites();  // Load favourites from the data source
    // Save favourites to the data source
    void saveFavourites(String roomCodes);
}

