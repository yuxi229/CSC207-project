package use_case.favourites;

import entity.User;

import java.util.List;

/**
 * This is the input boundary for saving favourites.
 * This series of files implements the saving functionality, so that certain rooms can be saved,
 * and quickly accessed again.
 *
 */

public interface FavouritesInputBoundary {

    /**
     * This method executes the use cases for saving favourites.
     */

    boolean addFavourite(User user, String roomCode);
    boolean removeFavourite(User user, String roomCode);
    boolean isFavourite(User user, String roomCode);
    List<String> getFavourites(User user);

}
