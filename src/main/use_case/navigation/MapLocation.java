package use_case.navigation;

public interface MapLocation {
    String getLocationID();
    double getX();
    double getY();
    int getFloor();
    @Override
    boolean equals(Object o);
}
