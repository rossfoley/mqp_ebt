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
    public static String runName = "radius1_200_newtask";
    public static int generation = 1000;
    public static int radius = 1;

    public static void main(String[] args) throws FileNotFoundException {
        // Set Mario options
        MarioAIOptions marioAIOptions = new MarioAIOptions();
        marioAIOptions.setVisualization(false);
        marioAIOptions.setFPS(30);
        MQPMarioTask task = new MQPMarioTask(marioAIOptions);

        // Load the EBT from the file
        IGPProgram gp = MarioXMLManager.loadEBT(runName, generation);
        EBTAgent agent = new EBTAgent(gp, radius);
        int[] difficulties = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1};

        // Evaluate the agent
//        task.evaluate(agent);

        int seed = 0;
        for (int difficulty : difficulties) {
            float fitness = task.evaluateSingleLevel(agent, difficulty, seed);
            System.out.println("Fitness for difficulty " + difficulty + " and seed " + seed + ": " + fitness);
            seed++;
        }

        // Exit
        System.exit(0);
    }
}
