import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.BlueprintSelectionInteractor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BlueprintSelectionInteractorTest {

    private BlueprintSelectionInteractor interactor;

    @BeforeEach
    void setUp() {
        interactor = new BlueprintSelectionInteractor();
    }

    @Test
    void testFetchAvailableBlueprints() {
        List<String> blueprints = interactor.fetchAvailableBlueprints();
        assertNotNull(blueprints, "Blueprint list should not be null");
        assertEquals(2, blueprints.size(), "Blueprint list should contain 2 items");
        assertTrue(blueprints.contains("floor1.jpg"), "Blueprint list should contain 'floor1.jpg'");
        assertTrue(blueprints.contains("floor2.jpg"), "Blueprint list should contain 'floor2.jpg'");
    }

    @Test
    void testSwitchToValidBlueprint() {
        interactor.switchBlueprint("floor1.jpg");
        assertEquals("floor1.jpg", interactor.getActiveBlueprint(), "Active blueprint should be 'floor1.jpg'");
    }

    @Test
    void testSwitchToInvalidBlueprintThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            interactor.switchBlueprint("invalid.jpg");
        });
        assertEquals("Blueprint not found: invalid.jpg", exception.getMessage());
    }

    @Test
    void testGetActiveBlueprintReturnsNullInitially() {
        assertNull(interactor.getActiveBlueprint(), "Active blueprint should be null initially");
    }

    @Test
    void testGetActiveBlueprintAfterSwitch() {
        interactor.switchBlueprint("floor2.jpg");
        assertEquals("floor2.jpg", interactor.getActiveBlueprint(), "Active blueprint should be 'floor2.jpg'");
    }
}
