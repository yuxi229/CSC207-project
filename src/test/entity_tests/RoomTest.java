package entity_tests;

import entity.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void testRoomCreation() {
        List<Corridor> corridors = new ArrayList<>();
        List<Floor> floors = new ArrayList<>();
        Room room = new Room("BA1100", corridors, floors);

        assertEquals("BA1100", room.getRoomCode());
        assertTrue(room.getConnectedCorridors().isEmpty());
    }

    @Test
    void testRoomFactory() {
        RoomFactory factory = new RoomFactory();
        List<Corridor> corridors = new ArrayList<>();
        List<Floor> floors = new ArrayList<>();
        Room room = factory.createRoom("BA1195", corridors, floors);

        assertNotNull(room);
        assertEquals("BA1195", room.getRoomCode());
    }
}
