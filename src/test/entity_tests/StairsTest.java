package entity_tests;

import entity.Stairs;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StairsTest {

    @Test
    void testStairs() {
        Stairs stairs = new Stairs("Stairs 1", 100, List.of("Stairs 2"), List.of(1, 2, 3, 4));
        assertEquals("Stairs 1", stairs.getId());
        assertEquals(100, stairs.getSize());
        assertEquals(List.of("Stairs 2"), stairs.getConnectedLocations());
        assertEquals(List.of(1, 2, 3, 4), stairs.getFloors());
    }
}
