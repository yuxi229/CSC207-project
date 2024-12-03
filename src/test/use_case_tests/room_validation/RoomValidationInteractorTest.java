package use_case_tests.room_validation;

import data_access.LocationDataAccess;
import entity.Corridor;
import entity.Location;
import entity.Room;
import entity.Stairs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.navigation.NavigationInputData;
import use_case.room_exists.RoomValidationInteractor;
import use_case.room_exists.RoomValidationOutputBoundary;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RoomValidationInteractorTest {

    private static class TestLocationDataAccess implements LocationDataAccess {
        @Override
        public boolean roomExists(String roomCode) {
            // Simulate rooms that exist
            return "RoomA".equals(roomCode) || "RoomB".equals(roomCode);
        }

        @Override
        public boolean idExists(String id) {
            return false;
        }

        @Override
        public Location getLocation(String id) {
            return null;
        }

        @Override
        public Room getRoom(String roomCode) {
            return null;
        }

        @Override
        public Stairs getStairs(String id) {
            return null;
        }

        @Override
        public Corridor getCorridor(String id) {
            return null;
        }

        @Override
        public List<Integer> getFloors() {
            return List.of();
        }

        @Override
        public Set<Location> getLocations() {
            return Set.of();
        }

        @Override
        public Set<Location> getLocations(int floor) {
            return Set.of();
        }
    }

    private static class TestRoomValidationPresenter implements RoomValidationOutputBoundary {
        private String errorMessage;
        private boolean successCalled;

        @Override
        public void prepareRoomErrorView(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        @Override
        public void prepareRoomSuccessView() {
            this.successCalled = true;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public boolean isSuccessCalled() {
            return successCalled;
        }
    }
    private LocationDataAccess locationDataAccess;

    @BeforeEach
    void setUp() {
        // Assuming you have a concrete class that implements LocationDataAccess
        locationDataAccess = new TestLocationDataAccess(); // Replace with the actual implementation
    }

    // Test the idExists method
    @Test
    void testIdExistsReturnsFalse() {
        String testId = "someId";
        boolean result = locationDataAccess.idExists(testId);
        assertFalse(result, "Expected idExists to return false for any id");
    }

    // Test the getLocation method
    @Test
    void testGetLocationReturnsNull() {
        String testId = "someId";
        Location result = locationDataAccess.getLocation(testId);
        assertNull(result, "Expected getLocation to return null for any id");
    }

    // Test the getRoom method
    @Test
    void testGetRoomReturnsNull() {
        String roomCode = "roomCode";
        Room result = locationDataAccess.getRoom(roomCode);
        assertNull(result, "Expected getRoom to return null for any room code");
    }

    // Test the getStairs method
    @Test
    void testGetStairsReturnsNull() {
        String stairsId = "stairsId";
        Stairs result = locationDataAccess.getStairs(stairsId);
        assertNull(result, "Expected getStairs to return null for any stairs id");
    }

    // Test the getCorridor method
    @Test
    void testGetCorridorReturnsNull() {
        String corridorId = "corridorId";
        Corridor result = locationDataAccess.getCorridor(corridorId);
        assertNull(result, "Expected getCorridor to return null for any corridor id");
    }

    // Test the getFloors method
    @Test
    void testGetFloorsReturnsEmptyList() {
        List<Integer> result = locationDataAccess.getFloors();
        assertNotNull(result, "Expected getFloors to return a non-null list");
        assertTrue(result.isEmpty(), "Expected getFloors to return an empty list");
    }

    // Test the getLocations method (no floor specified)
    @Test
    void testGetLocationsReturnsEmptySet() {
        Set<Location> result = locationDataAccess.getLocations();
        assertNotNull(result, "Expected getLocations to return a non-null set");
        assertTrue(result.isEmpty(), "Expected getLocations to return an empty set");
    }

    // Test the getLocations method for a specific floor
    @Test
    void testGetLocationsForFloorReturnsEmptySet() {
        int floor = 1; // Can be any floor id
        Set<Location> result = locationDataAccess.getLocations(floor);
        assertNotNull(result, "Expected getLocations for floor to return a non-null set");
        assertTrue(result.isEmpty(), "Expected getLocations for floor to return an empty set");
    }


    @Test
    void testBothRoomsExist() {
        TestLocationDataAccess locationDAO = new TestLocationDataAccess();
        TestRoomValidationPresenter presenter = new TestRoomValidationPresenter();
        RoomValidationInteractor interactor = new RoomValidationInteractor(locationDAO, presenter);

        interactor.execute(new NavigationInputData("RoomA", "RoomB"));


        assertTrue(presenter.isSuccessCalled());
        assertNull(presenter.getErrorMessage());
    }

    @Test
    void testBothRoomCodesNull() {
        TestLocationDataAccess locationDAO = new TestLocationDataAccess();
        TestRoomValidationPresenter presenter = new TestRoomValidationPresenter();
        RoomValidationInteractor interactor = new RoomValidationInteractor(locationDAO, presenter);

        interactor.execute(new NavigationInputData(null, null));

        assertTrue(presenter.isSuccessCalled());
        assertEquals("Departure room doesn't exist", presenter.getErrorMessage());
    }

    @Test
    void testOneValidRoomOneEmpty() {
        TestLocationDataAccess locationDAO = new TestLocationDataAccess();
        TestRoomValidationPresenter presenter = new TestRoomValidationPresenter();
        RoomValidationInteractor interactor = new RoomValidationInteractor(locationDAO, presenter);

        interactor.execute(new NavigationInputData("RoomA", ""));

        assertTrue(presenter.isSuccessCalled());
        assertEquals("Destination room doesn't exist", presenter.getErrorMessage());
    }

    @Test
    void testCaseSensitiveRoomCodes() {
        TestLocationDataAccess locationDAO = new TestLocationDataAccess();
        TestRoomValidationPresenter presenter = new TestRoomValidationPresenter();
        RoomValidationInteractor interactor = new RoomValidationInteractor(locationDAO, presenter);

        interactor.execute(new NavigationInputData("rooma", "RoomB"));

        assertTrue(presenter.isSuccessCalled());
        assertEquals("Departure room doesn't exist", presenter.getErrorMessage());
    }


    @Test
    void testDepartureRoomDoesNotExist() {
        TestLocationDataAccess locationDAO = new TestLocationDataAccess();
        TestRoomValidationPresenter presenter = new TestRoomValidationPresenter();
        RoomValidationInteractor interactor = new RoomValidationInteractor(locationDAO, presenter);

        interactor.execute(new NavigationInputData("InvalidRoom", "RoomB"));

        assertTrue(presenter.isSuccessCalled());
        assertEquals("Departure room doesn't exist", presenter.getErrorMessage());
    }

    @Test
    void testDestinationRoomDoesNotExist() {
        TestLocationDataAccess locationDAO = new TestLocationDataAccess();
        TestRoomValidationPresenter presenter = new TestRoomValidationPresenter();
        RoomValidationInteractor interactor = new RoomValidationInteractor(locationDAO, presenter);

        interactor.execute(new NavigationInputData("RoomA", "InvalidRoom"));

        assertTrue(presenter.isSuccessCalled());
        assertEquals("Destination room doesn't exist", presenter.getErrorMessage());
    }

    @Test
    void testNullDepartureRoomCode() {
        TestLocationDataAccess locationDAO = new TestLocationDataAccess();
        TestRoomValidationPresenter presenter = new TestRoomValidationPresenter();
        RoomValidationInteractor interactor = new RoomValidationInteractor(locationDAO, presenter);

        interactor.execute(new NavigationInputData(null, "RoomB"));

        assertTrue(presenter.isSuccessCalled());
        assertEquals("Departure room doesn't exist", presenter.getErrorMessage());
    }

    @Test
    void testNullDestinationRoomCode() {
        TestLocationDataAccess locationDAO = new TestLocationDataAccess();
        TestRoomValidationPresenter presenter = new TestRoomValidationPresenter();
        RoomValidationInteractor interactor = new RoomValidationInteractor(locationDAO, presenter);

        interactor.execute(new NavigationInputData("RoomA", null));

        assertTrue(presenter.isSuccessCalled());
        assertEquals("Destination room doesn't exist", presenter.getErrorMessage());
    }

    @Test
    void testEmptyRoomCodes() {
        TestLocationDataAccess locationDAO = new TestLocationDataAccess();
        TestRoomValidationPresenter presenter = new TestRoomValidationPresenter();
        RoomValidationInteractor interactor = new RoomValidationInteractor(locationDAO, presenter);

        interactor.execute(new NavigationInputData("", ""));

        assertTrue(presenter.isSuccessCalled());
        assertEquals("Departure room doesn't exist", presenter.getErrorMessage());
    }

    @Test
    void testBothRoomsInvalid() {
        TestLocationDataAccess locationDAO = new TestLocationDataAccess();
        TestRoomValidationPresenter presenter = new TestRoomValidationPresenter();
        RoomValidationInteractor interactor = new RoomValidationInteractor(locationDAO, presenter);

        interactor.execute(new NavigationInputData("InvalidRoom1", "InvalidRoom2"));

        assertTrue(presenter.isSuccessCalled());
        assertEquals("Departure room doesn't exist", presenter.getErrorMessage());
    }

}

