package use_case.instructions;

import java.util.List;

import use_case.navigation.maplocation.MapLocation;

/**
 * A class that makes string instructions.
 */
public interface TextInstructions {

    /**
     * The factory method that make written instructions from a list of map locations.
     *
     * @param locations The list of map locations.
     * @return The written instructions.
     */
    TextInstructions makeInstructions(List<MapLocation> locations);

    /**
     * Get the list of location ids in order.
     *
     * @return The list of location ids in order.
     */
    List<String> getLocationList();

    /**
     * Get the list of instructions in order.
     *
     * @return The list of instructions in order.
     */
    List<String> getInstructionList();

    /**
     * Get the instruction for getting from location1 to location2. (Possibly only used in testing.)
     *
     * @param location1Id The ID of the first location.
     * @param location2Id The ID of the second location.
     * @return The instruction between the two locations.
     */
    String getInstruction(String location1Id, String location2Id);
}
