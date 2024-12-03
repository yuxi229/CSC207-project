package use_case.favourites;

import java.util.List;

public class FavoritesOutputData {

    private List<String> favouriteRoomCodes;

    public FavoritesOutputData(List<String> favouriteRoomCodes) {
        this.favouriteRoomCodes = favouriteRoomCodes;
    }

    public List<String> getFavouriteRoomCodes() {
        return favouriteRoomCodes;
    }
}
