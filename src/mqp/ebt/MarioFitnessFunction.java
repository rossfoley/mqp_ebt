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
    private MQPMarioTask task;
    private int radius;

    public MarioFitnessFunction(int radius) {
        MarioAIOptions marioAIOptions = new MarioAIOptions();
        marioAIOptions.setVisualization(false);
        task = new MQPMarioTask(marioAIOptions);
        this.radius = radius;
    }

    /**
     * Evaluate the fitness of an EBT
     * @param ebt the EBT to evaluate
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
        // Create the agent
        EBTAgent agent = new EBTAgent(ebt, radius);

        // Evaluate the agent
        double fitness = Math.max(0, task.evaluate(agent));
        ebt.setFitnessValue(fitness);
        return fitness;
    }
}
