package use_case.navigation.maplocation;

/**
 * A class that represents a location on the map.
 */
public class ImageMapLocation implements MapLocation {
    private final String locationID;
    private final double x;
    private final double y;
    private final int floorID;

    public ImageMapLocation(String locationID, double imgX, double imgY, int floorID) {
        this.locationID = locationID;
        this.x = imgX;
        this.y = imgY;
        this.floorID = floorID;
    }

    @Override
    public String getLocationID() {
        return locationID;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public int getFloorID() {
        return floorID;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof ImageMapLocation) {
            final ImageMapLocation other = (ImageMapLocation) o;
            result = this.locationID.equals(other.locationID)
                    && this.x == other.x && this.y == other.y && this.floorID == other.floorID;
        }
        return result;
    }

    @Override
    public int hashCode() {
        final String toString = locationID + String.valueOf(x) + String.valueOf(y) + String.valueOf(floorID);
        return toString.hashCode();
    }
}
