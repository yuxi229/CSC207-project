package data_access;

import java.util.HashMap;
import java.util.List;

import entity.*;
import use_case.LocationDataAccessInterface;
import use_case.navigation.MapLocation;

/**
 * In-memory implementation of the DAO for storing navigation data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryLocationDAO implements LocationDataAccessInterface {
    private final HashMap<String, Location> locationMap = new HashMap<>();
    private final HashMap<String, Room> roomCodeToRoom = new HashMap<>();
    private final HashMap<String, Floor> floorIdToFloor = new HashMap<>();
    private final HashMap<String, HashMap<String, MapLocation>> mapLocationLookup = new HashMap<>();

    public InMemoryLocationDAO() {
    }

    public InMemoryLocationDAO(List<Location> locations, List<MapLocation> mapLocations) {
        loadLocations(locations);
        loadMapLocations(mapLocations);
    }

    private void loadLocations(List<Location> locations) {
        for (Location location : locations) {
            // Add location to locationMap
            locationMap.put(location.getId(), location);

            // Add rooms to roomCodeToRoom
            if (location instanceof Room) {
                roomCodeToRoom.put(((Room) location).getRoomCode(), (Room) location);
            }
            // Add floors to floorIdToFloor
            for (Floor floor : location.getFloors()) {
                if (!floorIdToFloor.containsKey(floor.getFloorId())) {
                    floorIdToFloor.put(floor.getFloorId(), floor);
                }
            }
        }
    }

    private void loadMapLocations(List<MapLocation> mapLocations) {
        for (MapLocation mapLocation : mapLocations) {
            if (!mapLocationLookup.containsKey(mapLocation.getLocationID())) {
                mapLocationLookup.put(mapLocation.getLocationID(), new HashMap<>());
            }
            mapLocationLookup.get(mapLocation.getLocationID()).put(mapLocation.getFloorID(), mapLocation);
        }
    }

    @Override
    public boolean roomExists(String roomCode) {
        return roomCodeToRoom.containsKey(roomCode);
    }

    @Override
    public boolean idExists(String id) {
        return locationMap.containsKey(id);
    }

    @Override
    public Location getLocation(String id) {
        return locationMap.get(id);
    }

    @Override
    public Room getRoom(String roomCode) {
        return roomCodeToRoom.get(roomCode);
    }

    @Override
    public Floor getFloor(String id) {
        return floorIdToFloor.get(id);
    }

    @Override
    public Stairs getStair(String id) {
        if (locationMap.containsKey(id) && locationMap.get(id) instanceof Stairs) {
            return (Stairs) locationMap.get(id);
        }
        return null;
    }

    @Override
    public Corridor getCorridor(String id) {
        if (locationMap.containsKey(id) && locationMap.get(id) instanceof Corridor) {
            return (Corridor) locationMap.get(id);
        }
        return null;
    }

    @Override
    public List<Floor> getFloors() {
        return List.copyOf(floorIdToFloor.values());
    }

    @Override
    public List<Location> getLocations() {
        return List.copyOf(locationMap.values());
    }

    @Override
    public MapLocation getMapLocation(String id, String floorID) {
        return mapLocationLookup.get(id).get(floorID);
    }
}