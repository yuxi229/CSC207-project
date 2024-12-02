package use_case.favourites;

import entity.User;

import java.util.List;

/**
 * This is the interactor for save favourites.
 */

public class FavouritesInteractor implements FavouritesInputBoundary {

    private final FavouritesDataAccessInterface favouritesDataAccessInterface;

    public FavouritesInteractor(FavouritesDataAccessInterface favouritesDataAccessInterface) {
        this.favouritesDataAccessInterface = favouritesDataAccessInterface;
    }

    @Override
    public boolean addFavourite(User user, String roomCode) {
        return user.addFavourite(roomCode);
    }

    public boolean removeFavourite(User user, String roomCode) {
        return user.removeFavourite(roomCode);
    }

    // Checks if the roomCode is already a favourite.
    public boolean isFavourite(User user, String roomCode) {
        if (user.getFavourites().contains(roomCode)) {
            return true;
        }
        return false;
    }

    public List<String> getFavourites(User user) {
        return user.getFavourites();
    }

}
