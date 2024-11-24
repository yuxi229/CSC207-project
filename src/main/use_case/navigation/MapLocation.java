package use_case.navigation;

public interface MapLocation {
    public String getLocationID();
    public double getX();
    public double getY();
    public int getFloor();

    @Override
    public boolean equals(Object o);
}
