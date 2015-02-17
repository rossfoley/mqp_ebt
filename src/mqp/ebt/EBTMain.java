package mqp.ebt;


import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.DefaultGPFitnessEvaluator;
import org.jgap.gp.impl.GPConfiguration;

/**
 * Main function that evolves EBTs
 * @author Ross Foley and Karl Kuhn
 */
public class EBTMain {
    public static void main(String[] args) throws InvalidConfigurationException {
        GPConfiguration config = new GPConfiguration();
        // TODO: CHANGE THESE VALUES TO REAL CONFIG VALUES IN THE FUTURE
        config.setMaxInitDepth(6);
        config.setPopulationSize(100);
        config.setCrossoverProb(0.9f);
        config.setReproductionProb(0.1f);
        config.setNewChromsPercent(0.3f);
        config.setStrictProgramCreation(true);
        config.setUseProgramCache(true);
        config.setGPFitnessEvaluator(new DefaultGPFitnessEvaluator());
        config.setFitnessFunction(new MarioFitnessFunction());

        EBTProblem problem = new EBTProblem(config);
        problem.start();
    }
}
