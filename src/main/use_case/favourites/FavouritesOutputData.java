package use_case.favourites;

import java.util.List;

public class FavouritesOutputData {
    private boolean success;
    private List<String> favouriteRooms;

    // Constructor
    public FavouritesOutputData(List<String> favouriteRooms) {
        this.favouriteRooms = favouriteRooms;
    }

    public List<String> getFavouriteRooms() {
        return favouriteRooms;
    }
}