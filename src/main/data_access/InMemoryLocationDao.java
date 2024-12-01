package data_access;

import java.util.*;

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
        final List<Integer> floorIdList = new ArrayList<>(floorIds);
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
    public MapLocation getMapLocation(String id, int floor) {
        return mapLocationLookup.get(id).get(floor);
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
    public void addMapLocation(MapLocation mapLocation) {
        final String locationID = mapLocation.getLocationID();
        final Integer floorID = mapLocation.getFloorID();
        if (mapLocationLookup.containsKey(locationID)) {
            System.out.println(mapLocationLookup.get(locationID));
            System.out.println(locationID);
            System.out.println(floorID);
            mapLocationLookup.get(locationID).put(floorID, mapLocation);
        }
        else {
            mapLocationLookup.put(locationID, Map.of(floorID, mapLocation));
        }
        floorIds.add(floorID);
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
