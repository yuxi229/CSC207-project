package use_case.instructions;

/**
 * A class that represents the output data for instructions.
 */
public class InstructionsOutputData {
    private TextInstructions instructions;

    /**
     * Constructor for InstructionsOutputData.
     * @param instructions The instructions.
     */
    public InstructionsOutputData(TextInstructions instructions) {
        setInstructions(instructions);
    }

    /**
     * Set the instructions.
     * @param instructions The instructions.
     */
    public void setInstructions(TextInstructions instructions) {
        this.instructions = instructions;
    }

    /**
     * Get the instructions.
     *
     * @return The instructions.
     */
    public TextInstructions getInstructions() {
        return instructions;
    }
}
