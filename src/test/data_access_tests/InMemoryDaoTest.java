package data_access_tests;

import data_access.*;
import entity.Corridor;
import entity.Elevator;
import entity.Room;
import entity.Stairs;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryDaoTest {

    // Tests for LocationDaoBuilder and LocationDataAccess
    @Test
    public void testRoomExists() {
        // Test that roomExists returns true for a room that exists
        Room room = new Room("room1", List.of("room2"), 1, 1, false);
        LocationDaoBuilder locationDaoBuilder = new InMemoryLocationDao();
        locationDaoBuilder.addLocation(room);
        LocationDataAccess locationDataAccess = locationDaoBuilder.createDataAccessObject();
        assert locationDataAccess.roomExists("room1");
    }

    @Test
    public void testIdExist() {
        Corridor corridor = new Corridor("corridor1", List.of("room1", "room2"), 1, 1, 1.0);
        LocationDaoBuilder locationDaoBuilder = new InMemoryLocationDao();
        locationDaoBuilder.addLocation(corridor);
        LocationDataAccess locationDataAccess = locationDaoBuilder.createDataAccessObject();
        assert locationDataAccess.idExists("corridor1");
    }

    @Test
    public void testAddLocationRepeat() {
        LocationDaoBuilder locationDaoBuilder = new InMemoryLocationDao();
        // Test that addLocation does not add the same location twice if the rooms have the same id
        Room room1 = new Room("room1", List.of("room2"), 1, 1, false);
        Room room2 = new Room("room1", List.of("room5"), 3, 2, true);
        locationDaoBuilder.addLocation(room1);
        locationDaoBuilder.addLocation(room2);
        LocationDataAccess locationDataAccess = locationDaoBuilder.createDataAccessObject();
        assert locationDataAccess.roomExists("room1");
        assertEquals(1, locationDataAccess.getLocations().size());
    }

    @Test
    public void testGetRoom() {
        // Test that getRoom returns the correct room
        Room room = new Room("room1", List.of("room2"), 1, 1, false);
        LocationDaoBuilder locationDaoBuilder = new InMemoryLocationDao();
        locationDaoBuilder.addLocation(room);
        LocationDataAccess locationDataAccess = locationDaoBuilder.createDataAccessObject();
        assertEquals(room, locationDataAccess.getRoom("room1"));
        assertEquals(room.getId(), locationDataAccess.getRoom("room1").getId());
    }

    @Test
    public void testGetFloorIds() {
        // Test that getFloorIds returns the correct floor ids in sorted order
        Room location1 = new Room("room1", List.of("room2"), 1, 1, false);
        Corridor location2 = new Corridor("corridor1", List.of("room1", "room2"), 1, 1, 1.0);
        Stairs location3 = new Stairs("stairs1", 10, List.of("room1", "room2"), List.of(1, 3, 5, 4));
        Elevator location4 = new Elevator("elevator1", List.of("room1", "room2"), List.of(3,4,5, 9, 6), 10);
        LocationDaoBuilder locationDaoBuilder = new InMemoryLocationDao();
        locationDaoBuilder.addLocation(location1);
        locationDaoBuilder.addLocation(location2);
        locationDaoBuilder.addLocation(location3);
        locationDaoBuilder.addLocation(location4);
        LocationDataAccess locationDataAccess = locationDaoBuilder.createDataAccessObject();
        assertEquals(List.of(1, 3, 4, 5, 6, 9), locationDataAccess.getFloorIds());
    }

    @Test
    public void testGetLocations() {
        // Test that getLocations returns the correct set of locations
        Room location1 = new Room("room1", List.of("room2"), 1, 1, false);
        Corridor location2 = new Corridor("corridor1", List.of("room1", "room2"), 1, 1, 1.0);
        Stairs location3 = new Stairs("stairs1", 10, List.of("room1", "room2"), List.of(1, 3, 5, 4));
        Elevator location4 = new Elevator("elevator1", List.of("room1", "room2"), List.of(3,4,5, 9, 6), 10);
        LocationDaoBuilder locationDaoBuilder = new InMemoryLocationDao();
        locationDaoBuilder.addLocation(location1);
        locationDaoBuilder.addLocation(location2);
        locationDaoBuilder.addLocation(location3);
        locationDaoBuilder.addLocation(location4);
        LocationDataAccess locationDataAccess = locationDaoBuilder.createDataAccessObject();
        assertEquals(Set.of(location1, location2, location3, location4), locationDataAccess.getLocations());
    }

    @Test
    public void testGetLocationsByFloor() {
        // Test that getLocationsByFloor returns the correct set of locations for a given floor
        Room location1 = new Room("room1", List.of("room2"), 1, 1, false);
        Corridor location2 = new Corridor("corridor1", List.of("room1", "room2"), 1, 1, 1.0);
        Stairs location3 = new Stairs("stairs1", 10, List.of("room1", "room2"), List.of(1, 3, 5, 4));
        Elevator location4 = new Elevator("elevator1", List.of("room1", "room2"), List.of(3,4,5, 9, 6), 10);
        LocationDaoBuilder locationDaoBuilder = new InMemoryLocationDao();
        locationDaoBuilder.addLocation(location1);
        locationDaoBuilder.addLocation(location2);
        locationDaoBuilder.addLocation(location3);
        locationDaoBuilder.addLocation(location4);
        LocationDataAccess locationDataAccess = locationDaoBuilder.createDataAccessObject();
        assertEquals(Set.of(location1, location2, location3), locationDataAccess.getLocations(1));
    }

    // Tests for MapLocationDaoBuilder and MapLocationDataAccess
}
