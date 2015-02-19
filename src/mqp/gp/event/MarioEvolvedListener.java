package mqp.gp.event;

import org.apache.log4j.Logger;
import org.jgap.event.GeneticEvent;
import org.jgap.event.GeneticEventListener;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.util.SystemKit;

/**
 * Event listener that prints out info for each generation
 * @author Ross Foley and Karl Kuhn
 */
public class MarioEvolvedListener implements GeneticEventListener {
    private transient final Logger LOGGER = Logger.getLogger(MarioEvolvedListener.class);

    public MarioEvolvedListener() {}

    public void geneticEventFired(GeneticEvent firedEvent) {
        GPGenotype gp = (GPGenotype) firedEvent.getSource();
        int generation = gp.getGPConfiguration().getGenerationNr();
        int freeMem = (int) SystemKit.getFreeMemoryMB();
        double bestFitness = gp.getFittestProgram().getFitnessValue();

        LOGGER.info("Results for Generation " + generation);
        LOGGER.info("Best Fitness: " + bestFitness);
        LOGGER.info("Free Memory: " + freeMem + " MB");
    }
}
