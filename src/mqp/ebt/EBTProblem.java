package mqp.ebt;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.GPProblem;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;

/**
 * Created by rossfoley on 2/11/15.
 */
public class EBTProblem extends GPProblem {
    GPConfiguration config;
    public EBTProblem(GPConfiguration c) throws InvalidConfigurationException {
        super(c);
        config = c;
        config.setFitnessFunction(new MarioFitnessFunction());
    }

    @Override
    public GPGenotype create() throws InvalidConfigurationException {
        return null;
    }

    public void start() {
        // Run the evolving thread
    }
}
