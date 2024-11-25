package entity;

import java.util.ArrayList;

/**
 * A floor implementation that inherits Location.
 */

class Floor extends Location {
    private ArrayList<Room> roomsList = new ArrayList<>();
    private ArrayList<Stairs> stairsList = new ArrayList<>();
    private ArrayList<Corridor> corridorsList = new ArrayList<>();

    public Floor(String id) {
        super(id);
    }

    public ArrayList<Room> getRoomsList() {
        return roomsList;
    }

    public void addRoom(Room room) {
        roomsList.add(room);
    }

    public ArrayList<Stairs> getStairsList() {
        return stairsList;
    }

    public void addStairs(Stairs stairs) {
        stairsList.add(stairs);
    }

    public ArrayList<Corridor> getCorridorsList() {
        return corridorsList;
    }

    public void addCorridor(Corridor corridor) {
        corridorsList.add(corridor);
    }
}
