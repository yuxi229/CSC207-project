package data_access;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.AbstractLocation;
import entity.Corridor;
import entity.Floor;
import entity.Room;
import entity.Stairs;
import use_case.navigation.MapLocation;

/**
 * In-memory implementation of the DAO for storing navigation data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryLocationDao implements LocationDataAccessInterface {
    private final Map<String, AbstractLocation> locationMap = new HashMap<>();
    private final Map<String, Room> roomCodeToRoom = new HashMap<>();
    private final Map<String, Floor> floorIdToFloor = new HashMap<>();
    private final Map<String, Map<String, MapLocation>> mapLocationLookup = new HashMap<>();

    public InMemoryLocationDao() {
    }

    public InMemoryLocationDao(List<AbstractLocation> locations, List<MapLocation> mapLocations) {
        loadLocations(locations);
        loadMapLocations(mapLocations);
    }

    private void loadLocations(List<AbstractLocation> locations) {
        for (AbstractLocation location : locations) {
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
    public AbstractLocation getLocation(String id) {
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
        final Stairs result;
        if (locationMap.containsKey(id) && locationMap.get(id) instanceof Stairs) {
            result = (Stairs) locationMap.get(id);
        }
        else {
            // TODO: Raise an appropriate Error
            result = null;
        }
        return result;
    }

    @Override
    public Corridor getCorridor(String id) {
        final Corridor result;
        if (locationMap.containsKey(id) && locationMap.get(id) instanceof Corridor) {
            result = (Corridor) locationMap.get(id);
        }
        else {
            // TODO: Raise an appropriate Error
            result = null;
        }
        return result;
    }

    @Override
    public List<Floor> getFloors() {
        return List.copyOf(floorIdToFloor.values());
    }

    @Override
    public List<AbstractLocation> getLocations() {
        return List.copyOf(locationMap.values());
    }

    @Override
    public MapLocation getMapLocation(String id, String floorID) {
        return mapLocationLookup.get(id).get(floorID);
    }
}
