package use_case.instructions;

import java.util.List;

import use_case.navigation.maplocation.MapLocation;

/**
 * A class that makes string instructions.
 */
public interface InstructionMaker {

    /**
     * Make written instructions from a list of map locations.
     *
     * @param locations The list of map locations.
     * @return The written instructions.
     */
    TextInstructions makeInstructions(List<MapLocation> locations);
}
