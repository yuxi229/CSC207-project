package entity_tests;

import entity.Corridor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CorridorTest {

    @Test
    void testCorridor() {
        Corridor corridor = new Corridor("Corridor 1", List.of("Corridor 2", "Corridor 3"), 50, 2, 20.5);
        assertEquals("Corridor 1", corridor.getId());
        assertEquals(List.of("Corridor 2", "Corridor 3"), corridor.getConnectedLocations());
        assertEquals(50, corridor.getSize());
        assertEquals(2, corridor.getFloor());
        assertEquals(20.5, corridor.getLength());
    }
}
