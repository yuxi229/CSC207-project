package entity_tests;

import entity.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorTest {

    @Test
    void testElevatorInitialization() {
        List<Corridor> corridors = new ArrayList<>();
        Elevator elevator = new Elevator("Elevator 1", corridors, 8.5);

        assertEquals("Elevator 1", elevator.getId());
        assertEquals(8.5, elevator.getLength());
        assertTrue(elevator.getFloors().isEmpty());
    }

    @Test
    void testElevatorFactory() {
        ElevatorFactory factory = new ElevatorFactory();
        List<Corridor> corridors = new ArrayList<>();
        Elevator elevator = (Elevator) factory.createElevator("Elevator 2", corridors, 12.0);

        assertNotNull(elevator);
        assertEquals("Elevator 2", elevator.getId());
        assertEquals(12.0, elevator.getLength());
    }
}
