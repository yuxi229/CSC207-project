package use_case.instructions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import use_case.navigation.maplocation.MapLocation;

/**
 * A class that represents a set of text instructions.
 */
public class BasicTextInstructions implements InstructionsOutputData, InstructionFactory {
    private final List<String> locationIds = new ArrayList<>();
    private final List<String> instructions = new ArrayList<>();
    private final Map<String, String> instructionLookup = new HashMap<>();

    BasicTextInstructions(List<MapLocation> mapLocations) {
        makeInstructions(mapLocations);
    }

    @Override
    public BasicTextInstructions getInstructions() {
        return this;
    }

    @Override
    public BasicTextInstructions makeInstructions(List<MapLocation> mapLocations) {
        if (mapLocations.size() >= 2) {
            for (int i = 1; i < mapLocations.size(); i++) {
                final String locationId2 = mapLocations.get(i).getLocationID();
                final String instruction = "Go to " + locationId2;
                if (instructionIsValid(locationId2, locationId2, instruction)) {
                    addInstruction(locationId2, locationId2, instruction);
                }
                else {
                    // TODO: Raise an error here.
                }
            }
        }
        return this;
    }

    /**
     * Get the list of location ids in order.
     *
     * @return The list of location ids in order.
     */
    public List<String> getLocationList() {
        return List.copyOf(locationIds);
    }

    /**
     * Get the list of instructions in order.
     *
     * @return The list of instructions in order.
     */
    public List<String> getInstructionList() {
        return List.copyOf(instructions);
    }

    /**
     * Check if the instruction is valid. (Possibly only used in testing.)
     *
     * @param location1Id The ID of the first location.
     * @param location2Id The ID of the second location.
     * @param instruction The instruction.
     * @return True if the instruction is valid, false otherwise.
     */
    public boolean instructionIsValid(String location1Id, String location2Id, String instruction) {
        return locationIds.isEmpty() || locationIds.get(locationIds.size() - 1).equals(location1Id);
    }

    /**
     * Get the instruction for getting from location1 to location2. (Possibly only used in testing.)
     *
     * @param location1Id The ID of the first location.
     * @param location2Id The ID of the second location.
     * @return The instruction between the two locations.
     */
    public String getInstruction(String location1Id, String location2Id) {
        return instructionLookup.get(location1Id + location2Id);
    }

    /**
     * Add an instruction for getting from location1 to location2.
     *
     * @param location1Id The ID of the first location.
     * @param location2Id The ID of the second location.
     * @param instruction The instruction.
     */
    private void addInstruction(String location1Id, String location2Id, String instruction) {
        locationIds.add(location1Id);
        locationIds.add(location2Id);
        instructions.add(instruction);
        instructionLookup.put(location1Id + location2Id, instruction);
    }
}
