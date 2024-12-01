package entity_tests;

import entity.Room;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void testRoom() {
        Room room = new Room("R1", List.of("R2"), 1, 30, true);
        assertEquals("R1", room.getRoomCode());
        assertEquals(List.of("R2"), room.getConnectedLocations());
        assertEquals(1, room.getFloor());
        assertEquals(30, room.getSize());
        assertTrue(room.isRestricted());
    }
}
