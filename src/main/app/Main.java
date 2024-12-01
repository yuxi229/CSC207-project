package app;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();

        // Load all data into memory
        appBuilder.loadData();

        // Add the use cases to the application
        appBuilder.addNavigationView();

        // Build the application
        appBuilder.build();
    }
}
