package entity_tests;

import entity.Elevator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorTest {

    @Test
    void testElevator() {
        Elevator elevator = new Elevator("E1", List.of("E2"), List.of(1, 2, 3), 150);
        assertEquals("E1", elevator.getId());
        assertEquals(List.of("E2"), elevator.getConnectedLocations());
        assertEquals(150, elevator.getSize());
        assertEquals(List.of(1, 2, 3), elevator.getFloors());
    }
}
