package use_case.navigation;

public class ImageMapLocation implements MapLocation {
    private final String locationID;
    private final double x;
    private final double y;
    private final int floor;

    public ImageMapLocation(String locationID, double x, double y, int floor) {
        this.locationID = locationID;
        this.x = x;
        this.y = y;
        this.floor = floor;
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
    public int getFloor() {
        return floor;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ImageMapLocation)) {
            return false;
        } else {
                ImageMapLocation other = (ImageMapLocation) o;
                return this.locationID.equals(other.locationID)
                        && this.x == other.x
                        && this.y == other.y
                        && this.floor == other.floor;
            }
        }
}
