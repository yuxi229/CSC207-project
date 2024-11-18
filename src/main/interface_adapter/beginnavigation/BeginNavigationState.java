package interface_adapter.beginnavigation;

/**
 * The State information representing the start navigation page.
 */
public class BeginNavigationState {
    private String departure = "";
    private String destination = "";

    public BeginNavigationState(BeginNavigationState copy) {
        departure = copy.departure;
        destination = copy.destination;
    }

    public BeginNavigationState() {

    }

    public String getDepartureRoom() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDepartureRoom(String departure) {
        this.departure = departure;
    }

    public void setDestinationRoom(String destination) {
        this.destination = destination;
    }
}
