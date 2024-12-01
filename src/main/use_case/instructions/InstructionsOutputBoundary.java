package use_case.instructions;

/**
 * Output boundary for getting instructions.
 */
public interface InstructionsOutputBoundary {
    /**
     * Present the instructions to the user.
     *
     * @param instructions The output data object consisting of instructions to present.
     */
    void presentInstructions(InstructionsOutputData instructions);
}
