package entity;

import java.util.List;

/**
 * Represents a washroom in the building.
 */
public class Washroom extends Location {
    private String gender;        // Gender designation (e.g., Male, Female, Unisex)
    private List<String> connected; // List of connected location IDs

    public Washroom(String id, int size, int floor, int imgXpos, int imgYpos, String gender, List<String> connected) {
        super(id, size, floor, imgXpos, imgYpos);
        this.gender = gender;
        this.connected = connected;
    }

    public String getGender() { return gender; }
    public List<String> getConnected() { return connected; }

    @Override
    public String toString() {
        return "Washroom{" +
                "gender='" + gender + '\'' +
                ", connected=" + connected +
                "} " + super.toString();
    }
}
