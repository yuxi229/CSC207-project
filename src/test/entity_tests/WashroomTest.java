package entity_tests;

import entity.Washroom;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WashroomTest {

    @Test
    void testWashroom() {
        Washroom washroom = new Washroom("W1", List.of("W2"), 10, 1, "Male");
        assertEquals("W1", washroom.getId());
        assertEquals(List.of("W2"), washroom.getConnectedLocations());
        assertEquals(10, washroom.getSize());
        assertEquals(1, washroom.getFloor());
        assertEquals("Male", washroom.getGender());
    }
}
