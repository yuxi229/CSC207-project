package interface_adapter.beginnavigation;

import use_case.navigation.maplocation.MapLocation;
import java.util.List;

/**
 * The State information representing the start navigation page.
 */
public class BeginNavigationState {
    private String departure = "";
    private String destination = "";
    private List<MapLocation> path;  // New field to store the path

    // Default constructor
    public BeginNavigationState() {
    }

    // Copy constructor
    public BeginNavigationState(BeginNavigationState copy) {
        this.departure = copy.departure;
        this.destination = copy.destination;
        this.path = copy.path;  // Copy the path as well
    }

    // Getters
    public String getDepartureRoom() {
        return departure;
    }

    public String getDestinationRoom() {
        return destination;
    }

    public List<MapLocation> getPath() {
        return path;
    }

    // Setters
    public void setDepartureRoom(String departure) {
        this.departure = departure;
    }

    public void setDestinationRoom(String destination) {
        this.destination = destination;
    }

    // New method to set the path
    public void setPath(List<MapLocation> path) {
        this.path = path;  // Set the path with the list of MapLocations
    }
}
