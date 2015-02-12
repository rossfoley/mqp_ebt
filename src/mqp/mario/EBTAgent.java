package mqp.mario;

import ch.idsia.agents.controllers.BasicMarioAIAgent;
import org.jgap.gp.IGPProgram;

/**
 * Agent that uses EBT to play Mario
 * @author Ross Foley and Karl Kuhn
 */
public class EBTAgent extends BasicMarioAIAgent {
    IGPProgram ebt;

    public EBTAgent(IGPProgram ebt) {
        super("EBTAgent");
        this.ebt = ebt;
    }

    /**
     * Compute the array of buttons to press based on
     * the output of the EBT
     * @return the buttons to press
     */
    public boolean[] getAction() {
        int output = ebt.execute_int(0, null); // Arguments might not be correct!
        return parseOutput(output);
    }

    /**
     * Convert the output of the EBT into a boolean array
     * The output is a 6 bit integer, where each bit represents
     * a single button press
     * @param input the output of the EBT
     * @return a boolean array representing the keys to press
     */
    public boolean[] parseOutput(int input) {
        boolean[] output = {false, false, false, false, false, false};
        for (int i = 0; i < 6; i++) {
            output[i] = ((input & (1 << i)) > 0);
        }
        return output;
    }
}
