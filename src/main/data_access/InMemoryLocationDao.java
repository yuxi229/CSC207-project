package data_access;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    private final Map<IdFloorPair, MapLocation> mapLocationLookup = new HashMap<>();
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
    public List<Integer> getFloors() {
        final List<Integer> floorIdList = new ArrayList<>(this.floorIds);
        Collections.sort(floorIdList);
        return floorIdList;
    }

    @Override
    public Set<Location> getLocations() {
        return Set.copyOf(locations);
    }

    @Override
    public Set<Location> getLocations(int floor) {
        final Set<Location> locationsOnFloor = new HashSet<>();
        for (Location location : locations) {
            if (location.getFloors().contains(floor)) {
                locationsOnFloor.add(location);
            }
        }
        return locationsOnFloor;
    }

    @Override
    public void addLocation(Location location) {
        locationMap.put(location.getId(), location);
        locations.add(location);
        floorIds.addAll(location.getFloors());
    }

    @Override
    public LocationDataAccess createDataAccessObject() {
        return this;
    }

    @Override
    public MapLocation getMapLocation(String id, int floor) {
        return mapLocationLookup.get(new IdFloorPair(id, floor));
    }

    @Override
    public void addMapLocation(MapLocation mapLocation) {
        final IdFloorPair idFloorPair = new IdFloorPair(mapLocation.getLocationID(), mapLocation.getFloor());
        mapLocationLookup.put(idFloorPair, mapLocation);
        floorIds.add(mapLocation.getFloor());
    }

    @Override
    public MapLocationDataAccess createMapLocationDao() {
        return this;
    }

    /**
     * Helper class for storing a pair of an ID and a floor number as a key in a map.
     */
    private static class IdFloorPair {
        private final String id;
        private final int floor;

        IdFloorPair(String id, int floor) {
            this.id = id;
            this.floor = floor;
        }

        @Override
        public boolean equals(Object obj) {
            boolean result = false;
            if (obj instanceof IdFloorPair) {
                final IdFloorPair other = (IdFloorPair) obj;
                result = id.equals(other.id) && floor == other.floor;
            }
            return result;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, floor);
        }
    }
}
