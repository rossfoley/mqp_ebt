package mqp.ebt;

import mqp.gp.command.*;
import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPProblem;
import org.jgap.gp.impl.DefaultGPFitnessEvaluator;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;

/**
 * GPProblem that handles creating a population of EBTs
 * @author Ross Foley and Karl Kuhn
 */
public class EBTProblem extends GPProblem {
    GPConfiguration config;
    final boolean useVerboseOutput = false;
    final int maxNodes = 100;
    private int radius;

    public EBTProblem(int radius) throws InvalidConfigurationException {
        super(new GPConfiguration());
        config = getGPConfiguration();
        this.radius = radius;
        initConfig();
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
            new ButtonPress(config)
        }};

        return GPGenotype.randomInitialGenotype(config, types, argTypes, nodeSets, maxNodes, useVerboseOutput);
    }

    /**
     * Initialize the GP configuration
     * @throws InvalidConfigurationException
     */
    private void initConfig() throws InvalidConfigurationException {
        config.setMaxInitDepth(8);
        config.setPopulationSize(100);
        config.setCrossoverProb(0.9f);
        config.setReproductionProb(0.1f);
        config.setNewChromsPercent(0.3f);
        config.setStrictProgramCreation(true);
        config.setUseProgramCache(true);
        config.setGPFitnessEvaluator(new DefaultGPFitnessEvaluator());
        config.setFitnessFunction(new MarioFitnessFunction(radius));
    }
}
