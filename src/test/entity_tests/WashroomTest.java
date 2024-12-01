package entity_tests;

import entity.Washroom;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WashroomTest {

    @Test
    void testWashroom() {
        Washroom washroom = new Washroom("Womens 1", List.of("Womens 2"), 10, 1, "Male");
        assertEquals("Womens 1", washroom.getId());
        assertEquals(List.of("Womens 2"), washroom.getConnectedLocations());
        assertEquals(10, washroom.getSize());
        assertEquals(1, washroom.getFloor());
        assertEquals("Male", washroom.getGender());
    }
}
