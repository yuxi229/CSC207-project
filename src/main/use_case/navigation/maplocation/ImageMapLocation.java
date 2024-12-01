package use_case.navigation.maplocation;

import java.util.Objects;

/**
 * A class that represents a location on the map.
 */
public class ImageMapLocation implements MapLocation {
    private final String locationID;
    private final int x;
    private final int y;
    private final int floorID;

    public ImageMapLocation(String locationID, int imgX, int imgY, int floorID) {
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
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getFloor() {
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
        return Objects.hash(locationID, x, y, floorID);
    }
}
