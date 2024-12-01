package entity_tests;

import entity.LocationFactory;
import entity.Room;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractSingleFloorLocationTest {
    @Test
    void testGetFloor() {
        Room room = LocationFactory.create("R1", List.of("C1"), 2, 20, false);
        assertEquals(2, room.getFloor());
    }

    @Test
    void testGetFloors() {
        Room room = LocationFactory.create("R1", List.of("C1"), 2, 20, false);
        assertEquals(List.of(2), room.getFloors());
    }
}
