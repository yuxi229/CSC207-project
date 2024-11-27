package entity_tests;

import entity.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StairsTest {

    @Test
    void testStairsCreation() {
        Floor lowerFloor = new Floor("Floor 1", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Floor upperFloor = new Floor("Floor 2", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Corridor lowerCorridor = new Corridor("Corridor 1", new ArrayList<>(), new ArrayList<>(), lowerFloor, 5.0);
        Corridor upperCorridor = new Corridor("Corridor 2", new ArrayList<>(), new ArrayList<>(), upperFloor, 6.0);

        Stairs stairs = new Stairs("Stairs 1", lowerFloor, upperFloor, lowerCorridor, upperCorridor, 10.5);

        assertEquals("Stairs 1", stairs.getId());
        assertEquals(lowerFloor, stairs.getLowerFloor());
        assertEquals(upperFloor, stairs.getUpperFloor());
        assertEquals(lowerCorridor, stairs.getLowerCorridor());
        assertEquals(upperCorridor, stairs.getUpperCorridor());
        assertEquals(10.5, stairs.getLength());
    }

    @Test
    void testSetters() {
        Floor lowerFloor = new Floor("Floor 1", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Floor upperFloor = new Floor("Floor 2", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Corridor lowerCorridor = new Corridor("Corridor 1", new ArrayList<>(), new ArrayList<>(), lowerFloor, 5.0);
        Corridor upperCorridor = new Corridor("Corridor 2", new ArrayList<>(), new ArrayList<>(), upperFloor, 6.0);

        Stairs stairs = new Stairs("Stairs 1", lowerFloor, upperFloor, lowerCorridor, upperCorridor, 10.5);

        Floor newLowerFloor = new Floor("Floor 3", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        stairs.setLowerFloor(newLowerFloor);
        assertEquals(newLowerFloor, stairs.getLowerFloor());

        Corridor newLowerCorridor = new Corridor("Corridor 3", new ArrayList<>(), new ArrayList<>(), newLowerFloor, 7.0);
        stairs.setLowerCorridor(newLowerCorridor);
        assertEquals(newLowerCorridor, stairs.getLowerCorridor());

        stairs.setLength(12.5);
        assertEquals(12.5, stairs.getLength());
    }

    @Test
    void testGetConnectedLocations() {
        Floor lowerFloor = new Floor("Floor 1", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Floor upperFloor = new Floor("Floor 2", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Corridor lowerCorridor = new Corridor("Corridor 1", new ArrayList<>(), new ArrayList<>(), lowerFloor, 5.0);
        Corridor upperCorridor = new Corridor("Corridor 2", new ArrayList<>(), new ArrayList<>(), upperFloor, 6.0);

        Stairs stairs = new Stairs("Stairs 1", lowerFloor, upperFloor, lowerCorridor, upperCorridor, 10.5);

        List<AbstractLocation> connectedLocations = stairs.getConnectedLocations();
        assertTrue(connectedLocations.contains(lowerCorridor));
        assertTrue(connectedLocations.contains(upperCorridor));
    }

    @Test
    void testStairsFactory() {
        StairsFactory factory = new StairsFactory();
        Floor lowerFloor = new Floor("Floor 1", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Floor upperFloor = new Floor("Floor 2", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Corridor lowerCorridor = new Corridor("Corridor 1", new ArrayList<>(), new ArrayList<>(), lowerFloor, 5.0);
        Corridor upperCorridor = new Corridor("Corridor 2", new ArrayList<>(), new ArrayList<>(), upperFloor, 6.0);

        Stairs stairs = factory.createStairs("Stairs 1", lowerFloor, upperFloor, lowerCorridor, upperCorridor, 15.0);

        assertNotNull(stairs);
        assertEquals("Stairs 1", stairs.getId());
        assertEquals(lowerFloor, stairs.getLowerFloor());
        assertEquals(upperFloor, stairs.getUpperFloor());
        assertEquals(15.0, stairs.getLength());
    }
}

