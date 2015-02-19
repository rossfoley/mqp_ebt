package mqp.ebt;

import ch.idsia.tools.MarioAIOptions;
import mqp.mario.EBTAgent;
import mqp.mario.MQPMarioTask;
import org.jgap.gp.IGPProgram;

import java.io.FileNotFoundException;

/**
 * Main function that runs a specific EBT
 * @author Ross Foley and Karl Kuhn
 */
public class EBTRunner {
    public static String runName = "ebtTest";
    public static int generation = 2;
    public static int radius = 1;

    public static void main(String[] args) throws FileNotFoundException {
        // Set Mario options
        MarioAIOptions marioAIOptions = new MarioAIOptions();
        marioAIOptions.setVisualization(true);
        marioAIOptions.setFPS(30);
        MQPMarioTask task = new MQPMarioTask(marioAIOptions);

        // Load the EBT from the file
        IGPProgram gp = MarioXMLManager.loadEBT(runName, generation);
        EBTAgent agent = new EBTAgent(gp, radius);

        // Evaluate the agent
        task.evaluate(agent);

        // Exit
        System.exit(0);
    }
}
