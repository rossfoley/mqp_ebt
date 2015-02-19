package mqp.ebt;

import ch.idsia.tools.MarioAIOptions;
import com.thoughtworks.xstream.XStream;
import mqp.mario.EBTAgent;
import mqp.mario.MQPMarioTask;
import org.jgap.gp.impl.GPProgram;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Main function that runs a specific EBT
 * @author Ross Foley and Karl Kuhn
 */
public class EBTRunner {
    public static void main(String[] args) {
        String runName = "ebtTest";
        String fileName = "test.xml";
        int radius = 1;

        // Set Mario options
        MarioAIOptions marioAIOptions = new MarioAIOptions();
        marioAIOptions.setVisualization(true);
        marioAIOptions.setFPS(30);
        MQPMarioTask task = new MQPMarioTask(marioAIOptions);

        try {
            XStream xstream = new XStream();
            File file = new File("db/" + runName + "/" + fileName);
            InputStream is = new FileInputStream(file);
            GPProgram gp = (GPProgram) xstream.fromXML(is);
            EBTAgent agent = new EBTAgent(gp, radius);
            task.evaluate(agent);
        } catch (FileNotFoundException e) {
            System.out.println("Error in reading file!");
            System.exit(1);
        }

        System.exit(0);
    }
}
