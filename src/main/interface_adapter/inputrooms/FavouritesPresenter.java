package interface_adapter.inputrooms;

import use_case.favourites.FavouritesOutputBoundary;

public class FavouritesPresenter implements FavouritesOutputBoundary {
    private final FavouritesView view;

    public FavouritesPresenter(FavouritesView view) {
        this.view = view;
    }

    @Override
    public void presentFavourites(FavouritesOutputData outputData) {
        if (outputData.isSuccess()) {
            view.showSuccessMessage("Room added to favourites!");
            view.showFavourites(outputData.getFavouriteRooms());
        } else {
            view.showErrorMessage("Failed to add room to favourites.");
        }
    }
}