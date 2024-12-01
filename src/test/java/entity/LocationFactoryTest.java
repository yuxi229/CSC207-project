package entity;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationFactoryTest {
    @Test
    void testCreateCorridor() {
        Corridor corridor = LocationFactory.create("C1", List.of("R1", "R2"), 1, 30, 100.0);
        assertEquals("C1", corridor.getId());
        assertEquals(List.of("R1", "R2"), corridor.getConnectedLocations());
        assertEquals(30, corridor.getSize());
        assertEquals(100.0, corridor.getLength());
    }

    @Test
    void testCreateRoom() {
        Room room = LocationFactory.create("R1", List.of("C1"), 2, 20, true);
        assertEquals("R1", room.getId());
        assertTrue(room.isRestricted());
    }
}
