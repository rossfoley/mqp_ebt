package mqp.gp.command;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;

/**
 * Command to check if Mario is on the ground
 * @author Ross Foley and Karl Kuhn
 */
public class IfMarioOnGround extends MarioCommand {
    public IfMarioOnGround(GPConfiguration config) throws InvalidConfigurationException {
        super(config);
    }

    @Override
    public int execute_int(ProgramChromosome c, int n, Object[] args) {
        if (getAgent(c).getMarioOnGround()) {
            return c.execute_int(n, 0, args);
        } else {
            return c.execute_int(n, 1, args);
        }
    }

    @Override
    public String toString() {
        return "if mario_on(ground) then &1 else &2";
    }

    @Override
    public String getName() {
        return "IfMarioOnGround";
    }
}
