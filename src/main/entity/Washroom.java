package entity;

import java.util.List;

/**
 * Represents a washroom in the building.
 */
public class Washroom extends AbstractSingleFloorLocation {
    private final String gender;

    public Washroom(String id, List<String> connected, int size, int floor, String gender) {
        super(id, connected, size, floor);
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
