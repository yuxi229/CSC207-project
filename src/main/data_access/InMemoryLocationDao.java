package data_access;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.*;
import use_case.LocationDataAccessInterface;
import use_case.navigation.MapLocation;

/**
 * In-memory implementation of the DAO for storing navigation data.
 * This implementation does NOT persist data between runs of the program.
 */
public class InMemoryLocationDao implements LocationDataAccessInterface {

    private final Map<String, Location> singleFloorLocationMap = new HashMap<>();
    private final Map<String, MultiFloorLocation> multiFloorLocationMap = new HashMap<>();
    private final Map<String, Room> roomMap = new HashMap<>();
    private final Map<String, Corridor> corridorMap = new HashMap<>();
    private final Map<String, Washroom> washroomMap = new HashMap<>();
    private final Map<String, Valve> valveMap = new HashMap<>();
    private final Map<String, Stair> stairMap = new HashMap<>();
    private final Map<String, Elevator> elevatorMap = new HashMap<>();

    public InMemoryLocationDao() {
    }

    public InMemoryLocationDao(List<Location> locations, List<MultiFloorLocation> multiFloorLocations) {
        loadSingleFloorLocations(locations);
        loadMultiFloorLocations(multiFloorLocations);
    }

    private void loadSingleFloorLocations(List<Location> locations) {
        for (Location location : locations) {
            singleFloorLocationMap.put(location.getId(), location);

            if (location instanceof Room) {
                roomMap.put(location.getId(), (Room) location);
            } else if (location instanceof Corridor) {
                corridorMap.put(location.getId(), (Corridor) location);
            } else if (location instanceof Washroom) {
                washroomMap.put(location.getId(), (Washroom) location);
            } else if (location instanceof Valve) {
                valveMap.put(location.getId(), (Valve) location);
            }
        }
    }

    private void loadMultiFloorLocations(List<MultiFloorLocation> multiFloorLocations) {
        for (MultiFloorLocation location : multiFloorLocations) {
            multiFloorLocationMap.put(location.getId(), location);

            if (location instanceof Stair) {
                stairMap.put(location.getId(), (Stair) location);
            } else if (location instanceof Elevator) {
                elevatorMap.put(location.getId(), (Elevator) location);
            }
        }
    }

    @Override
    public boolean roomExists(String roomCode) {
        return roomMap.containsKey(roomCode);
    }

    @Override
    public boolean idExists(String id) {
        return singleFloorLocationMap.containsKey(id) || multiFloorLocationMap.containsKey(id);
    }

    @Override
    public Location getLocation(String id) {
        return singleFloorLocationMap.get(id);
    }

    @Override
    public MultiFloorLocation getMultiFloorLocation(String id) {
        return multiFloorLocationMap.get(id);
    }

    @Override
    public Room getRoom(String roomCode) {
        return roomMap.get(roomCode);
    }

    @Override
    public Corridor getCorridor(String id) {
        return corridorMap.get(id);
    }

    @Override
    public Washroom getWashroom(String id) {
        return washroomMap.get(id);
    }

    @Override
    public Valve getValve(String id) {
        return valveMap.get(id);
    }

    @Override
    public Stair getStair(String id) {
        return stairMap.get(id);
    }

    @Override
    public Elevator getElevator(String id) {
        return elevatorMap.get(id);
    }

    @Override
    public List<Location> getSingleFloorLocations() {
        return List.copyOf(singleFloorLocationMap.values());
    }

    @Override
    public List<MultiFloorLocation> getMultiFloorLocations() {
        return List.copyOf(multiFloorLocationMap.values());
    }
}
