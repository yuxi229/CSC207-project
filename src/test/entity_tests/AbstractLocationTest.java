package entity_tests;

import entity.AbstractLocation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractLocationTest {
    static class TestLocation extends AbstractLocation {
        TestLocation(String id, List<String> connectedLocations, int size) {
            super(id, connectedLocations, size);
        }

        @Override
        public List<Integer> getFloors() {
            return List.of(); // Example implementation
        }
    }

    @Test
    void testGetId() {
        AbstractLocation location = new TestLocation("L1", List.of("L2", "L3"), 50);
        assertEquals("L1", location.getId());
    }

    @Test
    void testGetConnectedLocations() {
        AbstractLocation location = new TestLocation("L1", List.of("L2", "L3"), 50);
        assertEquals(List.of("L2", "L3"), location.getConnectedLocations());
    }

    @Test
    void testGetSize() {
        AbstractLocation location = new TestLocation("L1", List.of("L2", "L3"), 50);
        assertEquals(50, location.getSize());
    }

    @Test
    void testEqualsAndHashCode() {
        AbstractLocation location1 = new TestLocation("L1", List.of("L2", "L3"), 50);
        AbstractLocation location2 = new TestLocation("L1", List.of("L4"), 30);

        assertEquals(location1, location2);
        assertEquals(location1.hashCode(), location2.hashCode());
    }
}
