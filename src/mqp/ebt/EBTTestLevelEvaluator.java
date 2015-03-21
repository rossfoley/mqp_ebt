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
public class EBTTestLevelEvaluator {
    public static String runName = "radius2_300_newtask2";
    public static int generation = 1000;
    public static int radius = 2;

    public static void main(String[] args) throws FileNotFoundException {
        // Set Mario options
        MarioAIOptions marioAIOptions = new MarioAIOptions();
        marioAIOptions.setVisualization(false);
        marioAIOptions.setFPS(30);
        MQPMarioTask task = new MQPMarioTask(marioAIOptions);

        // Load the EBT from the file
        IGPProgram gp = MarioXMLManager.loadEBT(runName, generation);
        EBTAgent agent = new EBTAgent(gp, radius);

        // Set up difficulty and seed parameters
        int difficulty = 1;
        int startingSeed = 0;
        int numLevels = 1000;

        // Evaluate the agent on all the levels
        for (int i = 0; i < numLevels; i++) {
            float fitness = task.evaluateSingleLevel(agent, difficulty, startingSeed + i);
            System.out.println(fitness);
        }

        // Exit
        System.exit(0);
    }
}
