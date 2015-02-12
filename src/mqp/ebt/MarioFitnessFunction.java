package mqp.ebt;

import ch.idsia.tools.MarioAIOptions;
import mqp.mario.EBTAgent;
import mqp.mario.MQPMarioTask;
import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPProgram;

/**
 * Fitness evaluation function for EBTs
 * @author Ross Foley and Karl Kuhn
 */
public class MarioFitnessFunction extends GPFitnessFunction {
    /**
     * Evaluate the fitness of an EBT
     * @param ebt the EBT to evaulate
     * @return the fitness of the EBT
     */
    @Override
    protected double evaluate(IGPProgram ebt) {
        return computeFitness(ebt);
    }

    /**
     * Compute the fitness for an individual EBT
     * @param ebt the EBT to evaluate
     * @return the fitness of the EBT
     */
    public double computeFitness(IGPProgram ebt) {
        // Turn off the GUI for Mario
        MarioAIOptions marioAIOptions = new MarioAIOptions();
        marioAIOptions.setVisualization(false);

        // Create the MQP task and EBTAgent
        MQPMarioTask task = new MQPMarioTask(marioAIOptions);
        EBTAgent agent = new EBTAgent(ebt);

        // Evaluate the agent
        double fitness = task.evaluate(agent);
        ebt.setFitnessValue(fitness);
        return fitness;
    }
}
