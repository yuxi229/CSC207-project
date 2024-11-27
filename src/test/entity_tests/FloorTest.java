package entity_tests;

import entity.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FloorTest {

    @Test
    void testFloorInitialization() {
        List<Room> rooms = new ArrayList<>();
        List<Stairs> stairs = new ArrayList<>();
        List<Corridor> corridors = new ArrayList<>();
        Floor floor = new Floor("Floor 1", rooms, stairs, corridors);

        assertEquals("Floor 1", floor.getFloorId());
        assertTrue(floor.getRoomsList().isEmpty());
        assertTrue(floor.getStairsList().isEmpty());
    }

    @Test
    void testFloorFactory() {
        FloorFactory factory = new FloorFactory();
        List<Room> rooms = new ArrayList<>();
        List<Stairs> stairs = new ArrayList<>();
        List<Corridor> corridors = new ArrayList<>();
        Floor floor = factory.createFloor("Floor 2", rooms, stairs, corridors);

        assertNotNull(floor);
        assertEquals("Floor 2", floor.getFloorId());
    }
}

