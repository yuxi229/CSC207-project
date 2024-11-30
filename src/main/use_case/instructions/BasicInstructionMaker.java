package use_case.instructions;

import java.util.List;

import use_case.navigation.maplocation.MapLocation;

/**
 * Basic implementation of an instruction maker.
 */
public class BasicInstructionMaker implements InstructionMaker {
    BasicInstructionMaker() {
    }

    @Override
    public TextInstructions makeInstructions(List<MapLocation> locations) {
        final TextInstructions instructions = new TextInstructions();
        if (locations.size() >= 2) {
            for (int i = 1; i < locations.size(); i++) {
                final String locationId2 = locations.get(i).getLocationID();
                final String instruction = "Go to " + locationId2;
                if (instructions.instructionIsValid(locationId2, locationId2, instruction)) {
                    instructions.addInstruction(locationId2, locationId2, instruction);
                }
                else {
                    // TODO: Raise an error here.
                }
            }
        }
        return instructions;
    }
}
