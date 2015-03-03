package mqp.ebt;

import mqp.gp.command.*;
import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPProblem;
import org.jgap.gp.impl.DefaultGPFitnessEvaluator;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;

import java.util.ArrayList;
import java.util.List;

/**
 * GPProblem that handles creating a population of EBTs
 * @author Ross Foley and Karl Kuhn
 */
public class EBTProblem extends GPProblem {
    GPConfiguration config;
    final boolean useVerboseOutput = false;
    final int maxNodes = 300;
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
        return GPGenotype.randomInitialGenotype(config, types, argTypes, generateGenes(), maxNodes, useVerboseOutput);
    }

    /**
     * Generate an array of genes for the EBT to use
     * @return the array of genes
     * @throws InvalidConfigurationException
     */
    private CommandGene[][] generateGenes() throws InvalidConfigurationException {
        List<CommandGene> genes = new ArrayList<CommandGene>();

        // Add the position dependent genes
        for (int x = -1 * radius; x <= radius; x++) {
            for (int y = -1 * radius; y <= radius; y++) {
                genes.add(new IfBlockAtPosition(config, x, y));
                genes.add(new IfEnemyAtPosition(config, x, y));
            }
        }

        // Add the other genes
        genes.add(new IfMarioOnGround(config));
        genes.add(new IfMarioCanJump(config));
        genes.add(new ButtonPress(config));

        // Cast it to a double array because JGAP
        CommandGene[][] result = {genes.toArray(new CommandGene[0])};
        return result;
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
