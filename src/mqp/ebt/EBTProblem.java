package mqp.ebt;

import mqp.gp.IfEnemyAhead;
import mqp.gp.IfEnemyBehind;
import mqp.gp.IfPitAhead;
import mqp.gp.IfWallAhead;
import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPProblem;
import org.jgap.gp.impl.DefaultGPFitnessEvaluator;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.gp.terminal.Terminal;

/**
 * GPProblem that handles creating a population of EBTs
 * @author Ross Foley and Karl Kuhn
 */
public class EBTProblem extends GPProblem {
    GPConfiguration config;
    final boolean useVerboseOutput = false;
    final int maxNodes = 100;

    public EBTProblem() throws InvalidConfigurationException {
        super(new GPConfiguration());
        config = getGPConfiguration();
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
            new Terminal(config, CommandGene.IntegerClass, 0, 63, true)
        }};

        return GPGenotype.randomInitialGenotype(config, types, argTypes, nodeSets, maxNodes, useVerboseOutput);
    }

    private void initConfig() throws InvalidConfigurationException {
        config.setMaxInitDepth(6);
        config.setPopulationSize(100);
        config.setCrossoverProb(0.9f);
        config.setReproductionProb(0.1f);
        config.setNewChromsPercent(0.3f);
        config.setStrictProgramCreation(true);
        config.setUseProgramCache(true);
        config.setGPFitnessEvaluator(new DefaultGPFitnessEvaluator());
        config.setFitnessFunction(new MarioFitnessFunction());
    }
}
