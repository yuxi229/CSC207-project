package main.use_case.navigation;

public interface MapLocation {
    String getLocationID();
    double getX();
    double getY();
    String getFloorID();
    @Override
    boolean equals(Object o);
}
