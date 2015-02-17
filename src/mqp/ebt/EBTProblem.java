package mqp.ebt;

import mqp.gp.IfEnemyAhead;
import mqp.gp.IfEnemyBehind;
import mqp.gp.IfPitAhead;
import mqp.gp.IfWallAhead;
import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPProblem;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.gp.terminal.Terminal;

/**
 * GPProblem that handles creating a population of EBTs
 * @author Ross Foley and Karl Kuhn
 */
public class EBTProblem extends GPProblem {
    GPConfiguration config;
    boolean useVerboseOutput = false;

    public EBTProblem(GPConfiguration c) throws InvalidConfigurationException {
        super(c);
        config = c;
    }

    /**
     * Create a population of EBTs
     * @return the EBT genotype
     * @throws InvalidConfigurationException
     */
    @Override
    public GPGenotype create() throws InvalidConfigurationException {
        Class[] types = {CommandGene.IntegerClass};
        Class[][] argTypes = {{}};
        CommandGene[][] nodeSets = {{
            new IfEnemyAhead(config),
            new IfEnemyBehind(config),
            new IfWallAhead(config),
            new IfPitAhead(config),
            // Button presses
            new Terminal(config, CommandGene.IntegerClass, 0, 63, true)
        }};

        return GPGenotype.randomInitialGenotype(config, types, argTypes, nodeSets, 100, useVerboseOutput);
    }

    /**
     * Start the evolving process in a new thread
     */
    public void start() {
        // Run the evolving thread
    }
}
