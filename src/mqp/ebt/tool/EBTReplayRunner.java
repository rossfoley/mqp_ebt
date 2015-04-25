package mqp.ebt.tool;

import ch.idsia.agents.controllers.ReplayAgent;
import ch.idsia.benchmark.mario.engine.Replayer;
import ch.idsia.tools.MarioAIOptions;
import mqp.ebt.MarioXMLManager;
import mqp.mario.EBTAgent;
import mqp.mario.MQPMarioTask;
import org.jgap.gp.IGPProgram;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Main function that runs a specific EBT
 * @author Ross Foley and Karl Kuhn
 */
public class EBTReplayRunner {
    public static String runName = "radius1_200_newtask";
    public static int generation = 1000;
    public static int radius = 1;
    public static String fileName = "test2";

    public static void main(String[] args) throws IOException {
        // Set Mario options
        MarioAIOptions marioAIOptions = new MarioAIOptions();
        marioAIOptions.setVisualization(true);
        marioAIOptions.setFPS(30);
        MQPMarioTask task = new MQPMarioTask(marioAIOptions);

        // Load the replay data from file
        Replayer replayer = new Replayer(fileName);
        ReplayAgent agent = new ReplayAgent(fileName);

        // Initialize the replay
        replayer.openNextReplayFile();
        agent.setReplayer(replayer);

        // Play through one level to record a replay
        task.evaluateSingleLevel(agent, 0, 0);

        // Exit
        System.exit(0);
    }
}
