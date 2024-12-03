package use_case.favourites;

import java.util.List;

public interface FavouritesOutputBoundary {
    List<String> presentFavourites(FavouritesOutputData outputData);

}