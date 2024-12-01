package entity_tests;

import entity.Elevator;
import entity.LocationFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractMultiFloorLocationTest {
    @Test
    void testGetFloors() {
        Elevator elevator = LocationFactory.create("E1", List.of("C1", "C2"), List.of(1, 2), 10);
        assertEquals(List.of(1, 2), elevator.getFloors());
    }
}
