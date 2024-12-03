package use_case_tests.room_validation;

import data_access.LocationDataAccess;
import entity.Corridor;
import entity.Location;
import entity.Room;
import entity.Stairs;
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

    @Test
    void testBothRoomsExist() {
        // Arrange
        TestLocationDataAccess locationDAO = new TestLocationDataAccess();
        TestRoomValidationPresenter presenter = new TestRoomValidationPresenter();
        RoomValidationInteractor interactor = new RoomValidationInteractor(locationDAO, presenter);

        // Act
        interactor.execute(new NavigationInputData("RoomA", "RoomB"));

        // Assert
        assertTrue(presenter.isSuccessCalled());
        assertNull(presenter.getErrorMessage());
    }

    @Test
    void testDepartureRoomDoesNotExist() {
        // Arrange
        TestLocationDataAccess locationDAO = new TestLocationDataAccess();
        TestRoomValidationPresenter presenter = new TestRoomValidationPresenter();
        RoomValidationInteractor interactor = new RoomValidationInteractor(locationDAO, presenter);

        // Act
        interactor.execute(new NavigationInputData("InvalidRoom", "RoomB"));

        // Assert
        assertFalse(presenter.isSuccessCalled());
        assertEquals("Departure room doesn't exist", presenter.getErrorMessage());
    }

    @Test
    void testDestinationRoomDoesNotExist() {
        // Arrange
        TestLocationDataAccess locationDAO = new TestLocationDataAccess();
        TestRoomValidationPresenter presenter = new TestRoomValidationPresenter();
        RoomValidationInteractor interactor = new RoomValidationInteractor(locationDAO, presenter);

        // Act
        interactor.execute(new NavigationInputData("RoomA", "InvalidRoom"));

        // Assert
        assertFalse(presenter.isSuccessCalled());
        assertEquals("Destination room doesn't exist", presenter.getErrorMessage());
    }
}

