package app;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        // Build the application
        final AppBuilder appBuilder = new AppBuilder();

        // Load all data into memory
        appBuilder.loadData();

        // Add the use cases to the application
        appBuilder.addNavigationView();
        appBuilder.addBlueprintSelectionView();

        // Build the application
        appBuilder.build();
    }
}
