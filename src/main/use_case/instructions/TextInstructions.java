package use_case.instructions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that represents a set of text instructions.
 */
public class TextInstructions {
    private final List<String> locations = new ArrayList<>();
    private final List<String> instructions = new ArrayList<>();
    private final Map<String, String> instructionLookup = new HashMap<>();

    TextInstructions() {
    }

    /**
     * Check if the instruction is valid.
     *
     * @param location1Id The ID of the first location.
     * @param location2Id The ID of the second location.
     * @param instruction The instruction.
     * @return True if the instruction is valid, false otherwise.
     */
    public boolean instructionIsValid(String location1Id, String location2Id, String instruction) {
        return locations.isEmpty() || locations.get(locations.size() - 1).equals(location1Id);
    }

    /**
     * Add an instruction for getting from location1 to location2.
     *
     * @param location1Id The ID of the first location.
     * @param location2Id The ID of the second location.
     * @param instruction The instruction.
     */
    public void addInstruction(String location1Id, String location2Id, String instruction) {
        locations.add(location1Id);
        locations.add(location2Id);
        instructions.add(instruction);
        instructionLookup.put(location1Id + location2Id, instruction);
    }

    /**
     * Get the instruction for getting from location1 to location2.
     *
     * @param location1Id The ID of the first location.
     * @param location2Id The ID of the second location.
     * @return The instruction between the two locations.
     */
    public String getInstruction(String location1Id, String location2Id) {
        return instructionLookup.get(location1Id + location2Id);
    }

    /**
     * Get the list of location ids in order.
     *
     * @return The list of location ids in order.
     */
    public List<String> getInstructions() {
        return List.copyOf(instructions);
    }
}
