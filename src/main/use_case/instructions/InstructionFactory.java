package use_case.instructions;

import java.util.List;

import use_case.navigation.maplocation.MapLocation;

/**
 * A class that makes string instructions.
 */
public interface InstructionFactory {

    /**
     * Make written instructions from a list of map locations.
     *
     * @param locations The list of map locations.
     * @return The written instructions.
     */
    BasicTextInstructions makeInstructions(List<MapLocation> locations);
}
