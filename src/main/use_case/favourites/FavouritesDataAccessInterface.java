package use_case.favourites;

import java.util.List;

public interface FavouritesDataAccessInterface {
    List<String> loadFavourites();  // Load favourites from the data source
    void saveFavourites(List<String> favourites);  // Save favourites to the data source
}

