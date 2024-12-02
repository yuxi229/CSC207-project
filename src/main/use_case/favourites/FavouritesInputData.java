package use_case.favourites;

/**
 * This is the input data for saving favourites.
 * EXTEND
 */

public class FavouritesInputData implements FavouritesInputBoundary {
    private final String;

    public FavouritesInputData(String roomCode) {
        this.roomCode = roomCode;
    }


}
