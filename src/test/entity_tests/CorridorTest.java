package entity_tests;

import entity.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CorridorTest {

    @Test
    void testCorridorInitialization() {
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Stairs> stairs = new ArrayList<>();
        Floor floor = new Floor("Floor 1", rooms, stairs, new ArrayList<>());
        Corridor corridor = new Corridor("Corridor 1", rooms, stairs, floor, 10.5);

        assertEquals("Corridor 1", corridor.getId());
        assertEquals(10.5, corridor.getLength());
        assertEquals(floor, corridor.getFloors().get(0));
        assertEquals(0, corridor.getConnectedRooms().size());
    }

    @Test
    void testCorridorFactory() {
        CorridorFactory factory = new CorridorFactory();
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Stairs> stairs = new ArrayList<>();
        Floor floor = new Floor("Floor 1", rooms, stairs, new ArrayList<>());
        Corridor corridor = factory.createCorridor("Corridor 1", rooms, stairs, floor, 15.0);

        assertNotNull(corridor);
        assertEquals("Corridor 1", corridor.getId());
        assertEquals(15.0, corridor.getLength());
    }
}