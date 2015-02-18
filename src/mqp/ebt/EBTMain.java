package mqp.ebt;


import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPGenotype;

/**
 * Main function that evolves EBTs
 * @author Ross Foley and Karl Kuhn
 */
public class EBTMain {
    public static final int numGenerations = 10;

    public static void main(String[] args) throws InvalidConfigurationException {
        // Setup the initial population
        EBTProblem problem = new EBTProblem();
        GPGenotype gp = problem.create();
        gp.setVerboseOutput(true);

        // Run the evolution process
        gp.evolve(numGenerations);

        // Output the best solution
        System.out.println("Best EBT for Mario:");
        gp.outputSolution(gp.getAllTimeBest());
    }
}
