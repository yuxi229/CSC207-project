package use_case.navigation;

import data_access.InMemoryRoomDataAccessObject;
import entity.LocationFactory;
import entity.Room;
import entity.RoomFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class NavigationInteractorTest {
    @Test
    void successTest() {
        NavigationInputData inputData = new NavigationInputData("1100",  "1130");
        NavigationDataAccessInterface roomRepository = new InMemoryRoomDataAccessObject();

        // For the success test, we need to add room "1100" to the data access repository before we input rooms.
        RoomFactory factory = new LocationFactory();
        Room room = factory.create("1100");
        roomRepository.save(room);

        // This creates a successPresenter that tests whether the test case is as we expect.
        NavigationOutputBoundary successPresenter = new NavigationOutputBoundary() {
            @Override
            public void prepareSuccessView(NavigationOutputData user) {
                assertEquals("1100", user.getRoomCode());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        NavigationInputBoundary interactor = new NavigationInteractor(roomRepository, successPresenter);
        interactor.execute(inputData);
    }

}
