package mqp.ebt;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.GPProblem;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;

/**
 * GPProblem that handles creating a population of EBTs
 * @author Ross Foley and Karl Kuhn
 */
public class EBTProblem extends GPProblem {
    GPConfiguration config;

    public EBTProblem(GPConfiguration c) throws InvalidConfigurationException {
        super(c);
        config = c;
        config.setFitnessFunction(new MarioFitnessFunction());
    }

    /**
     * Create a population of EBTs
     * @return the EBT genotype
     * @throws InvalidConfigurationException
     */
    @Override
    public GPGenotype create() throws InvalidConfigurationException {
        return null;
    }

    /**
     * Start the evolving process in a new thread
     */
    public void start() {
        // Run the evolving thread
    }
}
