package use_case.navigation.maplocation;

/**
 * Factory for creating ImageMapLocation objects.
 */
public class ImageMapLocationFactory implements MapLocationFactory {
    @Override
    public MapLocation createMapLocation(String id, double mapX, double mapY, int floor) {
        return new ImageMapLocation(id, mapX, mapY, floor);
    }
}
