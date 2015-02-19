package mqp.gp.event;

import mqp.ebt.MarioXMLManager;
import org.apache.log4j.Logger;
import org.jgap.event.GeneticEvent;
import org.jgap.event.GeneticEventListener;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.impl.GPGenotype;

import java.io.FileNotFoundException;

/**
 * Event listener that writes the generation's best EBT to a file
 * @author Ross Foley and Karl Kuhn
 */
public class MarioEBTWriter implements GeneticEventListener {
    private transient final Logger LOGGER = Logger.getLogger(MarioEvolvedListener.class);
    private transient final String runName;

    public MarioEBTWriter(String runName) {
        this.runName = runName;
    }

    public void geneticEventFired(GeneticEvent firedEvent) {
        GPGenotype gp = (GPGenotype) firedEvent.getSource();
        int generation = gp.getGPConfiguration().getGenerationNr();
        IGPProgram best = gp.getFittestProgram();
        try {
            MarioXMLManager.writeEBT(best, runName, generation);
        } catch (FileNotFoundException e) {
            LOGGER.fatal("Couldn't write generation " + generation + " XML file!");
        }
    }
}
