package mqp.ebt;

import mqp.gp.event.MarioEvolvedListener;
import org.apache.log4j.Logger;
import org.jgap.InvalidConfigurationException;
import org.jgap.event.GeneticEvent;
import org.jgap.event.IEventManager;
import org.jgap.gp.impl.GPGenotype;

/**
 * Main function that evolves EBTs
 * @author Ross Foley and Karl Kuhn
 */
public class EBTMain {
    public static final int numGenerations = 5;
    public static final int radius = 1;
    public static transient Logger LOGGER = Logger.getLogger(EBTMain.class);

    public static void main(String[] args) throws InvalidConfigurationException {
        // Setup the initial population
        EBTProblem problem = new EBTProblem(radius);
        GPGenotype gp = problem.create();
        IEventManager eventManager = gp.getGPConfiguration().getEventManager();
        eventManager.addEventListener(GeneticEvent.GPGENOTYPE_EVOLVED_EVENT, new MarioEvolvedListener());
        gp.setVerboseOutput(false);

        // Run the evolution process
        LOGGER.info("Starting evolution process");
        gp.evolve(numGenerations);

        // Output the best solution
        LOGGER.info("Best EBT for Mario:");
        gp.outputSolution(gp.getAllTimeBest());
    }
}
