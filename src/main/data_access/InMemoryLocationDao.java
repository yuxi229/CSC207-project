package data_access;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import entity.Corridor;
import entity.Location;
import entity.Room;
import entity.Stairs;
import use_case.navigation.maplocation.MapLocation;

/**
 * In-memory implementation of the DAO for storing navigation data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryLocationDao implements LocationDataAccess, LocationDaoBuilder, MapLocationDataAccess,
        MapLocationDaoBuilder {
    private final Map<String, Location> locationMap = new HashMap<>();
    private final Map<String, Map<Integer, MapLocation>> mapLocationLookup = new HashMap<>();
    private final Set<Integer> floorIds = new HashSet<>();
    private final Set<Location> locations = new HashSet<>();

    public InMemoryLocationDao() {
    }

    @Override
    public boolean roomExists(String roomCode) {
        return locationMap.containsKey(roomCode);
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
        final Room result;
        if (locationMap.containsKey(roomCode) && locationMap.get(roomCode) instanceof Room) {
            result = (Room) locationMap.get(roomCode);
        }
        else {
            // TODO: Raise an appropriate Error
            result = null;
        }
        return result;
    }

    @Override
    public Stairs getStairs(String id) {
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
    public List<Integer> getFloorIds() {
        return List.copyOf(floorIds);
    }

    @Override
    public Set<Location> getLocations() {
        return Set.copyOf(locations);
    }

    @Override
    public Set<Location> getLocations(int floor) {
        return Set.of();
    }

    @Override
    public MapLocation getMapLocation(String id, int floor) {
        return mapLocationLookup.get(id).get(floor);
    }

    @Override
    public void addLocation(Location location) {
        locationMap.put(location.getId(), location);
        locations.add(location);
    }

    @Override
    public void addLocations(Set<Location> newLocations) {
        for (Location location : newLocations) {
            addLocation(location);
        }
    }

    @Override
    public LocationDataAccess createDataAccessObject() {
        return this;
    }

    @Override
    public void addMapLocation(MapLocation mapLocation) {
        mapLocationLookup.put(mapLocation.getLocationID(), Map.of(mapLocation.getFloorID(), mapLocation));
        floorIds.add(mapLocation.getFloorID());
    }

    @Override
    public void addMapLocations(Set<MapLocation> newMapLocations) {
        for (MapLocation mapLocation : newMapLocations) {
            addMapLocation(mapLocation);
        }
    }

    @Override
    public MapLocationDataAccess createMapLocationDao() {
        return this;
    }
}
