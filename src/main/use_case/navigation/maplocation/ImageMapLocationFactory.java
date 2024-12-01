package use_case.navigation.maplocation;

/**
 * Factory for creating ImageMapLocation objects.
 */
public class ImageMapLocationFactory implements MapLocationFactory {
    @Override
    public MapLocation createMapLocation(String id, int mapX, int mapY, int floor) {
        return new ImageMapLocation(id, mapX, mapY, floor);
    }
}
