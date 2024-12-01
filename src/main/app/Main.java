package app;

import javax.swing.JFrame;

//import api_infrastructure.LoadApiDataFacade;
//import data_access.LocationDataAccess;
//import data_access.MapLocationDataAccess;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {

//        // Load the data from the API
//        LoadApiDataFacade.loadDataIntoMemory();
//        final LocationDataAccess locationDataAccess = LoadApiDataFacade.getLocationDao();
//        final MapLocationDataAccess mapLocationDataAccess = LoadApiDataFacade.getMapLocationDao();

        // Build the application
        final AppBuilder appBuilder = new AppBuilder();

        // Load all data into memory
        appBuilder.loadData();
        final JFrame application = appBuilder
                .addNavigationView()
                .addBlueprintSelectionView()
                .addNavigationUseCase()
                .build();

        // Add the use cases to the application
        appBuilder.addNavigationView();

        // Build the application
        appBuilder.build();
    }
}
