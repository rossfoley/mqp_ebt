package mqp.ebt.tool;

import ch.idsia.tools.MarioAIOptions;
import mqp.ebt.MarioXMLManager;
import mqp.mario.EBTAgent;
import mqp.mario.MQPMarioTask;
import org.jgap.gp.IGPProgram;

import java.io.FileNotFoundException;

/**
 * Main function that runs a specific EBT
 * @author Ross Foley and Karl Kuhn
 */
public class EBTReplayCreator {
    public static String runName = "radius1_200_newtask";
    public static int generation = 1000;
    public static int radius = 1;
    public static String fileName = "test2";

    public static void main(String[] args) throws FileNotFoundException {
        // Set Mario options
        MarioAIOptions marioAIOptions = new MarioAIOptions();
        marioAIOptions.setVisualization(true);
        marioAIOptions.setFPS(30);
        marioAIOptions.setRecordFile("replay/" + fileName);
        MQPMarioTask task = new MQPMarioTask(marioAIOptions);

        // Load the EBT from the file
        IGPProgram gp = MarioXMLManager.loadEBT(runName, generation);
        EBTAgent agent = new EBTAgent(gp, radius);

        // Play through one level to record a replay
        task.evaluateSingleLevel(agent, 0, 0);

        // Exit
        System.exit(0);
    }
}
