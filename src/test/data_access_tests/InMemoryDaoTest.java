package data_access_tests;

import data_access.*;
import entity.Corridor;
import entity.Room;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        // Test that addLocation does not add the same location twice
        Room room = new Room("room1", List.of("room2"), 1, 1, false);
        locationDaoBuilder.addLocation(room);
        locationDaoBuilder.addLocation(room);
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

    // Tests for MapLocationDaoBuilder and MapLocationDataAccess
}
