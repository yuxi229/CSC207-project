package use_case.favourites;

public class FavouritesInteractor implements FavouritesInputBoundary {
    private final FavouritesDataAccessInterface favouritesDataAccess;
    private final FavouritesOutputBoundary outputBoundary;

    public FavouritesInteractor(FavouritesDataAccessInterface dataAccess, FavouritesOutputBoundary outputBoundary) {
        this.favouritesDataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void addFavourite(AddFavouriteInputData inputData) {
        // Business logic
        boolean success = false;
        String message;

        List<String> favourites = favouritesDataAccess.getFavourites(inputData.getUserId());
        if (favourites.contains(inputData.getRouteId())) {
            message = "Route is already in favourites.";
        } else {
            favourites.add(inputData.getRouteId());
            favouritesDataAccess.saveFavourites(inputData.getUserId(), favourites);
            success = true;
            message = "Route added successfully.";
        }

        // Create OutputData and pass to OutputBoundary
        AddFavouriteOutputData outputData = new AddFavouriteOutputData(success, message);
        outputBoundary.presentAddFavouriteResult(outputData);
    }
}
